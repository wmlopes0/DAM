package com.example.t08ejercicio05

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var listViewPeliculas: ListView
    private lateinit var webViewPeliculas: WebView
    private lateinit var arrayListPeliculas: ArrayList<Pelicula>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Referencia de componentes de la Activity
        listViewPeliculas = findViewById(R.id.listViewPeliculas)
        webViewPeliculas = findViewById(R.id.webViewPeliculas)

        //ARRAYLIST
        arrayListPeliculas = arrayListOf()
        arrayListPeliculas.add(
            Pelicula(R.drawable.superman, resources.getString(R.string.superman_text), "https://www.filmaffinity.com/es/film730631.html")
        )
        arrayListPeliculas.add(
            Pelicula(R.drawable.batman, resources.getString(R.string.batman_text), "https://www.filmaffinity.com/es/film943383.html")
        )
        arrayListPeliculas.add(
            Pelicula(R.drawable.capitanamerico, resources.getString(R.string.capitan_americo_text), "https://www.filmaffinity.com/es/film942015.html")
        )
        arrayListPeliculas.add(
            Pelicula(R.drawable.hulk, resources.getString(R.string.hulk_text), "https://www.filmaffinity.com/es/film182008.html")
        )
        arrayListPeliculas.add(
            Pelicula(R.drawable.ironman, resources.getString(R.string.ironman_text), "https://www.filmaffinity.com/es/film201496.html")
        )
        arrayListPeliculas.add(
            Pelicula(R.drawable.spiderman, resources.getString(R.string.spiderman_text), "https://www.filmaffinity.com/es/film218379.html")
        )

        //ADAPTADOR
        val baseAdapter = object : BaseAdapter() {
            override fun getCount(): Int {
                return arrayListPeliculas.size
            }

            override fun getItem(position: Int): Any {
                return arrayListPeliculas.get(position)
            }

            override fun getItemId(position: Int): Long {
                return position.toLong()
            }

            override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
                //INFLAR LA VISTA (si convertView no es null, utilizamos convertView)
                val view: View
                if (convertView == null) {
                    view = layoutInflater.inflate(R.layout.listviewpersonalizado, parent, false)
                } else {
                    view = convertView
                }

                //CAPTURAR LOS ELEMENTOS LIST ITEM
                val imageViewPeliculas: ImageView =
                    view.findViewById<ImageView>(R.id.imageViewPeliculas)
                val textViewTitulo: TextView = view.findViewById<TextView>(R.id.textViewTitulo)

                //ACTUALIZAR LOS ELEMENTOS LIST ITEM
                val pelicula: Pelicula = arrayListPeliculas.get(position)
                imageViewPeliculas.setImageResource(pelicula.getImagen())
                textViewTitulo.setText(pelicula.getTitulo())

                return view
            }
        }

        //Añadir el adaptador al ListView
        listViewPeliculas.adapter = baseAdapter

        //setOnItemClickListener
        listViewPeliculas.setOnItemClickListener { parent, view, position, id ->
            val peliculaSeleccionada: Pelicula = arrayListPeliculas.get(position)

            //Para que se carge en el webView, no en un navegador aparte
            webViewPeliculas.webViewClient = WebViewClient()
            webViewPeliculas.loadUrl(peliculaSeleccionada.getDireccionWeb())
        }


    }


}