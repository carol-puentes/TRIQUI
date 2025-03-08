package com.example.juegofinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn: Button = findViewById(R.id.prueba) //Enlace de recurso de interfaz con val

        btn.setOnClickListener {
            val intent = Intent(this@MainActivity, Multijuegos::class.java)  //Comunicacion entre los componentes
            startActivity(intent)
        }
    }
}



