package com.example.fitnesstracker

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var rvMain: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainItems = mutableListOf<MainItem>()

        mainItems.add(
            MainItem(
                id = 1,
                drawableId = R.drawable.baseline_food_bank_24,
                textStringId = R.string.imc,
                color = Color.GREEN
            )
        )

        mainItems.add(
            MainItem(
                id = 2,
                drawableId = R.drawable.baseline_bar_chart_24,
                textStringId = R.string.tmb,
                color = Color.CYAN
            )
        )

        rvMain = findViewById(R.id.rv_main)
        rvMain.layoutManager = GridLayoutManager(this, 2)
        val adapter = MainAdapter(mainItems) { id ->
            when (id) {
                1 -> {
                    val intent = Intent(this@MainActivity, ImcActivity::class.java)
                    startActivity(intent)
                }

                2 -> {
                    val intent = Intent(this@MainActivity, TmbActivity::class.java)
                    startActivity(intent)
                }
            }

        }
        rvMain.adapter = adapter


    }

    // classe para administrar a recyclerView e suas celular(os seus layouts de itens)
    private inner class MainAdapter(
        private val mainItems: List<MainItem>,
        private val onItemClickListener: (Int) -> Unit,
    ) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

        // Método que iforma qual é o layout XML da celula especifica (item)
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
            val view = layoutInflater.inflate(R.layout.main_item, parent, false)
            return MainViewHolder(view)
        }

        // Informa a quantidade de celulas que vai ter
        override fun getItemCount(): Int {
            return mainItems.size
        }

        // disparado sempre que houver uma rolagem e trocar o conteudo da celula
        override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
            val itemCurrent = mainItems[position]
            holder.bind(itemCurrent)
        }

        // É a classe da célula em si
        private inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            fun bind(itemCurrent: MainItem) {
                val img: ImageView = itemView.findViewById(R.id.item_img_icon)
                val name: TextView = itemView.findViewById(R.id.item_txt_name)
                val container: LinearLayout = itemView.findViewById(R.id.item_container_imc)

                img.setImageResource(itemCurrent.drawableId)
                name.setText(itemCurrent.textStringId)
                container.setBackgroundColor(itemCurrent.color)

                container.setOnClickListener {
                    onItemClickListener.invoke(itemCurrent.id)
                }

            }

        }

    }

}