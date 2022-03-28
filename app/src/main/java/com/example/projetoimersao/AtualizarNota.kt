package com.example.appimersao

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.projetoimersao.R
import java.lang.Exception

class AtualizarNota : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_atualizar_nota)

        val novoTitulo = findViewById<EditText>(R.id.novo_titulo)
        val novaDescricao = findViewById<EditText>(R.id.nova_descricao)

        val idNota = intent.getStringExtra("idNote").toString()
        val tituloDaNota = intent.getStringExtra("title").toString()
        val descricaoDaNota = intent.getStringExtra("descricao").toString()

        novoTitulo.setText(tituloDaNota)
        novaDescricao.setText(descricaoDaNota)

        val botaoSalvarNovaNota = findViewById<Button>(R.id.atualizar_button)

        botaoSalvarNovaNota.setOnClickListener {

            val noteDatabase = NoteDatabase()
            val noteMap = mapOf("title" to novoTitulo.text.toString(),
                "descricao" to novaDescricao.text.toString())

            noteDatabase.atualizarNota(idNota,noteMap, object : CallbackDatabase {

                override fun onSuccess(valor: Boolean) {
                    Toast.makeText(this@AtualizarNota, "Nota atualizada", Toast.LENGTH_SHORT).show()

                    val irParaAMain = Intent(this@AtualizarNota, MainActivity::class.java)
                    startActivity(irParaAMain)

                }

                override fun onFailure(valor: Exception) {
                    Toast.makeText(this@AtualizarNota, "Erro ao atualizar nota " + valor, Toast.LENGTH_SHORT).show()
                }

            })

        }



    }
}