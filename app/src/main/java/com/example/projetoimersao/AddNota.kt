package com.example.appimersao

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.projetoimersao.R
import java.lang.Exception

class AddNota : AppCompatActivity() {


    lateinit var titulo:EditText
    lateinit var descricao:EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_nota)

        titulo = findViewById(R.id.titulo)
        descricao = findViewById(R.id.descricao)

    }

    override fun onStart() {
        super.onStart()


        val botaoAdicionarNota = findViewById<Button>(R.id.botao_adicionar_nota)
        botaoAdicionarNota.setOnClickListener {

            val note = Note(null,titulo.text.toString(),descricao.text.toString())

            val noteDatabase = NoteDatabase()

            noteDatabase.criarNovaNota(note, object : CallbackDatabase{
                override fun onSuccess(valor: Boolean) {
                    Toast.makeText(this@AddNota,"Nota salva com sucesso", Toast.LENGTH_SHORT).show()
                    val irParaAMain = Intent(this@AddNota, MainActivity::class.java)
                    startActivity(irParaAMain)
                }

                override fun onFailure(valor: Exception) {
                    Toast.makeText(this@AddNota,"Erro ao salvar nota "+valor.message, Toast.LENGTH_SHORT).show()
                }

            })

        }


    }

}