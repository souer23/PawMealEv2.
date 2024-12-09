package com.example.pawmeal.Vistas

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.pawmeal.MainActivity
import com.example.pawmeal.R
import com.example.pawmeal.RegistrarActivity
import com.example.pawmeal.VistaAgua
import com.example.pawmeal.VistaComida
import com.example.pawmeal.VistaConfiguracion
import com.example.pawmeal.VistaNotificacion
import com.example.pawmeal.VistaPrincipal
import com.example.pawmeal.databinding.ActivityAdminMascotaBinding
import com.example.pawmeal.databinding.ActivityMainBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth


class AdminMascotaActivity : AppCompatActivity() {

    //Configuracion de viewBinding
    private lateinit var binding: ActivityAdminMascotaBinding
    // iniciar firebase
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Inicializar viewBinding
        binding = ActivityAdminMascotaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
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
        //programar boton para Ver agua
        binding.btnAgua.setOnClickListener{
            try {
                val intent= Intent(this, VistaAgua::class.java)
                startActivity(intent)
            }
            catch (e:Exception){
                Toast.makeText(this, e.toString(),Toast.LENGTH_SHORT).show()
            }
        }
        //programar boton para ver comida
        binding.btnComida.setOnClickListener{
            try {
                val intent= Intent(this, VistaComida::class.java)
                startActivity(intent)
            }
            catch (e:Exception){
                Toast.makeText(this, e.toString(),Toast.LENGTH_SHORT).show()
            }
        }
        //programar boton para ver configuraciones comida y agua
        binding.btnConfiguracion.setOnClickListener{
            try {
                val intent= Intent(this, VistaConfiguracion::class.java)
                startActivity(intent)
            }
            catch (e:Exception){
                Toast.makeText(this, e.toString(),Toast.LENGTH_SHORT).show()
            }
        }
        //programar boton para ver notificaciones
        binding.btnNotificaciones.setOnClickListener{
            try {
                val intent= Intent(this, VistaNotificacion::class.java)
                startActivity(intent)
            }
            catch (e:Exception){
                Toast.makeText(this, e.toString(),Toast.LENGTH_SHORT).show()
            }
        }
        //programar btn para volver a la lista y registrar mascotas
        binding.btnVolver.setOnClickListener{
            try {
                val intent= Intent(this, VistaPrincipal::class.java)
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