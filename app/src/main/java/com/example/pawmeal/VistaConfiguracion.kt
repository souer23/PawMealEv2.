package com.example.pawmeal

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText

class VistaConfiguracion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.vista_configuracion)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.vista_configuracion)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val config: ImageView = findViewById(R.id.iconoConfig)
        val noti: ImageView = findViewById(R.id.iconoNoti)
        val salir: ImageView = findViewById(R.id.iconoSalir)
        val volver: Button = findViewById(R.id.volver)

        volver.setOnClickListener {
            val intent = Intent(this, VistaPrincipal::class.java)
            startActivity(intent)
        }

        config.setOnClickListener {
            val intent = Intent(this, VistaConfiguracion::class.java)
            startActivity(intent)
        }

        noti.setOnClickListener {
            val intent = Intent(this, VistaNotificacion::class.java)
            startActivity(intent)
        }

        salir.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
}