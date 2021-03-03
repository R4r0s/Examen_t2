package com.example.examen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MostrarResultado : AppCompatActivity() {
    lateinit var textViewResultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar_resultado)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        textViewResultado = findViewById(R.id.textViewResultado)
        val extras = intent.extras
        var num1: String?
        var num2: String?
        var operacion: String?
        var resultOperacion: String?
        if (extras != null) {
            if (extras.containsKey("num1") && extras.containsKey("num2") && extras.containsKey("op") && extras.containsKey(
                    "result"
                )
            ) {
                num1 = extras.getString("num1")
                num2 = extras.getString("num2")
                operacion = extras.getString("op")
                resultOperacion = extras.getString("result")
                textViewResultado.text = "${num1} ${operacion} ${num2} = ${resultOperacion}"

            }
        }

    }
}