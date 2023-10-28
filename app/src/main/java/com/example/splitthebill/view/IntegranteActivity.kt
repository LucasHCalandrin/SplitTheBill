package com.example.splitthebill.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.splitthebill.R
import com.example.splitthebill.databinding.ActivityIntegranteBinding

class IntegranteActivity : AppCompatActivity() {

    private val aib: ActivityIntegranteBinding by lazy {
        ActivityIntegranteBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(aib.root)

        setSupportActionBar(aib.toolbarIn.toolbar)
        supportActionBar?.subtitle = "Informações do Integrante"
    }
}