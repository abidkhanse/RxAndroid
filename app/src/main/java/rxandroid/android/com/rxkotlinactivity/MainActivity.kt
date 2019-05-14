package rxandroid.android.com.rxkotlinactivity

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.functions.Consumer
import org.w3c.dom.Text
import android.R.attr.button
import android.R.attr.text
import android.support.design.widget.TextInputEditText
import android.widget.EditText
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit


class MainActivity : Activity() {

    lateinit var textView            : TextView
    lateinit var editText            : EditText
    lateinit var button              : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        //updateTextWithInput()
        //updateTextWithButton()
        //updateTextWithDebounce_Button()
        //updateTextWithDebounce_Input()
        // updateTextWithDebounceAndFilter_Input()
         updateTextInReverseOrder()


    }

    fun updateTextInReverseOrder() {

        textView = findViewById(R.id.textView)
        editText = findViewById(R.id.editText)

        val disposable = RxTextView
            .textChanges(editText)
            .map{ it.toString().reversed()}
            .subscribe { textView.text = it }
    }


    fun updateTextWithDebounceAndFilter_Input(){

        textView = findViewById(R.id.textView)
        editText = findViewById(R.id.editText)

        val disposable = RxTextView
            .textChanges(editText)
            .filter { it.length > 3 }
            .debounce(500, TimeUnit.MILLISECONDS)
            .subscribe { textView.text = it }
    }



    fun updateTextWithDebounce_Button() {

        textView = findViewById(R.id.textView)
        button = findViewById(R.id.button)

        val disposable = RxView

            .clicks(button)
            .debounce(1, TimeUnit.SECONDS)
            .map { 1 }
            .scan(0) { acc , next -> acc + next }
            .subscribe {
                textView.text = it.toString()
            }
    }


    fun updateTextWithThrottle_Button() {

        textView = findViewById(R.id.textView)
        button = findViewById(R.id.button)

        val disposable = RxView

            .clicks(button)
            .throttleFirst(500, TimeUnit.MILLISECONDS)
            .map { 1 }
            .scan(0) { acc , next -> acc + next }
            .subscribe {
                textView.text = it.toString()
            }
    }


    fun updateTextWithScan_Button() {

        textView = findViewById(R.id.textView)
        button = findViewById(R.id.button)

        val disposable = RxView.clicks(button)

            .map { 1 }
            .scan(0) { acc , next -> acc + next }
            .subscribe {
                textView.text = it.toString()

            }
    }


    fun updateTextWithInput() {

        textView = findViewById(R.id.textView)
        editText = findViewById(R.id.editText)

        val disposable = RxTextView
            .textChanges(editText)
            .subscribe { textView.text = it }
    }


    fun updateTextWithDebounce_Input(){

        textView = findViewById(R.id.textView)
        editText = findViewById(R.id.editText)

        val disposable = RxTextView
            .textChanges(editText)
            .debounce(300, TimeUnit.MILLISECONDS)
            .subscribe { textView.text = it }
    }

}
