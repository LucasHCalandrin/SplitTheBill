package com.example.splitthebill.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.splitthebill.R
import com.example.splitthebill.adapter.IntegranteAdapter
import com.example.splitthebill.databinding.ActivityMainBinding
import com.example.splitthebill.model.Constant.EXTRA_CONTACT
import com.example.splitthebill.model.Constant.VIEW_CONTACT
import com.example.splitthebill.model.Integrante

class MainActivity : AppCompatActivity() {

    private val amb: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val integranteList: MutableList<Integrante> = mutableListOf()

    private val integranteAdapter: IntegranteAdapter by lazy {
        IntegranteAdapter(this, integranteList)
    }

    private lateinit var carl: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(amb.root)

        setSupportActionBar(amb.toolbarIn.toolbar)

        amb.integranteLv.adapter = integranteAdapter

        carl = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult())
        { result ->
            if(result.resultCode == RESULT_OK) {
                val integrante = result.data?.getParcelableExtra<Integrante>(EXTRA_CONTACT)
                integrante?.let{ _integrante ->
                    if (integranteList.any { it.id == integrante.id }) {
                        val posicao = integranteList.indexOfFirst { it.id == integrante.id }
                        integranteList[posicao] = _integrante
                    }
                    else {
                        integranteList.add(_integrante)
                    }
                    integranteAdapter.notifyDataSetChanged()
                }
            }

        }

        amb.integranteLv.setOnItemClickListener { parent, view, position, id ->
            val integrante = integranteList[position]
            val viewIntegranteIntent = Intent(this, IntegranteActivity::class.java)
            viewIntegranteIntent.putExtra(EXTRA_CONTACT, integrante)
            viewIntegranteIntent.putExtra(VIEW_CONTACT, true)
            startActivity(viewIntegranteIntent)
        }

        registerForContextMenu(amb.integranteLv)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.addIntegranteMi -> {
                carl.launch(Intent(this, IntegranteActivity::class.java))
                true
            }
            else -> false
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        menuInflater.inflate(R.menu.context_menu_main, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val posicao = (item.menuInfo as AdapterView.AdapterContextMenuInfo).position
        return when (item.itemId){
            R.id.removerIntegranteMI -> {
                integranteList.removeAt(posicao)
                integranteAdapter.notifyDataSetChanged()
                Toast.makeText(this, "Integrante Excluído", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.editarIntegranteMI -> {
                val integrante = integranteList[posicao]
                val editarIntegranteIntent = Intent(this, IntegranteActivity::class.java)
                editarIntegranteIntent.putExtra(EXTRA_CONTACT, integrante)
                carl.launch(editarIntegranteIntent)
                true
            }
            else -> { false }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        unregisterForContextMenu(amb.integranteLv)
    }

}