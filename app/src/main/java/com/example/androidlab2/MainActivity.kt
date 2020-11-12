package com.example.androidlab2
import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.Toast
import android.content.DialogInterface
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun calculate(v: View){
        var homevalue = if(homevalueinput.text.isNotEmpty()){homevalueinput.text.toString().toDouble()}else{0.00}
        var downpayment = if(downpayinput.text.isNotEmpty()){downpayinput.text.toString().toDouble()}else{0.00}
        var APR = if(aprinput.text.isNotEmpty()){aprinput.text.toString().toDouble()}else{0.00}
        if (APR == 0.00){
            val alertDialog = AlertDialog.Builder(this).create()
            alertDialog.setTitle("ALERT")
            alertDialog.setMessage("YOU MUST HAVE INTEREST RATE")
            alertDialog.setButton(
                AlertDialog.BUTTON_POSITIVE,
                "OK"
            ) { dialog, which ->
                Toast.makeText(
                    applicationContext,
                    "APR can't be 0%",
                    Toast.LENGTH_SHORT
                ).show()
            }
            alertDialog.show()
        }
        var aprmonth = APR/100/12
        var terms = if(termsinput.text.isNotEmpty()){termsinput.text.toString().toDouble()}else{0.00}
        var taxrate = if(taxrateinput.text.isNotEmpty()){taxrateinput.text.toString().toDouble()}else{0.00}
        var principal = homevalue-downpayment



        var monthlyterm = terms*12
        var totalttaxpaid = homevalue*taxrate/100*terms
        var calc = Math.pow(1+aprmonth, monthlyterm)

        var montlypayment = ((principal * aprmonth * calc)/(calc-1)) + (homevalue*taxrate/100)/12
        var totaltmonthpay = montlypayment*monthlyterm
        var totatinterest = totaltmonthpay-homevalue

        val months= arrayOf("Januar", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December")

        val cal = Calendar.getInstance()
        cal.add(Calendar.YEAR, terms.toInt())
        val year = cal.time.year
        val month = cal.getTime().month

        totaltaxpaidoutput.text = String.format("%.2f", totalttaxpaid)
        monthlypaymentoutput.text = String.format("%.2f", montlypayment)
        totalinterespaidoutput.text = String.format("%.2f", totatinterest)
        payoffdateoutput.text = months[month-1] +" "+ (year+1900).toString()

    }

    fun reset(v: View) {
        homevalueinput.text.clear()
        downpayinput.text.clear()
        aprinput.text.clear()
        taxrateinput.text.clear()
        termsinput.text.clear()

        totaltaxpaidoutput.text = ""
        monthlypaymentoutput.text = ""
        totalinterespaidoutput.text = ""
        payoffdateoutput.text = ""
    }
}
