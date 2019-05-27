
function goToProduct(place){
	window.location.replace("product.html?product=" + place);
}


	
//ajax function to get database info

function makeRequest(){
	
	var request = new XMLHttpRequest();
	request.onreadystatechange= function (){
		if (this.readyState == 4 && this.status == 200) {
			console.log(this.responseText);
			
			var jsonLines = this.responseText.split("*");

			
			 var dataArr = JSON.parse(jsonLines[0]);
			 console.log(dataArr);
			// //gets productTable to populate
			 var table = document.getElementById("productTable").getElementsByTagName('tbody')[0];
			
//			loop through json array
			for(i = 0; i < dataArr.length; i++){
				
				//create empty <tr> element; add it to position i
				var row = table.insertRow(-1); 
				
				//insert 5 <td> elements
				var cell1 = row.insertCell(0);
				var cell2 = row.insertCell(1);
				var cell3 = row.insertCell(2);
				var cell4 = row.insertCell(3);
				var cell5 = row.insertCell(4);
				
				
				//add text to the new cells from json array
				//cell1.setAttribute("src", dataArr[i]["url"]);
                //cell1.setAttribute("href","/product.html?product=");
                
                var memeImage = document.createElement('img');
                memeImage.setAttribute('src',dataArr[i]['url']);
                memeImage.setAttribute('onclick', 'goToProduct(' + String(i+1) +  ")");
                cell1.appendChild(memeImage);




				cell2.innerHTML = dataArr[i]["title"];
                cell3.innerHTML = dataArr[i]["origin"];
                for(j = 0; j < dataArr[i]["types"].length; j++){
                		if(j>0)
                			cell4.innerHTML += ",";
                		cell4.innerHTML += dataArr[i]["types"][j]["name"];
            	}		
                cell5.innerHTML = "$" + dataArr[i]["price"];
				
			}
			
			
			//handle visited
			var visitedArr = JSON.parse(jsonLines[1]);
			console.log(visitedArr)
			var visitedTable = document.getElementById("visitedTable").getElementsByTagName('tr')[0];
			for(i = 0; i < visitedArr.length; i++){
				var th = document.createElement('th');
				var image = document.createElement('img');
				image.setAttribute('src',dataArr[parseInt(visitedArr[i]['id'],10)-1]['url']);
				th.appendChild(image);
				visitedTable.appendChild(th);
			}
			
			
			
  }
	};//
	request.open("GET", "ProductsServlet", true);
	request.send();
	
}

makeRequest();
