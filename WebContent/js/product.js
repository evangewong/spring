


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
//			var myObj = JSON.parse(this.responseText); //note: JSON.parse() returns jsonarray
//			var productImage = document.getElementById("productImage");
//			productImage.setAttribute("src", myObj["url"]);  //sets the src attribute in the img element
//
//			document.getElementById("productOrigin").innerHTML = myObj["origin"];
//			document.getElementById("productDesc").innerHTML = myObj["description"];
//
//			for(j = 0; j < myObj["type"].length; j++){
//            	if(j>0){
//               		document.getElementById("productType").innerHTML += ",";
//            	}
//                document.getElementById("productType").innerHTML += myObj["type"][j];
//            }
//			
//		    document.getElementById("price").innerHTML = myObj["price"];
		    // document.getElementById("taxPrice").innerHTML = myObj["price"];
		}
	};
	request.open("GET", "ProductServlet?product="+ product, true);
	request.send();
	
}

// //ajax function to get city and state from zip code
// function getPlace(zip){
//     var xhr = new XMLHttpRequest();
//     xhr.onreadystatechange = function () {
//         // 4 means finished, and 200 means okay
//         if (xhr.readyState == 4 && xhr.status == 200)
//         {
//             var result = xhr.responseText;
//             var place = result.split(', ');
//             if (document.getElementById("address_city").value == "")
//                 document.getElementById("address_city").value = place[0];
//             if (document.getElementById("address_state").value == "")
//                 document.getElementById("address_state").value = place[1];
//         }
//     };
//     xhr.open("GET","../php/getCityState.php?address_zip=" + zip, true);
//     xhr.send();
// }

// //ajax function to get price from product and tax rate from zip code
// function getTaxPrice(zip, price){
//     var xhr = new XMLHttpRequest();
//     xhr.onreadystatechange = function () {
//         // 4 means finished, and 200 means okay
//         if (xhr.readyState == 4 && xhr.status == 200)
//         {
//             var result = xhr.responseText;
//             //if (document.getElementById("price").value == "")
//                 document.getElementById("taxPrice").innerHTML = result;
//         }
//     };
//     xhr.open("GET","../php/getTaxPrice.php?address_zip=" + zip +
//         "&price=" + price, true);
//     xhr.send();
// }

// //ajax function to update info
// function updateInfo(zip){
//     getPlace(zip);
//     getTaxPrice(zip, document.getElementById("price").innerText);
// }

// //ajax function to make post request for product order
// function postOrder(formSubmitEvent){
    
	
// 	formSubmitEvent.preventDefault();
// 	var request = new XMLHttpRequest();
// 	request.onreadystatechange = function(){
// 		if (this.readyState == 4 && this.status == 200) {
// 			console.log(this.responseText);
// 			window.location.replace("confirmation.html?order=" + this.responseText);
// 		}
// 	};
// 	request.open("POST", "../php/postOrder.php", true);
// 	request.setRequestHeader("content-type", "application/x-www-form-urlencoded");
// 	console.log(document.getElementById("taxPrice").innerHTML)
// 	request.send($("#order_form").serialize() + "&product="+product); 
	
// }


// $("#order_form").submit((event) => postOrder(event));


var product = getUrlVars()["product"]; //gets the actual paramter from the url. logs it to console.
makeRequest(product);

