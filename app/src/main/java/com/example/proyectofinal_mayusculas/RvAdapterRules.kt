package com.example.proyectofinal_mayusculas

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinal_mayusculas.databinding.ItemReglasBinding

class RvAdapterRules(var todos: List<Todorules>): RecyclerView.Adapter<RvAdapterRules.ViewHolder>() {
    class ViewHolder(val binding: ItemReglasBinding): RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemReglasBinding.inflate(layoutInflater,parent,false)
        return ViewHolder(binding)
    }

    // Aquí es donde se debe de actualizar el adaptador (enlaza datos con elementos del layout)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            textViewIDRules.text= todos[position].id
            textViewTextRules.text= todos[position].regla
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }
}