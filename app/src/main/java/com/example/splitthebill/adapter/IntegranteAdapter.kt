package com.example.splitthebill.adapter

import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.splitthebill.R
import com.example.splitthebill.databinding.CelulaIntegranteBinding
import com.example.splitthebill.model.Integrante

class IntegranteAdapter(context: Context,
                     private val integranteList: MutableList<Integrante>
): ArrayAdapter<Integrante>(context, R.layout.celula_integrante, integranteList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val integrante = integranteList[position]
        var cib: CelulaIntegranteBinding? = null

        var celulaIntegranteView = convertView
        if(celulaIntegranteView == null){
            cib = CelulaIntegranteBinding.inflate(
                context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater,
                parent,
                false
            )
            celulaIntegranteView = cib.root

            val celulaIntergranteHolder = celulaIntergranteHolder(cib.nomeTv, cib.valorPagoTv)
            celulaIntegranteView.tag = celulaIntergranteHolder
        }
        val holder = celulaIntegranteView.tag as celulaIntergranteHolder
        holder.nomeTv.setText(integrante.nome)
        holder.valorTv.setText(integrante.valorPago)

        return celulaIntegranteView
    }

    private data class celulaIntergranteHolder(val nomeTv: TextView, val valorTv: TextView)

}