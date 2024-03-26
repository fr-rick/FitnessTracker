package com.example.fitnesstracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnesstracker.model.Calc
import java.text.SimpleDateFormat
import java.util.Locale

class ListCalcActivity : AppCompatActivity() {

    private lateinit var rvList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_calc)

        val result = mutableListOf<Calc>()
        val adapter = ListCalcAdapter(result)
        rvList = findViewById(R.id.rv_list)
        rvList.layoutManager = LinearLayoutManager(this)
        rvList.adapter = adapter


        val type = intent?.extras?.getString("type") ?: throw IllegalStateException("type not found")

        Thread{
            val app = application as App
            val dao = app.db.calcDao()
            val response = dao.getRegisterByType(type)

            runOnUiThread{
                result.addAll(response)
                adapter.notifyDataSetChanged()
            }
        }.start() 


    }

    private inner class ListCalcAdapter(
        private val listCalc: List<Calc>
    ) : RecyclerView.Adapter<ListCalcAdapter.ListCalcViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListCalcViewHolder {
            val view = layoutInflater.inflate(android.R.layout.simple_list_item_1, parent, false)
            return ListCalcViewHolder(view)
        }

        override fun getItemCount(): Int {
            return listCalc.size
        }

        override fun onBindViewHolder(holder: ListCalcViewHolder, position: Int) {
            val itemCurrent = listCalc[position]
            holder.bind(itemCurrent)
        }

        private inner class ListCalcViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            fun bind(itemCurrent: Calc) {

                val tv = itemView as TextView

                val res = itemCurrent.res
                val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale("pt", "BR"))
                val date = sdf.format(itemCurrent.createdDate)

                tv.text = getString(R.string.list_response, res, date)
            }

        }

    }
}