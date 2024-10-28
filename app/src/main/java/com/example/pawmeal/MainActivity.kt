package com.example.pawmeal

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    private val credenciales = arrayOf(
        Pair("usuario", "1234"),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val usuarioEditText = findViewById<TextInputEditText>(R.id.etUsuario)
        val contraseñaEditText = findViewById<TextInputEditText>(R.id.etContraseña)

        findViewById<Button>(R.id.btnIniciarSesion).setOnClickListener {
            val usuarioIngresado = usuarioEditText.text.toString()
            val contraseñaIngresada = contraseñaEditText.text.toString()

            if (usuarioIngresado.isEmpty()) {
                Toast.makeText(this, "Por favor, ingrese el nombre de usuario", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (contraseñaIngresada.isEmpty()) {
                Toast.makeText(this, "Por favor, ingrese la contraseña", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (validarCredenciales(usuarioIngresado, contraseñaIngresada)) {
                val intent = Intent(this, VistaPrincipal::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Usuario o contraseña incorrecta", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validarCredenciales(usuario: String, contraseña: String): Boolean {
        for (credencial in credenciales) {
            if (credencial.first == usuario && credencial.second == contraseña) {
                return true
            }
        }
        return false
    }
}

