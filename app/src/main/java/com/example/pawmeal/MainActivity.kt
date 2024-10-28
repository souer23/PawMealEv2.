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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Configuración del botón
        findViewById<Button>(R.id.btnIniciarSesion).setOnClickListener {
            try {
                val usuarioIngresado = findViewById<TextInputEditText>(R.id.etUsuario).text.toString()
                val contraseñaIngresada = findViewById<TextInputEditText>(R.id.etContraseña).text.toString()

                // Validación de las credenciales
                if (usuarioIngresado == "usuario" && contraseñaIngresada == "1234") {
                    // Inicio de sesión exitoso, navegar a vista_principal
                    val intent = Intent(this, VistaPrincipal::class.java)
                    startActivity(intent)
                } else {
                    // Credenciales incorrectas, mostrar notificación
                    Toast.makeText(this, "Usuario o contraseña incorrecta", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                // Manejo de errores
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
            }
        }
    }
}