package com.example.t09practicaentregable.ui.listarLibros

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.t09practicaentregable.R
import com.example.t09practicaentregable.model.Libro
import com.squareup.picasso.Picasso

class LibroAdapter(private val listaLibros: List<Libro>) :
    RecyclerView.Adapter<LibroAdapter.LibroViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibroViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_libro, parent, false)
        return LibroViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: LibroViewHolder, position: Int) {
        //Recupero el libro de la posición actual
        val libroActual = listaLibros[position]
        // Configura los Views del holder
        holder.textViewTitulo.text = libroActual.titulo
        holder.textViewAutor.text = libroActual.autor
        holder.textViewPrecio.text = libroActual.precio +"€"
        //Utilizo la libreria Piccaso para mostrar la imagen por la URL
        Picasso.get()
            .load(libroActual.imagen)//Cargamos la url de la imagen del campo imagen de nuestro objeto Libro
            .placeholder(R.drawable.libro) // Imagen de placeholder
            .error(R.drawable.libro) // Imagen de error
            .into(holder.imageViewLibro) //Asigno el imageView
    }

    override fun getItemCount() = listaLibros.size

    class LibroViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //Defino los Views
        val imageViewLibro: ImageView = itemView.findViewById(R.id.imageViewLibro)
        val textViewTitulo: TextView = itemView.findViewById(R.id.textViewTitulo)
        val textViewAutor: TextView = itemView.findViewById(R.id.textViewAutor)
        val textViewPrecio: TextView = itemView.findViewById(R.id.textViewPrecio)
    }
}