package com.example.splitthebill.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import com.example.splitthebill.databinding.ActivityIntegranteBinding
import com.example.splitthebill.model.Constant.EXTRA_CONTACT
import com.example.splitthebill.model.Integrante
import java.util.Random

class IntegranteActivity : AppCompatActivity() {

    private val aib: ActivityIntegranteBinding by lazy {
        ActivityIntegranteBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(aib.root)

        setSupportActionBar(aib.toolbarIn.toolbar)
        supportActionBar?.subtitle = "Informações do Integrante"

        val integranteRecebido = intent.getParcelableExtra<Integrante>(EXTRA_CONTACT)
        integranteRecebido?.let { _integranteRecebido ->
            with(aib) {
                nomeEt.setText(_integranteRecebido.nome)
                valorEt.setText(_integranteRecebido.valorPago)
                comprouEt.setText(_integranteRecebido.itensComprados)
            }
        }

        with (aib) {
            salvarBt.setOnClickListener {
                val integrante = Integrante(
                    id = integranteRecebido?.id?:generateId(),
                    nome = nomeEt.text.toString(),
                    valorPago = valorEt.text.toString(),
                    itensComprados = comprouEt.text.toString(),
                )

                val resultIntent = Intent()
                resultIntent.putExtra(EXTRA_CONTACT, integrante)
                setResult(RESULT_OK, resultIntent)
                finish()
            }
        }
    }

    private fun generateId() = Random(System.currentTimeMillis()).nextInt()

}