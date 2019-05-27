

function checkout(){
	
	var request = new XMLHttpRequest();
	request.onreadystatechange = function(){
		if (this.readyState == 4 && this.status == 200) {
			console.log(this.responseText);
		}
	};
	request.open("POST", "PurchaseServlet", true);
	request.setRequestHeader("content-type", "application/x-www-form-urlencoded");
	
	
	var parameters = $("#order_form").serialize();
	for(i = 0; i < window.cartData.length; i++){
		parameters += "&" + i.toString() + "=" + i.toString(); 
	}
	console.log(parameters);
	request.send(parameters);
	
}

var cartData;


function makeRequest(){
	
	var request = new XMLHttpRequest();
	request.onreadystatechange= function (){
		if (this.readyState == 4 && this.status == 200) {
			console.log(this.responseText);
			
			var jsonLines = this.responseText.split("*");
			
			var productsData = JSON.parse(jsonLines[0]);
			cartData = JSON.parse(jsonLines[1]);
			
			var table = document.getElementById("cart_table").getElementsByTagName('tbody')[0];
			
			for(i = 0; i < cartData.length;i++){
				//create empty <tr> element; add it to position i
				var row = table.insertRow(-1); 
				var cell1 = row.insertCell(0);
				cell1.innerHTML = productsData[parseInt(cartData[i]["id"]) -1]["title"];
			}
			
			
			
		
		}	
	};//
	request.open("GET", "CartServlet", true);
	request.send();
	
}

makeRequest();