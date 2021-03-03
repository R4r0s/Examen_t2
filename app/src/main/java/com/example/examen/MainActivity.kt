package com.example.examen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {
    lateinit var editTextNumero1: EditText
    lateinit var editTextNumero2: EditText
    lateinit var buttonSuma: Button
    lateinit var buttonResta: Button
    lateinit var buttonMulti: Button
    lateinit var buttonDiv: Button
    private var numero1: String = ""
    private var numero2: String = ""
    private lateinit var operador: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editTextNumero1 = findViewById(R.id.editTextTextNumero1)
        editTextNumero2 = findViewById(R.id.editTextTextNumero2)
        buttonSuma = findViewById(R.id.buttonSuma)
        buttonResta = findViewById(R.id.buttonResta)
        buttonMulti = findViewById(R.id.buttonMulti)
        buttonDiv = findViewById(R.id.buttonDiv)

       buttonSuma.setOnClickListener {
           if (numero1 == null && numero2== null){
               Toast.makeText(applicationContext, "Rellene los campos", Toast.LENGTH_SHORT).show()
           }else {
               numero1 = editTextNumero1.text.toString()
               numero2 = editTextNumero2.text.toString()
               operador = "+"
           }
            if (buttonResta.isEnabled && buttonDiv.isEnabled && buttonMulti.isEnabled){
                buttonResta.isEnabled = false
                buttonDiv.isEnabled = false
                buttonMulti.isEnabled =  false
            }else{
                buttonResta.isEnabled = true
                buttonDiv.isEnabled = true
                buttonMulti.isEnabled =  true
            }
        }
        buttonResta.setOnClickListener {
            if (numero1 == null && numero2== null){
                Toast.makeText(applicationContext, "Rellene los campos", Toast.LENGTH_SHORT).show()
            }else {
                numero1 = editTextNumero1.text.toString()
                numero2 = editTextNumero2.text.toString()
                operador = "-"
            }
            if (buttonSuma.isEnabled && buttonDiv.isEnabled && buttonMulti.isEnabled){
                buttonSuma.setEnabled(false)
                buttonDiv.setEnabled(false)
                buttonMulti.setEnabled(false)
            }else{
                buttonSuma.isEnabled = true
                buttonDiv.isEnabled = true
                buttonMulti.isEnabled =  true
            }
        }

        buttonMulti.setOnClickListener {
            if (numero1 == null && numero2== null){
                Toast.makeText(applicationContext, "Rellene los campos", Toast.LENGTH_SHORT).show()
            }else {
                numero1 = editTextNumero1.text.toString()
                numero2 = editTextNumero2.text.toString()
                operador = "*"
            }
            if (buttonResta.isEnabled && buttonDiv.isEnabled && buttonSuma.isEnabled){
                buttonResta.isEnabled = false
                buttonDiv.isEnabled = false
                buttonSuma.isEnabled =  false
            }else{
                buttonResta.isEnabled = true
                buttonDiv.isEnabled = true
                buttonSuma.isEnabled =  true
            }
        }

        buttonDiv.setOnClickListener {
            if (numero1 == null && numero2== null){
                Toast.makeText(applicationContext, "Rellene los campos", Toast.LENGTH_SHORT).show()
            }else {
                numero1 = editTextNumero1.text.toString()
                numero2 = editTextNumero2.text.toString()
                operador = "/"
            }
            if (numero1.toInt() == 0){
                Toast.makeText(applicationContext, "El deniminador no puede ser 0", Toast.LENGTH_SHORT).show()
            }
            if (buttonResta.isEnabled && buttonSuma.isEnabled && buttonMulti.isEnabled){
                buttonResta.isEnabled = false
                buttonSuma.isEnabled = false
                buttonMulti.isEnabled =  false
            }else{
                buttonResta.isEnabled = true
                buttonSuma.isEnabled = true
                buttonMulti.isEnabled =  true
            }
        }
    }

    fun iniciarServicio(view: View) {
        var intent : Intent = Intent(applicationContext, MyService::class.java)
        intent.putExtra("nummero1", numero1)
        intent.putExtra("numero2", numero2)
        intent.putExtra("operacion", operador)
        startService(intent)
    }
}