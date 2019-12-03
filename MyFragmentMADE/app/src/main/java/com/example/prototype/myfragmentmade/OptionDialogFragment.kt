package com.example.prototype.myfragmentmade


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.fragment_option_dialog.*

/**
 * A simple [Fragment] subclass.
 */
class OptionDialogFragment : DialogFragment(), View.OnClickListener {
    private lateinit var btnChoose: Button
    private lateinit var btnClose: Button
    private lateinit var rgOptions: RadioGroup
    private lateinit var rb_ml: RadioButton
    private lateinit var rb_ac: RadioButton
    private lateinit var rb_ma: RadioButton
    private lateinit var rb_sr: RadioButton
    private var optionDialogListener: OnOptionDialogListener? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_option_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnChoose = view.findViewById(R.id.btn_choose)
        btnChoose.setOnClickListener(this)
        btnClose = view.findViewById(R.id.btn_close)
        btnClose.setOnClickListener(this)
        rgOptions = view.findViewById(R.id.rg_options)
        rb_ml = view.findViewById(R.id.rb_ml)
        rb_ac = view.findViewById(R.id.rb_ac)
        rb_ma = view.findViewById(R.id.rb_ma)
        rb_sr = view.findViewById(R.id.rb_sr)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        val fragment = parentFragment
        if(fragment is DetailCategoryFragment) {
            val detailCategoryFragment = fragment as DetailCategoryFragment?
            this.optionDialogListener = detailCategoryFragment?.optionDialogListener
        }
    }

    override fun onDetach() {
        super.onDetach()
        this.optionDialogListener = null
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.btn_close -> dialog?.cancel()
            R.id.btn_choose -> {
                val checkedRadialButtonId = rg_options.checkedRadioButtonId
                if(checkedRadialButtonId != 1) {
                    var coach: String? = null
                    when(checkedRadialButtonId) {
                        R.id.rb_ml -> coach = rb_ml.text.toString()
                        R.id.rb_ac -> coach = rb_ac.text.toString()
                        R.id.rb_ma -> coach = rb_ma.text.toString()
                        R.id.rb_sr -> coach = rb_sr.text.toString()
                    }

                    if (optionDialogListener != null) {
                        optionDialogListener?.onOptionChosen(coach)
                    }
                    dialog?.dismiss()
                }
            }
        }
    }

    interface OnOptionDialogListener {
        fun onOptionChosen(text: String?)
    }
}

