package com.example.md_lab04__recyclerview_getrequests

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val recyclerView: RecyclerView = findViewById(R.id.rView)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = Adapter(this, fetchList())
    }

    private fun fetchList(): ArrayList<ColorData> {
        val colorNames = listOf("Красный", "Алый", "Кораллово-красный", "Люминесцентный красный", "Кармин")
        var colorHexes = listOf("ff0000", "FF2400", "B32821", "F80000", "960018")
        val list = arrayListOf<ColorData>()

        for (i in 0..colorNames.size) {
            val model = ColorData(colorNames[i], Color.parseColor(colorHexes[i]))
            list.add(model)
        }
        return list
    }

    data class ColorData (
        val colorName: String,
        val colorHex: Int
    )

    class Adapter(private val context: Context,
                  private val list: ArrayList<ColorData>) : RecyclerView.Adapter<Adapter.ViewHolder>() {

        class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
            val colorName: TextView = view.findViewById(R.id.textViewOfRecyclerView)
            val colorHex: View = view.findViewById(R.id.viewOfRecyclerView)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(context).inflate(R.layout.rview_item,parent, false)
            return ViewHolder(view)
        }

        override fun getItemCount(): Int {
            return list.count()
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val data = list[position]
            holder.colorHex.setBackgroundColor(data.colorHex)
            holder.colorName.text = data.colorName
        }
    }
}