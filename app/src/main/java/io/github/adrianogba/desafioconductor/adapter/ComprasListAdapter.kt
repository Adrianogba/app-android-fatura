package io.github.adrianogba.desafioconductor.adapter


import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import io.github.adrianogba.desafioconductor.R
import io.github.adrianogba.desafioconductor.model.Compra

import kotlinx.android.synthetic.main.item_compra.view.*
import java.text.DecimalFormat


class ComprasListAdapter(private val contactosList: ArrayList<Compra>) : RecyclerView.Adapter<ComprasListAdapter.myViewHolder>() {



    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        holder.bind(contactosList[position])
    }

    override fun getItemCount(): Int {
        return contactosList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComprasListAdapter.myViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return myViewHolder(layoutInflater.inflate(R.layout.item_compra, parent, false))
    }

    class myViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        val mFormat = DecimalFormat("#,##0.00")
        fun bind(compra: Compra) {
            itemView.tvValor.text = mFormat.format(compra.value.toFloat()) + "R$"
            //itemView.tvTelefono.text = contacto.telefono
            itemView.setOnClickListener {

            }
        }
    }
}