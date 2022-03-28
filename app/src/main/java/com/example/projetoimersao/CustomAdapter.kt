package com.example.appimersao

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projetoimersao.R

class CustomAdapter(private val activity: Activity, private val data: ArrayList<Note>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>(){

    private lateinit var mListener : onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position:Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }

    class  ViewHolder(view:View, listerner: onItemClickListener) : RecyclerView.ViewHolder(view){
        val titulo: TextView
        val descricao : TextView

        init {
            titulo = view.findViewById(R.id.titulo_card)
            descricao = view.findViewById(R.id.descricao_card)

            view.setOnClickListener {
                listerner.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(activity)
            .inflate(R.layout.itens,parent,false)
        return ViewHolder(view,mListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.titulo.text = data[position].title.toString()
        holder.descricao.text = data[position].descricao.toString()
    }

    override fun getItemCount(): Int {
        return data.size
    }


}