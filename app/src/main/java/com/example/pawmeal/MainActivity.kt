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
    // Array de pares para almacenar usuarios y contraseñas
    private val credenciales = arrayOf(
        Pair("usuario1", "1234"),
        Pair("usuario2", "abcd"),
        Pair("usuario3", "5678")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { vista, insets ->
            val barrasDelSistema = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            vista.setPadding(barrasDelSistema.left, barrasDelSistema.top, barrasDelSistema.right, barrasDelSistema.bottom)
            insets
        }

        // Configuración del botón
        findViewById<Button>(R.id.btnIniciarSesion).setOnClickListener {
            try {
                val usuarioIngresado = findViewById<TextInputEditText>(R.id.etUsuario).text.toString()
                val contraseñaIngresada = findViewById<TextInputEditText>(R.id.etContraseña).text.toString()

                // Validación de las credenciales
                if (validarCredenciales(usuarioIngresado, contraseñaIngresada)) {
                    // Inicio de sesión exitoso, navegar a VistaPrincipal
                    val intent = Intent(this, VistaPrincipal::class.java)
                    startActivity(intent)
                } else {
                    // Credenciales incorrectas, mostrar notificación
                    Toast.makeText(this, "Usuario o contraseña incorrecta", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                // Manejo de errores
                Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validarCredenciales(usuario: String, contraseña: String): Boolean {
        for (credencial in credenciales) {
            if (credencial.first == usuario && credencial.second == contraseña) {
                return true // Credenciales válidas
            }
        }
        return false // Credenciales inválidas
    }
}