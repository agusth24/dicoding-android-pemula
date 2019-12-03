package com.example.prototype.myunittestingmvvmmade

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mainViewModel: MainViewModel

    private lateinit var edtWidth: EditText
    private lateinit var edtHeight: EditText
    private lateinit var edtLength: EditText
    private lateinit var tvResult: TextView
    private lateinit var btnCalVolume: Button
    private lateinit var btnCalSurfaceArea: Button
    private lateinit var btnCalCircumreference: Button
    private lateinit var btnSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = MainViewModel(CuboidModel())

        edtWidth = findViewById(R.id.edt_width)
        edtHeight = findViewById(R.id.edt_height)
        edtLength = findViewById(R.id.edt_length)
        tvResult = findViewById(R.id.tv_result)
        btnCalVolume = findViewById(R.id.btn_cal_volume)
        btnCalSurfaceArea = findViewById(R.id.btn_cal_surface_area)
        btnCalCircumreference = findViewById(R.id.btn_cal_circumference)
        btnSave = findViewById(R.id.btn_save)

        btnSave.setOnClickListener(this)
        btnCalCircumreference.setOnClickListener(this)
        btnCalSurfaceArea.setOnClickListener(this)
        btnCalVolume.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        val length = edtLength.text.toString().trim()
        val width = edtWidth.text.toString().trim()
        val height = edtHeight.text.toString().trim()

        when {
            length.isEmpty() -> edtLength.error = "Field length is required"
            width.isEmpty() -> edtWidth.error = "Field width is required"
            height.isEmpty() -> edtHeight.error = "Filed height is required"
            else -> {
                val l = length.toDouble()
                val h = height.toDouble()
                val w = width.toDouble()

                when {
                    v.id == R.id.btn_save -> {
                        mainViewModel.save(l,w,h)
                        visible()
                    }
                    v.id == R.id.btn_cal_circumference -> {
                        tvResult.text = mainViewModel.gerCircumreference().toString()
                        gone()
                    }
                    v.id == R.id.btn_cal_surface_area -> {
                        tvResult.text = mainViewModel.getSurfaceArea().toString()
                        gone()
                    }
                    v.id == R.id.btn_cal_volume -> {
                        tvResult.text = mainViewModel.getVolume().toString()
                        gone()
                    }
                }
            }

        }
    }

    private fun visible() {
        btnCalVolume.visibility = View.VISIBLE
        btnCalSurfaceArea.visibility = View.VISIBLE
        btnCalCircumreference.visibility = View.VISIBLE
        btnSave.visibility = View.GONE
    }

    private fun gone() {
        btnCalVolume.visibility = View.GONE
        btnCalSurfaceArea.visibility = View.GONE
        btnCalCircumreference.visibility = View.GONE
        btnSave.visibility = View.VISIBLE
    }
}
