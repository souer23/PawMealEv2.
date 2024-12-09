package com.example.pawmeal //nombre del paquete para la firebase

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.example.pawmeal.databinding.ActivityMainBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class MainActivity : AppCompatActivity() {

    //Configuracion de viewBinding
    private lateinit var binding: ActivityMainBinding

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Inicializar viewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize Firebase Auth
        auth = Firebase.auth

        // Programar el boton de login
        binding.btnIniciarSesion.setOnClickListener {

            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            if (email.isEmpty()){
                binding.etEmail.error = "Por favor ingrese un correo"
                return@setOnClickListener
            }

            if (password.isEmpty()){
                binding.etPassword.error = "Por favor ingrese la contraseña"
                return@setOnClickListener
            }
            signIn(email, password)
        }

        //programar textview para registrarse
        binding.tvRegistrar.setOnClickListener{
            try {
                val intent= Intent(this, RegistrarActivity::class.java)
                startActivity(intent)
            }
            catch (e:Exception){
                Toast.makeText(this, e.toString(),Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener{
                if(it.isSuccessful){
                    binding.etEmail.setText("")
                    binding.etPassword.setText("")
                    Toast.makeText(this, "Inicio de sesión correcto", Toast.LENGTH_SHORT).show()

                    try {
                        // ir a la vista Principal
                        val intent = Intent(this, VistaPrincipal::class.java)
                        startActivity(intent)
                    }
                    catch (e: Exception){
                        //try and catch en caso de que no deje ir a vista principal
                        Log.e("Error", e.message.toString())
                        Toast.makeText(this, "Error al ir a vista principal", Toast.LENGTH_SHORT).show()
                    }

                }else{
                    Toast.makeText(this, "Error al iniciar sesión", Toast.LENGTH_SHORT).show()
                }
            }
    }
}

