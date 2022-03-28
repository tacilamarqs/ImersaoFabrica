package com.example.appimersao

import java.lang.Exception

interface CallbackDatabase {
    fun onSuccess(valor:Boolean)
    fun onFailure(valor: Exception)
    fun getData(note: ArrayList<Note>){}

}