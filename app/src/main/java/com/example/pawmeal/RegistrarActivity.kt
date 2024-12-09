package com.example.pawmeal

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.pawmeal.databinding.ActivityMainBinding
import com.example.pawmeal.databinding.ActivityRegistrarBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class RegistrarActivity : AppCompatActivity() {

    //Configuracion de viewBinding
    private lateinit var binding: ActivityRegistrarBinding
    //firebase
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //Inicializar viewBinding
        binding = ActivityRegistrarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize Firebase Auth
        auth = Firebase.auth

        binding.btnRegistrar.setOnClickListener{
            //obtener el correo
            val email = binding.etEmail.text.toString()
            val pass1 = binding.etPassword1.text.toString()
            val pass2 = binding.etPassword2.text.toString()

            //try and catch en caso de que no se haya un problema al ingresar los datos

            try {
                if (email.isEmpty()){
                    binding.etEmail.error = "Porfavor  ingrese un correo"
                    return@setOnClickListener
                }
                if (pass1.isEmpty()){
                    binding.etEmail.error = "Porfavor  ingrese una contraseña"
                    return@setOnClickListener
                }
                if (pass2.isEmpty()){
                    binding.etEmail.error = "Porfavor repita la contraseña"
                    return@setOnClickListener
                }
                //validad que ambas contraseñas coincidan
                if(pass1 != pass2){
                    binding.etPassword2.error = "Las contraseñas no coinciden"
                    return@setOnClickListener
                }
                else{
                    signUp(email,pass1)
                }
            }
            catch (e: Exception){
                Log.e("Error", e.message.toString())
            }
        }


        //Crear boton para volver a la pagina de inicion de sesion en caso de querer cancelar el registro
        binding.btnVolver.setOnClickListener {
            try {
                val intent = Intent( this, MainActivity::class.java)
                startActivity(intent)
            }
            catch (e: Exception){
                Log.e("Error", e.message.toString())
            }


        }
    }

    private fun signUp(email: String, pass1: String) {
        auth.createUserWithEmailAndPassword(email, pass1)
            .addOnCompleteListener{
                //si se crea el usuario sin errores lo lleva a la pagina de inicio de sesion
                if(it.isSuccessful){
                    try {
                        Toast.makeText(this, "Usuario registrado",Toast.LENGTH_SHORT).show()
                        val intent = Intent( this, MainActivity::class.java)
                        startActivity(intent)
                    }
                    catch (e: Exception){
                        Log.e("Error", e.message.toString())
                    }
                }
                else{
                    Toast.makeText(this, "Error en el registro del usuario", Toast.LENGTH_SHORT).show()
                }
            }

    }

}