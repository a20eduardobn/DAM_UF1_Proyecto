package com.example.uf1_proyecto.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.uf1_proyecto.R
import com.example.uf1_proyecto.model.Registro

class RegistrosAdapter(private val registros: List<Registro>) : RecyclerView.Adapter<RegistrosAdapter.RegistroViewHolder>() {

    class RegistroViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mainTextView: TextView = itemView.findViewById(R.id.mainTextView)
        val secondaryTextView: TextView = itemView.findViewById(R.id.secondaryTextView)
        val amountTextView: TextView = itemView.findViewById(R.id.amountTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RegistroViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_registro, parent, false)
        return RegistroViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RegistroViewHolder, position: Int) {
        val registro = registros[position]

        // Asignar valores a las vistas según los datos del modelo
        holder.mainTextView.text = registro.name
        holder.secondaryTextView.text = registro.description
        holder.amountTextView.text = registro.amount.toString()
    }

    override fun getItemCount(): Int {
        return registros.size
    }
}