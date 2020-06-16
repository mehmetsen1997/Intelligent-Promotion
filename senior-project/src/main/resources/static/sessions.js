var seconds = 0;

function timeCounter(){

    setInterval( function(){
       seconds+=1
    }, 1000);


}

timeCounter();

function divisibileByTen(num){

    if(num % 10){
        return true;
    }
    else{
        return false;
    }
}
function sessionIdTime(){

    if(sessionStorage.length == 0){

        localStorage.setItem("sessionId", timeTake());
        sessionStorage.setItem("revenue", false);
        sessionStorage.setItem("informative", 0);
        sessionStorage.setItem("informativeDuration", 0);
        sessionStorage.setItem("administrative", 0);
        sessionStorage.setItem("administrativeDuration", 0);
        sessionStorage.setItem("productRelated", 0);
        sessionStorage.setItem("productRelatedDuration", 0);

    }

};

sessionIdTime();

function checkTenSeconds(){

    denemeJSON();
    setTimeout(checkTenSeconds, 2000);
}

checkTenSeconds();

function pageTime(category){
    
    if(sessionStorage.getItem(category+"Duration")===null){        
        sessionStorage.setItem(category+"Duration",seconds);
    }

    else{
        sessionStorage.setItem(category+"Duration",parseInt(sessionStorage.getItem(category+"Duration"))+1);
    }

}

function pageTimeEverySecondProduct(){

    
    pageTime('productRelated');
    setTimeout(pageTimeEverySecondProduct, 1000);
}

function pageTimeEverySecondAdministrative(){

    
    pageTime('administrative');
    setTimeout(pageTimeEverySecondAdministrative, 1000);
}

function pageTimeEverySecondInformative(){

    
    pageTime('informative');
    setTimeout(pageTimeEverySecondInformative, 1000);
}

function timeTake() {

    let timeStamp;
    timeStamp = new Date().getTime() + '.' + Math.random().toString(36).substring(5);
    return timeStamp;
}


function pageViewCounter(category){
    if(sessionStorage.getItem(category)==null){
        sessionStorage.setItem(category,1);
    }
    else{
        console.log(sessionStorage.getItem(category));
        sessionStorage.setItem(category,parseInt(sessionStorage.getItem(category))+1);
        console.log(sessionStorage.getItem(category));
    }
}

function purchaseClicked(){
	sessionStorage.setItem("revenue", true);
	gtag('event', 'purchase', {
		"transaction_id": localStorage.getItem("SessionTime"),
		"value": localStorage.getItem("totalCost"),
		"currency": "USD"
	});  
}

function createCORSRequest(method, url) {
    var xhr = new XMLHttpRequest();
    if ("withCredentials" in xhr) {
      xhr.open(method, url, true);
    } else if (typeof XDomainRequest != "undefined") {
      xhr = new XDomainRequest();
      xhr.open(method, url);
    } else {
      xhr = null;
    }
    return xhr;
  }

//Get the modal
var modal = document.getElementById("myModal");

var freeShip = document.getElementById("freeShipping");
var disc = document.getElementById("discount");
var gift = document.getElementById("giftCard");


    // Get the button that opens the modal
var btn = document.getElementById("myBtn");

    // Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];


    // When the user clicks on <span> (x), close the modal
span.onclick = function() {
  modal.style.display = "none";
}


function closeModal() {
  modal.style.display = "none";
  
}

        

    // When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
  if (event.target == modal) {
    modal.style.display = "none";
	console.log(localStorage.getItem("promosionShowed"));

  }
}

function denemeJSON(){

    
    // Gives name of the page exp = "/purchase.html"
    let location = window.location.href;
    let parsedLocation = location.split("/");
    parsedLocation = parsedLocation[[parsedLocation.length-1]]
    parsedLocation = "/"+ parsedLocation;
    sessionStorage.setItem("currentPage", parsedLocation);

    // Hold entire information to send JSON
    let hold = [];

    var id = {};
    id["sessionId"] = localStorage.getItem("sessionId");
    //id[Object.keys(localStorage)[3]] = Object.values(localStorage)[3];

    for(let i = 0; i < sessionStorage.length; i++){

        
        id[Object.keys(sessionStorage)[i]] = Object.values(sessionStorage)[i];
      
    }
    
    hold.push(id);
    
    var result = JSON.stringify(id);

    console.log(result); 
//    console.log("can");
    
    var xhr = new XMLHttpRequest();
//    xhr.open("POST", "http://localhost:8080/realTimeData", true);
//    xhr.setRequestHeader('Content-Type', 'application/json');
//    xhr.send(result);
    xhr.open("POST", "/realTimeData",true);
    xhr.setRequestHeader("Content-type", "application/json");
//    xhr.onreadystatechange = function() {//Call a function when the state changes.
//        if(xhr.readyState == 4 && xhr.status == 200) {
//            alert(xhr.responseText);
//        }
//    }
    
    xhr.onreadystatechange = function() {
        if(xhr.status == 200) {
        	
            var myResponse = JSON.parse(xhr.responseText);
            console.log(myResponse);
            gift.style.display = "none";
            disc.style.display = "block";
            freeShip.style.display = "none";

            if(myResponse.content != null){
                
                if(myResponse.content.type.contentTypeId == 1){

                    gift.style.display = "none";
                    disc.style.display = "block";
                    freeShip.style.display = "none";
                }
                if(myResponse.content.type.contentTypeId == 2){

                    gift.style.display = "none";
                    disc.style.display = "none";
                    freeShip.style.display = "block";
                }
                if(myResponse.content.type.contentTypeId == 3){

                    gift.style.display = "block";
                    disc.style.display = "none";
                    freeShip.style.display = "none";
                }
                
            }
            modal.style.display = "block";

        }
    }
    xhr.send(result);


}


function purchase(){

    console.log(localStorage.getItem("sessionId"));

}

sessionIdTime();



