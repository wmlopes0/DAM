package com.example.t08ejercicio04

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView

class MainActivity : AppCompatActivity(), AdapterView.OnItemClickListener {

    private lateinit var listViewSeries: ListView
    private lateinit var resultado: TextView
    private lateinit var arrayListSeries: ArrayList<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Recupero los componentes necesarios
        listViewSeries = findViewById(R.id.listView)
        resultado = findViewById(R.id.resultado)
        //ArrayList de series
        arrayListSeries =
            arrayListOf("Hermanos de sangre", "Breaking bad", "Friends", "Patria", "El patrón del mal")
        //Adaptador
        val adaptador: ArrayAdapter<String> =
            ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, arrayListSeries)
        listViewSeries.adapter = adaptador
        listViewSeries.onItemClickListener = this
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val msgResultado:String = "${getString(R.string.seleccionado)} ${arrayListSeries.get(position)}"
        resultado.setText(msgResultado)
    }
}