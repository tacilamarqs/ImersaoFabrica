package com.example.appimersao

import android.util.Log
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class NoteDatabase {

    private  var referenciaDoBancoDeDados = Firebase.database.reference


    fun criarNovaNota(note:Note, callbackDatabase: CallbackDatabase){
        referenciaDoBancoDeDados.child("Notas").child(gerarIdAutomatico()).setValue(note)
            .addOnCompleteListener { data ->
                Log.i("Tudo ok save", "nota ${data.isSuccessful}")
                callbackDatabase.onSuccess(data.isSuccessful)
            }
            .addOnFailureListener { exception ->
                Log.i("Error in sava","Erro ao salvar nota", exception)
                callbackDatabase.onFailure(exception)
            }
    }

    fun lerTodasAsNotas(callbackDatabase: CallbackDatabase){
        referenciaDoBancoDeDados.child("Notas").get()
            .addOnSuccessListener { data ->
                val notasVindasDoBD = ArrayList<Note>()
                for (itens in data.children){
                    val chave = itens.key
                    val filho = itens.getValue(Note::class.java)
                    val note = Note(chave,filho?.title,filho?.descricao)
                    notasVindasDoBD.add(note)
                }

                callbackDatabase.getData(notasVindasDoBD)

                Log.i("Tudo ok save", "nota ${data.value}")
            }
            .addOnFailureListener { exception ->
                Log.i("Error ao ler","Erro ao ler todas as nota", exception)
                callbackDatabase.onFailure(exception)
            }

    }


    fun atualizarNota(idNote : String, noteMap : Map<String,String>,callbackDatabase: CallbackDatabase){
        referenciaDoBancoDeDados.child("Notas").child(idNote).updateChildren(noteMap)
            .addOnCompleteListener {
                Log.i("Tudo ok update", "nota ${it.isSuccessful}")

                callbackDatabase.onSuccess(it.isSuccessful)
            }
            .addOnFailureListener {
                Log.i("Error in update","Erro ao atualizar dados", it )

                callbackDatabase.onFailure(it)
            }

    }


    fun excluirNota(idNota:String, callbackDatabase: CallbackDatabase){
        referenciaDoBancoDeDados.child("Notas").child(idNota).removeValue()
            .addOnCompleteListener {

                callbackDatabase.onSuccess(it.isSuccessful)
                if(it.isSuccessful){
                    Log.i("Nota deletada", "tudo certo ao excluir a nota ")
                }
            }
            .addOnFailureListener {
                Log.i("Error ao excluir", "algo errado em ", it)
                callbackDatabase.onFailure(it)
            }
    }

    fun lerNota(idNota: String){
        referenciaDoBancoDeDados.child("Notas").child(idNota).get()
            .addOnSuccessListener {
                Log.i("Ler dado", " todas as nota ${it.value}")
            }
            .addOnFailureListener {
                Log.i("Error ao ler", "Erro ao ler todas as nota", it)
            }
    }



    private fun gerarIdAutomatico(): String {
        val idAutomatico = Firebase.database.getReference("Notas").push().key
        return idAutomatico.toString()
    }

}