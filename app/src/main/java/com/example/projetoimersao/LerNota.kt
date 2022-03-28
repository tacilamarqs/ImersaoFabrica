package com.example.appimersao

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.projetoimersao.R
import java.lang.Exception

class LerNota : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ler_nota)

        val titulo = findViewById<TextView>(R.id.ler_titulo_card)
        val descricao = findViewById<TextView>(R.id.ler_descricao_card)
        val butoaAtualizarNota = findViewById<Button>(R.id.botao_atualizar)
        val botaoExcluirNota = findViewById<Button>(R.id.botao_deletar)

        val idDaNota = intent.getStringExtra("id").toString()
        val tituloDaNota = intent.getStringExtra("title").toString()
        val descircaoDaNota = intent.getStringExtra("descricao").toString()

        titulo.text = tituloDaNota
        descricao.text = descircaoDaNota

        butoaAtualizarNota.setOnClickListener {

            val irParaAPaginaAtualizar = Intent(this,AtualizarNota::class.java)

            irParaAPaginaAtualizar.putExtra("idNote", idDaNota)
            irParaAPaginaAtualizar.putExtra("title", tituloDaNota)
            irParaAPaginaAtualizar.putExtra("descricao", descircaoDaNota)

            startActivity(irParaAPaginaAtualizar)

        }

        botaoExcluirNota.setOnClickListener {

            val noteDatabase = NoteDatabase()

            noteDatabase.excluirNota(idDaNota, object : CallbackDatabase{
                override fun onSuccess(valor: Boolean) {
                    Toast.makeText(this@LerNota, "Nota excluida", Toast.LENGTH_SHORT).show()

                    val irParaAMain = Intent(this@LerNota, MainActivity::class.java)
                    startActivity(irParaAMain)
                }

                override fun onFailure(valor: Exception) {
                    Toast.makeText(this@LerNota, "Erro ao excluir nota "+valor, Toast.LENGTH_SHORT).show()
                }
            })
        }

    }
}