"use strict"
let key =  "87ed2c6f01764209ad3d9dbbcb12efc8";
let keys = "";
let ingredient;
let ingredArray = [];



//autocomplete function
function nutrients() {


    fetch(`https://api.spoonacular.com/recipes/autocomplete?apiKey=${key}&query=${keys}&number=5`)
        .then(response => response.json())
        .then(response => {

            let list = document.getElementById("ingredients")
            for (let i = 0; i < response.length; i++) {
                let option = document.createElement("option")
                option.value = response[i].title;
                list.append(option)

            }


        })
        .catch(err => console.error(err));
}
document.getElementById("ingred").addEventListener("keyup", function (e) {

    keys += e.key
    nutrients()
})
nutrients();

//reload the session to remove ingredients
document.getElementById("redo").addEventListener("click", function () {
    location.reload()
})

// eventlistener for search recipes by ingredients
document.getElementById("submit").addEventListener("click", function () {
    let search = document.querySelector("#ingred");
    if (search.value !== "") {
        document.getElementById("lists").innerText = ""
        ingredArray.push(search.value)
        document.getElementById("lists").innerText = ingredArray.toString()
        search.value = "";
        keys = "";
        document.getElementById("nutrition").innerText = ""
        recipesByIngredients()
    }


})




// function for searching and populating cards as well as storing recipe id, image url, and title
function recipesByIngredients() {
    if (ingredArray.length !== 0) {


        fetch(`https://api.spoonacular.com/recipes/search?apiKey=${key}&number=8&query=${ingredArray.toString()}`)
            .then(response => response.json())
            .then(response => {
                console.log(response);

                document.getElementById("pics").innerHTML = "";

                let num = response.results.length;

                for (let i = 0; i < num; i++) {

                    let url =  response.baseUri + response.results[i].image

                    let html =

                        `<div class="card shadow col-4" style="width: 18rem;">
                            <img class="card-img-top" src="${url}" alt="Card image cap">
                                <div class="card-body">
                                    <h5 class="card-title">"${response.results[i].title}"</h5>
                                    <p class="card-text nutrition" id="${i}"></p>
                                    
                                    <button  class="btn btn-primary reddit" type="button" id="${response.results[i].id}">Nutrition</button>
                                    <input class="add" name="add" type="submit" value="Add" idd="${response.results[i].id}" title2="${response.results[i].title}" image="${url}">
                                </div>
                        </div>`



                    document.getElementById("pics").innerHTML += html



                }



                document.querySelectorAll(".reddit").forEach(x => {
                    console.log(x);
                    x.addEventListener("click", function (e) {

                        document.getElementById(x.previousElementSibling.id).innerText = ""



                        nuts(x.id)
                        // location.assign("http://localhost:8080/hello")
                    })
                })


                document.querySelectorAll(".add").forEach((x) => {

                    x.addEventListener("click", function (e) {
                        let hidden = document.getElementById("hidden")


                        hidden.value = x.getAttribute("idd")+ "<"+ x.getAttribute("title2") +"<"+x.getAttribute("image")
                        document.getElementById("form").submit();



                    })
                })


            })
            .catch(err => console.error("thisdfsd" + err));
    }
}


function nuts(id) {
    fetch(`https://api.spoonacular.com/recipes/${id}/information?apiKey=${key}&includeNutrition=true`)
        .then(response => response.json())
        .then(response => {
            document.getElementById(id).previousElementSibling.innerHTML = ""

            console.log(response)
            document.getElementById(id).previousElementSibling.innerHTML += "Nutrition: "
            for (let i = 0; i < 9; i++) {
                if (i === 0 | i === 1 | i === 3 | i === 8) {
                    document.getElementById(id).previousElementSibling.innerHTML += "  " + response.nutrition.nutrients[i].name + ": " + response.nutrition.nutrients[i].amount + "  |"
                }
            }
            document.getElementById(id).previousElementSibling.innerHTML += "<br> Ingredients:"

            for (let i = 0; i < response.nutrition.ingredients.length; i++) {

                document.getElementById(id).previousElementSibling.innerHTML += "  " + response.nutrition.ingredients[i].name + "  |"
            }

        })
        .catch(err => console.error(err));
}



// fetch(`https://api.spoonacular.com/food/wine/dishes?wine=malbec&apiKey=${key}`)
//     .then(response => response.json())
//     .then(response => {
//
//
//         console.log(response)
//
//     })
//     .catch(err => console.error(err));



