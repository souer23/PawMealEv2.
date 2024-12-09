package com.example.pawmeal

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.example.pawmeal.Vistas.CrearFragment
import com.example.pawmeal.Vistas.VerFragment
import com.example.pawmeal.databinding.VistaPrincipalBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class VistaPrincipal : AppCompatActivity() {

    //configurar viewBinding
    private lateinit var binding: VistaPrincipalBinding

    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = VistaPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.vista_principal)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //definir e inicializar firebase auth
        auth = Firebase.auth

        binding.iconoSalir.setOnClickListener{
            signOut() //funcion cerrar sesion
        }

        //Cargar fragment por defecto
        try {
            if(savedInstanceState == null){
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container, VerFragment()).commit()
            }
            binding.bottomNavigation.setOnItemSelectedListener {
                when(it.itemId){
                    R.id.item_1->{
                        supportFragmentManager.beginTransaction().replace(R.id.fragment_container,VerFragment()).commit()
                        true
                    }
                    R.id.item_2->{
                        supportFragmentManager.beginTransaction().replace(R.id.fragment_container,CrearFragment()).commit()
                        true
                    }
                    else -> false
                }
            }
            binding.bottomNavigation.setOnItemReselectedListener {
                when(it.itemId){
                    R.id.item_1->{
                        true
                    }
                    R.id.item_2->{
                        true
                    }
                    else -> false
                }
            }
        }
        catch (e: Exception){
            Log.e("Error", e.message.toString())
        }
    }

    private fun signOut() {
        Firebase.auth.signOut()
        Toast.makeText(this, "Sesion cerrada", Toast.LENGTH_SHORT).show()
        finish()
    }
}