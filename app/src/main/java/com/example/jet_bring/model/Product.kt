package com.example.jet_bring.model

import androidx.compose.runtime.Immutable
import com.example.jet_bring.R

@Immutable
data class Product(
    val id : Long,
    val name: String,
    val icon: Int,
    var description: String?=null
)

fun getProduct(productName : String, description : String) : Product {
    val tmp = Product(0, "", R.drawable.product_apple, "")
    for (item in products) {
        if(item.name == productName){
            return Product(item.id, item.name, item.icon, description)
        }
    }
    return tmp
}

val products = listOf(
    /**
     *
     * Frutta e verdura
     *
     */
    Product(
        id = 1,
        name = "Mela",
        icon = R.drawable.product_apple,
    ),
    Product(
        id = 2,
        name = "Pera",
        icon = R.drawable.product_pear
    ),
    Product(
        id = 3,
        name = "Arancia",
        icon = R.drawable.product_orange
    ),
    Product(
        id = 4,
        name = "Aglio",
        icon = R.drawable.product_garlic
    ),
    Product(
        id = 5,
        name = "Banane",
        icon = R.drawable.product_banana
    ),
    Product(
        id = 6,
        name = "Limone",
        icon = R.drawable.product_lemon
    ),
    Product(
        id = 7,
        name = "Cipolla",
        icon = R.drawable.product_onion
    ),
    Product(
        id = 8,
        name = "Carote",
        icon = R.drawable.product_carrot
    ),
    Product(
        id = 9,
        name = "Zenzero",
        icon = R.drawable.product_ginger
    ),
    Product(
        id = 10,
        name = "Insalata",
        icon = R.drawable.product_salad
    ),
    Product(
        id = 11,
        name = "Fragole",
        icon = R.drawable.product_strawberry
    ),
    Product(
        id = 12,
        name = "Zucca",
        icon = R.drawable.product_pumpkin
    ),
    Product(
        id = 13,
        name = "Albicocche",
        icon = R.drawable.product_apricot
    ),
    Product(
        id = 14,
        name = "Funghi",
        icon = R.drawable.product_mushrooms
    ),
    Product(
        id = 15,
        name = "Pomodori",
        icon = R.drawable.product_tomato
    ),
    /**
     *
     * Panetteria
     *
     */

    Product(
        id = 16,
        name = "Toast",
        icon = R.drawable.product_toast
    ),
    Product(
        id = 17,
        name = "Pane",
        icon = R.drawable.product_bread
    ),
    Product(
        id = 18,
        name = "Cornetti",
        icon = R.drawable.product_croissant
    ),
    /**
     *
     * Latte e formaggi
     *
     */
    Product(
        id = 19,
        name = "Latte",
        icon = R.drawable.product_milk
    ),
    Product(
        id = 20,
        name = "Uova",
        icon = R.drawable.product_egg
    ),
    Product(
        id = 21,
        name = "Mozzarella",
        icon = R.drawable.product_mozzarella
    ),
    Product(
        id = 22,
        name = "Formaggio",
        icon = R.drawable.product_cheese
    ),
    Product(
        id = 23,
        name = "Burro",
        icon = R.drawable.product_butter
    ),
    /**
     *
     * Carne e pesce
     *
     */
    Product(
        id = 24,
        name = "Acciughe",
        icon = R.drawable.product_anchovy
    ),
    Product(
        id = 25,
        name = "Pancetta",
        icon = R.drawable.product_bacon
    ),
    Product(
        id = 26,
        name = "Carne",
        icon = R.drawable.product_meat
    ),
    Product(
        id = 27,
        name = "Pesce",
        icon = R.drawable.product_fish
    ),
    Product(
        id = 28,
        name = "Pollo",
        icon = R.drawable.product_turkey
    ),
)


val virginProducts = listOf(
    /**
     *
     * Frutta e verdura
     *
     */
    Product(
        id = 1,
        name = "Mela",
        icon = R.drawable.product_apple,
        description = "1"
    ),
    Product(
        id = 2,
        name = "Pera",
        icon = R.drawable.product_pear,
        description = "1"
    ),
    Product(
        id = 3,
        name = "Arancia",
        icon = R.drawable.product_orange,
        description = "1"
    ),
    Product(
        id = 4,
        name = "Aglio",
        icon = R.drawable.product_garlic,
        description = "1"
    ),
    Product(
        id = 5,
        name = "Banane",
        icon = R.drawable.product_banana,
        description = "1"
    ),
    Product(
        id = 6,
        name = "Limone",
        icon = R.drawable.product_lemon,description = "1"
    ),
    Product(
        id = 7,
        name = "Cipolla",
        icon = R.drawable.product_onion,description = "1"
    ),
    Product(
        id = 8,
        name = "Carote",
        icon = R.drawable.product_carrot,description = "1"
    ),
    Product(
        id = 9,
        name = "Zenzero",
        icon = R.drawable.product_ginger,description = "1"
    ),
    Product(
        id = 10,
        name = "Insalata",
        icon = R.drawable.product_salad,description = "1"
    ),
    Product(
        id = 11,
        name = "Fragole",
        icon = R.drawable.product_strawberry,description = "1"
    ),
    Product(
        id = 12,
        name = "Zucca",
        icon = R.drawable.product_pumpkin,description = "1"
    ),
    Product(
        id = 13,
        name = "Albicocche",
        icon = R.drawable.product_apricot,description = "1"
    ),
    Product(
        id = 14,
        name = "Funghi",
        icon = R.drawable.product_mushrooms,description = "1"
    ),
    Product(
        id = 15,
        name = "Pomodori",
        icon = R.drawable.product_tomato,description = "1"
    ),
    /**
     *
     * Panetteria
     *
     */

    Product(
        id = 16,
        name = "Toast",
        icon = R.drawable.product_toast,description = "1"
    ),
    Product(
        id = 17,
        name = "Pane",
        icon = R.drawable.product_bread,description = "1"
    ),
    Product(
        id = 18,
        name = "Cornetti",
        icon = R.drawable.product_croissant,description = "1"
    ),
    /**
     *
     * Latte e formaggi
     *
     */
    Product(
        id = 19,
        name = "Latte",
        icon = R.drawable.product_milk,description = "1"
    ),
    Product(
        id = 20,
        name = "Uova",
        icon = R.drawable.product_egg,description = "1"
    ),
    Product(
        id = 21,
        name = "Mozzarella",
        icon = R.drawable.product_mozzarella,description = "1"
    ),
    Product(
        id = 22,
        name = "Formaggio",
        icon = R.drawable.product_cheese,description = "1"
    ),
    Product(
        id = 23,
        name = "Burro",
        icon = R.drawable.product_butter,description = "1"
    ),
    /**
     *
     * Carne e pesce
     *
     */
    Product(
        id = 24,
        name = "Acciughe",
        icon = R.drawable.product_anchovy,description = "1"
    ),
    Product(
        id = 25,
        name = "Pancetta",
        icon = R.drawable.product_bacon,description = "1"
    ),
    Product(
        id = 26,
        name = "Carne",
        icon = R.drawable.product_meat,description = "1"
    ),
    Product(
        id = 27,
        name = "Pesce",
        icon = R.drawable.product_fish,description = "1"
    ),
    Product(
        id = 28,
        name = "Pollo",
        icon = R.drawable.product_turkey,description = "1"
    ),
)