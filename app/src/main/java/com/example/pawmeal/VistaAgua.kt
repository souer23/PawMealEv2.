package com.example.pawmeal

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.example.pawmeal.Vistas.AdminMascotaActivity
import com.example.pawmeal.databinding.ActivityAdminMascotaBinding
import com.example.pawmeal.databinding.VistaAguaBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class VistaAgua : AppCompatActivity() {

    //Configuracion de viewBinding
    private lateinit var binding: VistaAguaBinding
    // iniciar firebase
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Inicializar viewBinding
        binding = VistaAguaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.vista_agua)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //definir e inicializar firebase auth
        auth = Firebase.auth

        binding.iconoSalir.setOnClickListener{
            val intent= Intent(this, MainActivity::class.java)
            startActivity(intent)
            signOut() //Funcion cerrar sesion
        }
        //programar vista para volver a Adminitrador de mascota
        binding.btnVolver.setOnClickListener{
            try {
                val intent= Intent(this, AdminMascotaActivity::class.java)
                startActivity(intent)
            }
            catch (e:Exception){
                Toast.makeText(this, e.toString(),Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun signOut() {
        Firebase.auth.signOut()
        Toast.makeText(this, "Sesion cerrada", Toast.LENGTH_SHORT).show()
        finish()
    }
}