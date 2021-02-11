package com.target.targetcasestudy

import com.target.targetcasestudy.data.isNumber
import com.target.targetcasestudy.data.validateCreditCard
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import junit.framework.Assert.assertEquals

/**
 * Feel free to make modifications to these unit tests! Remember, you have full technical control
 * over the project, so you can use any libraries and testing strategies that see fit.
 */
class CreditCardValidatorTest {
  @Test
  fun `is credit card number valid`() {
    Assert.assertTrue(
      "valid credit card number should yield true",
      validateCreditCard("4539976741512043")
    )
  }
  @Test
  fun `is string is number`() {
    Assert.assertTrue(
            "string is a number",
            isNumber("12")
    )
  }
  @Test
  fun `is string not is number`() {
    Assert.assertFalse(
            "string is not a number",
            isNumber("TA)&09")
    )
  }
}
@RunWith(Parameterized::class)
class CreditCardTest(
        private val expected: Boolean,
        private val input: String
) {

  companion object{
    @JvmStatic
    @Parameterized.Parameters(name="validateCreditCard")
    fun nameTests() = listOf(
            arrayOf(true,"4539976741512043"),
            arrayOf(true,"0000000000000"),
            //Maestro card
            arrayOf(true,"0604449361108540"),
            //Diners Club - North America
            arrayOf(true,"5470217205212764"),
            //VISA
            arrayOf(true,"4679026900646420"),
            arrayOf(false,""),
            arrayOf(false,"000000"),
            arrayOf(false,"9999999999999"),
            arrayOf(false,"testingacreditcard"),
    )
  }

  @Test
  fun test_isValidName(){
    val actual = validateCreditCard(input)
    assertEquals(expected, actual)
  }
}
