package com.target.targetcasestudy.data

import java.util.*

data class DealItem(
        var id: Int,
        var title: String,
        var description: String,
        var price: String,
        var aisle: String,
        var regular_price: Price,
        var image_url: String
) {

    fun dealTitle(): String {
        return capitalize(title)
    }
}
data class Price(
        var amount_in_cents: Int,
        var currency_symbol: String,
        var display_string: String
)

private fun capitalize(capString: String): String {
   return capString.split(' ').joinToString(" ") { it.capitalize(Locale.ROOT) }
}
