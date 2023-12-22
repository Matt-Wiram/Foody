let key =  "87ed2c6f01764209ad3d9dbbcb12efc8";
let array = []
let html = ""
document.querySelectorAll(".nutrition").forEach((x) => {

    nuts(x.id)
    x.innerHTML =  html

})

function nuts(id) {
    fetch(`https://api.spoonacular.com/recipes/${id}/information?apiKey=${key}&includeNutrition=true`)
        .then(response => response.json())
        .then(response => {
            html = ""
            let html2 = "Ingredients: "
            for (let i = 0; i < 9; i++) {
                if (i === 0 | i ===  1 | i ===  3 | i ===  8) {
                    html += "  " + response.nutrition.nutrients[i].name + ": " + response.nutrition.nutrients[i].amount + "  |"
                }

            }
            for (let i = 0; i < response.nutrition.ingredients.length; i++) {
                html2 += " " + response.nutrition.ingredients[i].name + " |"
            }

            console.log(response)


            document.getElementById(id).innerHTML = "Nutrition: " + html


            document.getElementById(id).nextElementSibling.innerHTML = html2


        })
        .catch(err => console.error(err));
}
