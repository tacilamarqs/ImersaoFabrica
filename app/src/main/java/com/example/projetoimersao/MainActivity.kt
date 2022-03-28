package com.example.appimersao

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.projetoimersao.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private lateinit var listView: RecyclerView
    private lateinit var todasAsNotas:ArrayList<Note>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val botaoAdicionar = findViewById<FloatingActionButton>(R.id.floatingActionButton)

        listView = findViewById(R.id.recycle_view)

        todasAsNotas = ArrayList()

        val customAdapter = CustomAdapter(this,todasAsNotas)
        listView.adapter = customAdapter
        listView.isClickable = true

        val noteDatabase = NoteDatabase()
        noteDatabase.lerTodasAsNotas(object : CallbackDatabase{
            override fun onSuccess(valor: Boolean) {

            }

            override fun onFailure(valor: Exception) {
                Toast.makeText(this@MainActivity,"Error em "+ valor.message,Toast.LENGTH_SHORT).show()
            }

            override fun getData(note: ArrayList<Note>) {
                for (item in note){
                    val note = Note(item.id,item.title,item.descricao)
                    todasAsNotas.add(note)
                    customAdapter.notifyDataSetChanged()
                }

                Log.i("Todas as notas", "valores ${todasAsNotas}")
            }
        })

        customAdapter.setOnItemClickListener(object : CustomAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                val idNota = todasAsNotas[position].id
                val title = todasAsNotas[position].title
                val descricao = todasAsNotas[position].descricao

                val irParaAPaginaMostrarNota = Intent(this@MainActivity,LerNota::class.java)
                irParaAPaginaMostrarNota.putExtra("id", idNota)
                irParaAPaginaMostrarNota.putExtra("title", title)
                irParaAPaginaMostrarNota.putExtra("descricao",descricao)

                startActivity(irParaAPaginaMostrarNota)

            }
        })

        botaoAdicionar.setOnClickListener {
            val irParaPaginaAddNota = Intent(this, AddNota::class.java)
            startActivity(irParaPaginaAddNota)
        }

    }

}