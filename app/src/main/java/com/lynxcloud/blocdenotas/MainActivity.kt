package com.lynxcloud.blocdenotas

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import java.io.FileOutputStream
import java.io.FileInputStream
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private lateinit var editTextNote: EditText
    private lateinit var buttonSave: Button
    private val fileName = "nota.txt"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextNote = findViewById(R.id.editTextNote)
        buttonSave = findViewById(R.id.buttonSave)

        // Cargar la nota si existe
        loadNote()

        buttonSave.setOnClickListener {
            saveNote()
        }
    }

    private fun saveNote() {
        val text = editTextNote.text.toString()
        try {
            val fos: FileOutputStream = openFileOutput(fileName, Context.MODE_PRIVATE)
            fos.write(text.toByteArray())
            fos.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun loadNote() {
        try {
            val fis: FileInputStream = openFileInput(fileName)
            val buffer = ByteArray(fis.available())
            fis.read(buffer)
            fis.close()
            val text = String(buffer)
            editTextNote.setText(text)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}
