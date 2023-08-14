package com.example.temperatureconverter

import android.R
import android.accessibilityservice.GestureDescription
import android.content.DialogInterface
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import com.example.temperatureconverter.databinding.ActivityMainBinding
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var selectedUnit: String

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val df = DecimalFormat("#.##")//Decimal formatter
        selectedUnit = "Fahrenheit"

        binding.selectType.setOnClickListener() {
            showAlertDialog()
        }


        binding.editInput.addTextChangedListener() {
            val resultText: String
            var inputVal = binding.editInput.text.toString()
            if (inputVal.isNotEmpty()) {
                var doubleInput = inputVal.toDouble()
                if(selectedUnit == "Fahrenheit"){
                    resultText = df.format((doubleInput - 32) * 5 / 9)
                    binding.textResultType.text = "Celsius"
                }else{
                    //(0°C × 9/5) + 32
                    resultText = df.format((doubleInput *9/5)+32)
                    binding.textResultType.text = "Fahrenheit"
                }

                binding.textResult.text = resultText
            }

        }





    }


    @RequiresApi(Build.VERSION_CODES.N)
    private fun showAlertDialog() {
        val alertDialog: android.app.AlertDialog.Builder = android.app.AlertDialog.Builder(this@MainActivity)
        alertDialog.setTitle("Select Unit") //Setting title for alertBox
        val items = arrayOf("Fahrenheit", "Celsius")
        val checkedItem = -1
        alertDialog.setSingleChoiceItems(items, checkedItem,
            DialogInterface.OnClickListener { dialog, which ->
                selectedUnit = items[which]
                binding.textType.setText(items[which])
            })
        val positiveButton = alertDialog.setPositiveButton(
            R.string.ok,
            DialogInterface.OnClickListener { dialog, which ->
                dialog.dismiss()
            })
        val alert: android.app.AlertDialog? = alertDialog.create()
        if (alert != null) {
            alert.setCanceledOnTouchOutside(false)
        }
        if (alert != null) {
            alert.show()
        }
    }
}


