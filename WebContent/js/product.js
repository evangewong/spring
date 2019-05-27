


function getUrlVars() {//gets the paramters in url, returns a dictionary..... /page.html?prouduct=first -> {'product' : 'first'}
    var vars = {};
    var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
        vars[key] = value;
    });
    return vars;
}

function makeRequest(product){
	
	var request = new XMLHttpRequest();
	request.onreadystatechange= function (){
		if (this.readyState == 4 && this.status == 200) {
			console.log(this.responseText);
			var myObj = JSON.parse(this.responseText); //note: JSON.parse() returns jsonarray
			var productImage = document.getElementById("productImage");
			productImage.setAttribute("src", myObj["url"]);  //sets the src attribute in the img element

			document.getElementById("productOrigin").innerHTML = myObj["origin"];
			document.getElementById("productDesc").innerHTML = myObj["description"];
//
			for(j = 0; j < myObj["type"].length; j++){
            	if(j>0){
               		document.getElementById("productType").innerHTML += ",";
            	}
                document.getElementById("productType").innerHTML += myObj["type"][j]["name"];
            }
			
		    document.getElementById("price").innerHTML = myObj["price"];
		}
	};
	request.open("GET", "ProductServlet?product="+ product, true);
	request.send();
	
}



 //ajax function to make post request for product order
 function AddToCart(){
    
	
 	var request = new XMLHttpRequest();
 	request.onreadystatechange = function(){
 		if (this.readyState == 4 && this.status == 200) {
 			console.log(this.responseText);
 			alert("Item has been added to cart");
 		}
 	};
 	request.open("POST", "CartServlet", true);
 	request.setRequestHeader("content-type", "application/x-www-form-urlencoded");
 	request.send("product="+product); 
	
 }





//ajax function to make post request for product history servlet
function postHistory(){
 
	
	var request = new XMLHttpRequest();
	request.onreadystatechange = function(){
		if (this.readyState == 4 && this.status == 200) {
			console.log(this.responseText);
		}
	};
	request.open("POST", "VisitedProducts", true);
	request.setRequestHeader("content-type", "application/x-www-form-urlencoded");
	request.send("visited="+product); 
	
}

var product = getUrlVars()["product"]; //gets the actual paramter from the url. logs it to console.
makeRequest(product);
postHistory();
