package hoods.com.jetexpense.core.utils

import androidx.compose.ui.graphics.Color
import hoods.com.jetexpense.R
import hoods.com.jetexpense.core.theme.businessBg
import hoods.com.jetexpense.core.theme.clothBg
import hoods.com.jetexpense.core.theme.electricBg
import hoods.com.jetexpense.core.theme.food_drink
import hoods.com.jetexpense.core.theme.gadgetBg
import hoods.com.jetexpense.core.theme.giftBg
import hoods.com.jetexpense.core.theme.groceryBg
import hoods.com.jetexpense.core.theme.healthBg
import hoods.com.jetexpense.core.theme.homeBg
import hoods.com.jetexpense.core.theme.leisureBg
import hoods.com.jetexpense.core.theme.miscBg
import hoods.com.jetexpense.core.theme.petBg
import hoods.com.jetexpense.core.theme.schBg
import hoods.com.jetexpense.core.theme.snackBg
import hoods.com.jetexpense.core.theme.subBg
import hoods.com.jetexpense.core.theme.taxiBg
import hoods.com.jetexpense.core.theme.travelBg
import hoods.com.jetexpense.core.theme.vehicleBg


enum class Category(
    val title: String,
    val iconRes: Int,
    val bgRes: Color,
    val colorRes: Color = Color.White,
) {
    FOOD_DRINK("Food & Drink", R.drawable.drink, food_drink, Color.Black),
    CLOTHING("Clothing", R.drawable.clothing, clothBg, Color.Black),
    HOME("Home", R.drawable.home, homeBg, Color.Black),
    HEALTH("Health", R.drawable.health, healthBg),
    SCHOOL("School", R.drawable.school, schBg),
    GROCERY("Grocery", R.drawable.grocery, groceryBg, Color.Black),
    ELECTRICITY("Electricity", R.drawable.electricity, electricBg, Color.Black),
    BUSINESS("Business", R.drawable.business, businessBg, Color.Black),
    VEHICLE("Vehicle", R.drawable.vehicle, vehicleBg),
    TAXI("Taxi", R.drawable.taxi, taxiBg),
    LEISURE("Leisure", R.drawable.leisure, leisureBg, Color.Black),
    GADGET("Gadget", R.drawable.gadget, gadgetBg),
    TRAVEL("Travel", R.drawable.travel, travelBg, Color.Black),
    SUBSCRIPTION("Subscription", R.drawable.sub, subBg),
    PET("Pet", R.drawable.pet, petBg, Color.Black),
    SNACK("Snack", R.drawable.snack, snackBg, Color.Black),
    GIFT("Gift", R.drawable.gift, giftBg, Color.Black),
    MISC("Miscellaneous", R.drawable.misc, miscBg)
}
