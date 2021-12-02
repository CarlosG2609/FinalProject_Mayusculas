package com.example.proyectofinal_mayusculas

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinal_mayusculas.databinding.ItemPuntostiempoBinding
import com.example.proyectofinal_mayusculas.databinding.ItemTablapuntuacionBinding

class RvAdapterTiempo(var todos: List<Usuario>): RecyclerView.Adapter<RvAdapterTiempo.ViewHolder>() {
    class ViewHolder(val binding: ItemPuntostiempoBinding): RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemPuntostiempoBinding.inflate(layoutInflater,parent,false)
        return ViewHolder(binding)
    }

    // Aquí es donde se debe de actualizar el adaptador (enlaza datos con elementos del layout)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            textViewNumber.text= (position+1).toString()
            textViewUser.text= todos[position].nombre
            textViewTiempo.text= todos[position].fecha
            textViewScore.text= todos[position].calif.toString()
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }
}