package com.target.targetcasestudy.data

import java.util.*

data class DealItem(
        var id: Int,
        var title: String,
        var description: String,
        var price: String,
        var aisle: String,
        var regular_price: RegularPrice,
        var sale_price: SalePrice?,
        var image_url: String
) {

    fun dealTitle(): String {
        return capitalize(title)
    }

    fun salePrice(): String {
        return if (sale_price?.display_string?.isBlank() == false) {
            sale_price?.display_string.toString()
        } else {
            regular_price.display_string
        }
    }

    fun isSalePriceSame(): Boolean {
        return sale_price?.display_string?.isBlank() == false
    }
}
data class RegularPrice(
        var amount_in_cents: Int,
        var currency_symbol: String,
        var display_string: String
)
data class SalePrice(
        var amount_in_cents: Int,
        var currency_symbol: String,
        var display_string: String = ""
)

private fun capitalize(capString: String): String {
   return capString.split(' ').joinToString(" ") { it.capitalize(Locale.ROOT) }
}
