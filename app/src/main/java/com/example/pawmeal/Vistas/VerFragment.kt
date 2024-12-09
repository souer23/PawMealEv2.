package com.example.pawmeal.Vistas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pawmeal.Adapter.AdapterMascota
import com.example.pawmeal.Models.Mascota
import com.example.pawmeal.databinding.FragmentVerBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class VerFragment : Fragment() {

    // Inicializar el binding
    private var _binding: FragmentVerBinding? = null
    private val binding get() = _binding!!

    // Firebase Database Reference
    private lateinit var database: DatabaseReference

    // Lista de mascotas
    private lateinit var mascotasList: ArrayList<Mascota>

    // Adaptador de RecyclerView
    private lateinit var adapterMascota: AdapterMascota

    // RecyclerView
    private lateinit var mascotaRecyclerView: RecyclerView

    // En el onCreateView se infla el layout del fragmento
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el layout del fragment y habilitar binding
        _binding = FragmentVerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configuración de RecyclerView
        mascotaRecyclerView = binding.rvMascotas
        mascotaRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        mascotaRecyclerView.hasFixedSize()

        // Inicializar la lista de mascotas
        mascotasList = arrayListOf()

        // Obtener las mascotas desde Firebase
        getMascotas()
    }

    private fun getMascotas() {
        // Iniciar la base de datos
        database = FirebaseDatabase.getInstance().getReference("Mascotas")

        // Obtener los datos desde Firebase
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (mascotasSnapshot in snapshot.children) {
                        val mascota = mascotasSnapshot.getValue(Mascota::class.java)
                        mascotasList.add(mascota!!)
                    }
                    adapterMascota = AdapterMascota(mascotasList)
                    mascotaRecyclerView.adapter = adapterMascota
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Puedes manejar el error aquí
            }
        })
    }

    // Se asegura de limpiar el binding cuando el fragmento sea destruido
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

