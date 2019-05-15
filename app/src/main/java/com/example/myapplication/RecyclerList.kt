package com.example.myapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxTextView
import com.jakewharton.rxbinding3.widget.afterTextChangeEvents

class RecyclerList : AppCompatActivity() {

    lateinit var filterEditText       : EditText
    lateinit var recyclerView   : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.recycler_view_list)

        filterEditText = findViewById(R.id.editTextFilter) as EditText

        recyclerView = findViewById(R.id.rvID) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        recyclerView.adapter = MyAdapter(getDataSet())

    }





    inner class MyAdapter(_list : List<String> ) : RecyclerView.Adapter<Holder>() {

        private val list = _list

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {

            return Holder(layoutInflater.inflate(R.layout.recycle_view_holder, parent, false))
        }

        override fun getItemCount(): Int {
            return list.size
        }

        override fun onBindViewHolder(holder: Holder, position: Int) {
            holder.bind(list[position])
        }
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val rvText = itemView.findViewById(R.id.textView2) as TextView

        fun bind(str: String) {

            rvText.text = str
        }
    }


    fun getDataSet(): ArrayList<String> {

        val dataSet = ArrayList<String>()

        dataSet.add("one")
        dataSet.add("two")
        dataSet.add("three")
        dataSet.add("four")
        dataSet.add("five")
        dataSet.add("eleven")
        dataSet.add("twelve")
        dataSet.add("thirteen")
        dataSet.add("fourteen")
        dataSet.add("fifteen")
        dataSet.add("twenty one")
        dataSet.add("twenty two")
        dataSet.add("thirty one")
        dataSet.add("thirty two")
        dataSet.add("forty one")
        dataSet.add("forty two")
        dataSet.add("one hundred")

        return dataSet
    }

}
