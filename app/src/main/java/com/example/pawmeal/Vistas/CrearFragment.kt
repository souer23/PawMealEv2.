package com.example.pawmeal.Vistas

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pawmeal.Models.Mascota
import com.example.pawmeal.databinding.FragmentCrearBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class CrearFragment : Fragment() {

    // Inicializar el binding
    private var _binding: FragmentCrearBinding? = null
    private val binding get() = _binding!!

    // Firebase Database Reference
    private lateinit var database: DatabaseReference

    // En el onCreateView se infla el layout del fragmento
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el layout del fragment y habilitar binding
        _binding = FragmentCrearBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicializar la base de datos
        database = FirebaseDatabase.getInstance().getReference("Mascotas")

        // Configurar el botón "Guardar"
        binding.btnGuardar.setOnClickListener {
            val nombre = binding.etNombre.text.toString()
            val sexo = binding.etSexo.text.toString()
            val raza = binding.etRaza.text.toString()

            // Validaciones de campos
            if (nombre.isEmpty()) {
                binding.etNombre.error = "Por favor ingresar nombre"
                return@setOnClickListener
            }
            if (sexo.isEmpty()) {
                binding.etSexo.error = "Por favor ingresar sexo de la mascota"
                return@setOnClickListener
            }
            if (raza.isEmpty()) {
                binding.etRaza.error = "Por favor ingresar raza"
                return@setOnClickListener
            }

            // Generar ID aleatorio
            val id = database.push().key

            val mascota = Mascota(id, nombre, sexo, raza)
            id?.let {
                database.child(it).setValue(mascota).addOnSuccessListener {
                    // Limpiar los campos después de agregar la mascota
                    binding.etNombre.setText("")
                    binding.etSexo.setText("")
                    binding.etRaza.setText("")
                    // Mostrar mensaje de éxito
                    Snackbar.make(binding.root, "Mascota Agregada", Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }

    // Se asegura de limpiar el binding cuando el fragmento sea destruido
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
