package com.target.targetcasestudy.ui.payment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.target.targetcasestudy.R
import com.target.targetcasestudy.data.validateCreditCard
import com.target.targetcasestudy.databinding.DialogPaymentBinding


/**
 * Dialog that displays a minimal credit card entry form.
 *
 * Your task here is to enable the "submit" button when the credit card number is valid and
 * disable the button when the credit card number is not valid.
 *
 * You do not need to input any of your actual credit card information. See `Validators.kt` for
 * info to help you get fake credit card numbers.
 *
 * You do not need to make any changes to the layout of this screen (though you are welcome to do
 * so if you wish).
 */
class PaymentDialogFragment : DialogFragment() {

  private lateinit var binding:DialogPaymentBinding
  private lateinit var viewModel: PaymentDialogViewModel

  override fun onCreateView(
          inflater: LayoutInflater,
          container: ViewGroup?,
          savedInstanceState: Bundle?
  ): View {
    binding = DialogPaymentBinding.inflate(inflater, container, false)
    viewModel = ViewModelProvider(this).get(PaymentDialogViewModel::class.java)
    binding.viewModel = viewModel

    setObservers()
    return binding.root
  }

  override fun onResume() {
    super.onResume()
    creditCardEditBoxListener()
  }

  //Listener for edit text = credit card
  private fun creditCardEditBoxListener() {
    binding.cardNumber.addTextChangedListener(object : TextWatcher {
      override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
      }

      override fun beforeTextChanged(s: CharSequence, start: Int, count: Int,
                                     after: Int) {
        // TODO Auto-generated method stub
      }

      override fun afterTextChanged(creditCardNumber: Editable) {
        // enabled and disabled the submit button based on credit card number validation
        binding.submit.isEnabled = validateCreditCard(creditCardNumber.toString())
      }
    })
  }

  private fun setObservers() {
    viewModel.eventCancel.observe(viewLifecycleOwner, { isClicked ->
      if (isClicked) {
        dismiss()
        viewModel.onCancelClickComplete()
      }
    })
    viewModel.eventSubmit.observe(viewLifecycleOwner, { isClicked ->
      if (isClicked) {
        if (binding.submit.isEnabled){
          Toast.makeText(context,getString(R.string.valid_message), Toast.LENGTH_LONG).show()
        }
        dismiss()
        //call your methods on submit click
        viewModel.onSubmitClickComplete()
      }
    })
  }

}