package com.example.myapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxTextView
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    lateinit var textView            : TextView
    lateinit var editText            : EditText
    lateinit var button              : Button
    lateinit var rvButton            : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        openRecycleViewActivity()
        updateTextWithDebounceAndFilter_Input()
        updateTextWithDebounceFunction_Button()
    }

    private fun openRecycleViewActivity() {

        rvButton = findViewById(R.id.rvButton) as Button

        val subscribe = RxView.clicks(rvButton).subscribe {

            val intent = Intent(this, RecyclerList::class.java)
            startActivity(intent)
        }
    }



    fun updateTextWithDebounceAndFilter_Input(){

        textView = findViewById(R.id.textViewItem) as TextView
        editText = findViewById(R.id.editText) as EditText

        val disposable = RxTextView
            .textChanges(editText)
            .filter { it.length > 3 }
            .debounce(500, TimeUnit.MILLISECONDS)
            .subscribe { textView.text = it }
    }



    fun updateTextWithDebounceFunction_Button() {

        textView = findViewById(R.id.textViewItem) as TextView
        button = findViewById(R.id.button) as Button

        val disposable = RxView

            .clicks(button)
            .debounce(1, TimeUnit.SECONDS)
            .map { 1 }
            .scan(0) { acc , next -> acc + next }
            .subscribe {
                textView.text = it.toString()
            }
    }


    fun updateTextWithThrottleFunction_Button() {

        textView = findViewById(R.id.textViewItem) as TextView
        button = findViewById(R.id.button) as Button

        val disposable = RxView

            .clicks(button)
            .throttleFirst(500, TimeUnit.MILLISECONDS)
            .map { 1 }
            .scan(0) { acc , next -> acc + next }
            .subscribe {
                textView.text = it.toString()
            }
    }


    fun updateTextWithScanFunction_Button() {

        textView = findViewById(R.id.textViewItem) as TextView
        button = findViewById(R.id.button) as Button

        val disposable = RxView.clicks(button)

            .map { 1 }
            .scan(0) { acc , next -> acc + next }
            .subscribe {
                textView.text = it.toString()
            }
    }


    fun updateTextWithInput() {

        textView = findViewById(R.id.textViewItem) as TextView
        editText = findViewById(R.id.editText) as EditText

        val disposable = RxTextView
            .textChanges(editText)
            .subscribe { textView.text = it }
    }


    fun updateTextWithDebounce_Input() {

        textView = findViewById(R.id.textViewItem) as TextView
        editText = findViewById(R.id.editText) as EditText

        val disposable = RxTextView
            .textChanges(editText)
            .debounce(300, TimeUnit.MILLISECONDS)
            .subscribe { textView.text = it }
    }


}
