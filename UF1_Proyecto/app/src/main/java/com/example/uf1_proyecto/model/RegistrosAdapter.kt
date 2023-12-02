package com.example.uf1_proyecto.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.uf1_proyecto.R
import java.text.NumberFormat
import java.util.Locale


class RegistrosAdapter : ListAdapter<Registro, RegistrosAdapter.RegistroViewHolder>(RegistroDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RegistroViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_registro, parent, false)
        return RegistroViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RegistroViewHolder, position: Int) {
        val registro = getItem(position)

        // Asignar valores a las vistas según los datos del modelo
        holder.mainTextView.text = registro.name
        holder.secondaryTextView.text = registro.description
        //Utilizamos numberformat para darle un formato monetario apropiado
        val format: NumberFormat = NumberFormat.getCurrencyInstance(Locale.getDefault())
        holder.amountTextView.text = format.format(registro.amount)
    }

    class RegistroViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mainTextView: TextView = itemView.findViewById(R.id.mainTextView)
        val secondaryTextView: TextView = itemView.findViewById(R.id.secondaryTextView)
        val amountTextView: TextView = itemView.findViewById(R.id.amountTextView)
    }

    private class RegistroDiffCallback : DiffUtil.ItemCallback<Registro>() {
        override fun areItemsTheSame(oldItem: Registro, newItem: Registro): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Registro, newItem: Registro): Boolean {
            return oldItem == newItem
        }
    }
}