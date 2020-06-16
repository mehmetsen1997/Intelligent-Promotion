let carts = document.querySelectorAll('.add-cart');

let products = [
    {
        name: 'Acer',
        tag: 'acer',
        price: 2500,
        inCart: 0
    },
    {
        name: 'Monster',
        tag: 'monster',
        price: 4500,
        inCart: 0
    },
    {
        name: 'Dell',
        tag: 'dell',
        price: 1750,
        inCart: 0
    },
    {
        name: 'Acer',
        tag: 'acer',
        price: 2500,
        inCart: 0
    },
    {
        name: 'Monster',
        tag: 'monster',
        price: 4500,
        inCart: 0
    },
    {
        name: 'Dell',
        tag: 'dell',
        price: 1750,
        inCart: 0
    }
]

for(let i=0; i < carts.length ; i++){

    carts[i].addEventListener('click', () => {

        cartnumbers(products[i]);
        totalCost(products[i]);
    })
}

function cartnumbers(product) {
    let productNumbers = localStorage.getItem('cartnumbers');
    productNumbers = parseInt(productNumbers);

    
    if( productNumbers ) {
        localStorage.setItem('cartnumbers',  productNumbers + 1)
        document.querySelector('.card span').textContent = productNumbers + 1
    }
    else{
        localStorage.setItem('cartnumbers', 1);
        document.querySelector('.card span').textContent = 1
    }

    setItems(product);
}

function setItems(product) {

    let cardItems = localStorage.getItem('productsInCart');
    cardItems = JSON.parse(cardItems);

    if(cardItems != null){

        if(cardItems[product.tag] == undefined){

            cardItems = {
                ...cardItems,
                [product.tag]: product
            }

        }
        cardItems[product.tag].inCart += 1;
    }
    else{
        product.inCart = 1;

        cardItems = {
            [product.tag]: product
        }

    }

    
    

    localStorage.setItem("productsInCart" , JSON.stringify(cardItems));
    

}

function totalCost(product){

    let totalCartCost = localStorage.getItem('totalCost');

    if (totalCartCost != null){
        totalCartCost = parseInt(totalCartCost);
        localStorage.setItem('totalCost', totalCartCost + product.price);
    }else{

        localStorage.setItem('totalCost', product.price);
    }

    
}


function loadCartNumbers () {
    let productNumbers = localStorage.getItem('cartnumbers');
    
    if (productNumbers) {
        document.querySelector('.card span').textContent = productNumbers;

    }
}

function deleteCart(){

    let holdTime = localStorage.getItem("sessionId");
    localStorage.clear();
    location.reload();
    localStorage.setItem("sessionId", holdTime);
}




function displayCart(){
    let cartItems = localStorage.getItem("productsInCart");
    cartItems = JSON.parse(cartItems);

    let productCont = document.querySelector(".allProducts");
    let totalCartCost = localStorage.getItem('totalCost');
    if(cartItems && productCont){

        productCont.innerHTML = '';
        Object.values(cartItems).map(item => {
            productCont.innerHTML +=  `
                <div>
                    <table>
                        <tr>
                        </br \>
                            <th>${item.name}</th>
                            <th>${item.price}</th>
                            <th>${item.inCart}</th>
                            <th>${item.inCart * item.price} </th>
                        </tr>
                
                    </table>
                    
                </div>
            
            `
        } ) 

        productCont.innerHTML += `

            </br \>
            

            <div class="totalCost">
                
                <h4>TOTAL = ${totalCartCost}</h4>
            </div>
        
        `
    }


}
/*
function sessionDeneme() {

    if (sessionStorage.clickcount) {
        sessionStorage.clickcount = Number(sessionStorage.clickcount) + 1;
      } else {
        sessionStorage.clickcount = 1;
      }
      document.getElementById("result").innerHTML = "You have clicked the button " +
      sessionStorage.clickcount + " time(s) in this session.";
}
*/






loadCartNumbers();
displayCart();

