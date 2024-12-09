package com.example.pawmeal.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pawmeal.Models.Mascota
import com.example.pawmeal.R
import com.example.pawmeal.Vistas.AdminMascotaActivity

class AdapterMascota(private var mascotas: ArrayList<Mascota>) :
    RecyclerView.Adapter<AdapterMascota.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombre: TextView = itemView.findViewById(R.id.tvNombreMascota)
        val sexo: TextView = itemView.findViewById(R.id.tvSexoMascota)
        val raza: TextView = itemView.findViewById(R.id.tvRazaMascota)
        val btnAdministrar: Button = itemView.findViewById(R.id.btnAdministrar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_productos, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mascota = mascotas[position]

        holder.nombre.text = mascota.nombre
        holder.sexo.text = mascota.sexo
        holder.raza.text = mascota.raza

        // Configurar el botón "Administrar"
        holder.btnAdministrar.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, AdminMascotaActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return mascotas.size
    }
}

