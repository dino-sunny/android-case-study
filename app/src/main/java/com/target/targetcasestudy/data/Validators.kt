package com.target.targetcasestudy.data

import androidx.core.text.isDigitsOnly

/**
 * For an explanation of how to validate credit card numbers read:
 *
 * https://www.freeformatter.com/credit-card-number-generator-validator.html#fakeNumbers
 *
 * This contains a breakdown of how this algorithm should work as
 * well as a way to generate fake credit card numbers for testing
 *
 * The structure and signature of this is open to modification, however
 * it *must* include a method, field, etc that returns a [Boolean]
 * indicating if the input is valid or not
 *
 * Additional notes:
 *  * This method does not need to validate the credit card issuer
 *  * This method must validate card number length (13 - 19 digits), but does not
 *    need to validate the length based on the issuer.
 *
 * @param creditCardNumber - credit card number of (13, 19) digits
 * @return true if a credit card number is believed to be valid,
 * otherwise false
 */
fun validateCreditCard(creditCardNumber: String): Boolean {
  //creating a new array with credit card numbers
  if (creditCardNumber.isNotEmpty()
          && creditCardNumber.length>= 13 && creditCardNumber.length<=19 && isNumber(creditCardNumber)) {
    val cardNumbers: MutableList<String> = ArrayList()
    for (index in creditCardNumber.indices) {
      cardNumbers.add(creditCardNumber[index].toString())
    }
    val arraySize = cardNumbers.size
    var sum = 0
    for (i in arraySize - 2 downTo 0) {
      var currentSum = cardNumbers[i].toInt()
      val isOdd = (arraySize - 1 - i) % 2
      if (isOdd != 0) {
        currentSum *= 2
      }
      if (currentSum > 9) {
        currentSum -= 9
      }
      sum += currentSum
    }
    return (sum + cardNumbers[arraySize - 1].toInt()) % 10 == 0
  }
  return false
}

//check a given string is numeric
fun isNumber(str: String) = try { str.toDouble()
  true
} catch (e: NumberFormatException) {
  false
}