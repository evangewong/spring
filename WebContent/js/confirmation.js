
function getUrlVars() {
    var vars = {};
    var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
        vars[key] = value;
    });
    return vars;
}

let id = getUrlVars()["order"];
function makeRequest(product){
    
    var request = new XMLHttpRequest();
    request.onreadystatechange= function (){
        if (this.readyState == 4 && this.status == 200) {
            var myObj = JSON.parse(this.responseText); //note: JSON.parse() returns jsonarray
            console.log(myObj)
            

            let order_table = jQuery("#order_table_body");
            console.log(order_table);

            let rowHTML = "";
            rowHTML += "<tr>";
            rowHTML += "<th>" + myObj["id"] + "</th>";
            rowHTML += "<th>" + myObj["productTitle"] + "</th>";
            rowHTML += "<th>" + myObj["quantity"] + "</th>";
            rowHTML += "<th>" + myObj["shippingMethod"] + "</th>";
            rowHTML += "</tr>"

            order_table.append(rowHTML);



            let customer_table = jQuery("#customer_table_body");
            console.log(customer_table);

            rowHTML = "";
            rowHTML += "<tr>";
            rowHTML += "<th>" + myObj["firstName"] + " " + myObj["lastName"] + "</th>";
            rowHTML += "<th>" + myObj["address"] + "</th>";
            rowHTML += "<th>" + myObj["phoneNumber"] + "</th>";
            rowHTML += "<th>" + myObj["ccNumber"].slice(11,15) + "</th>";
            rowHTML += "</tr>"

            customer_table.append(rowHTML);

            
            
        }
    };
    request.open("GET", "../php/confirmation.php?order="+ id, true);
    request.send();
    
}

makeRequest(id);




// 	let order_table = jQuery("#order_table_body");
// 	for(let i = 0; i < resultData.length;i++){
// 		let rowHTML = "";
// 		rowHTML += "<tr>";		
// 		rowHTML += "<th>" + resultData[i]["title"] + "</th>";
// 		rowHTML += "<th>"+ resultData[i]["quantity"]+ "</th>";
// 		rowHTML += "</tr>";
//         order_table.append(rowHTML);
// 	}

// }



// jQuery.ajax({
//     dataType: "json",  
//     method: "GET",
//     url: "api/storecart", 
//     success: (resultData) => handleResult(resultData)
// });