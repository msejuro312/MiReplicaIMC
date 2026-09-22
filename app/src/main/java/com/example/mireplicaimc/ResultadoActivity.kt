package com.example.mireplicaimc

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mireplicaimc.databinding.ActivityResultadoBinding
class ResultadoActivity : AppCompatActivity(){
    private lateinit var binding: ActivityResultadoBinding

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = ActivityResultadoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mostrarResultado()

        binding.buttonVolver.setOnClickListener {
            finish()
        }

    }

    private fun mostrarResultado(){
        val nombre = intent.getStringExtra(EXTRA_NOMBRE)
            ?: getString(R.string.valor_no_disponible)
        val imc = intent.getDoubleExtra(EXTRA_IMC,IMC_POR_DEFECTO)
        val clasificacion = intent.getStringExtra(EXTRA_CLASIFICACION)
            ?: getString(R.string.valor_no_disponible)

        binding.textViewNombreResultado.text=
            getString(R.string.resultado_nombre_formato,nombre)
        binding.textViewImcResultado.text = getString(R.string.resultado_imc_formato,imc)
        binding.textViewClasificacionResultado.text = clasificacion

    }

    companion object {
        const val EXTRA_NOMBRE = "extra_nombre"
        const val EXTRA_IMC = "extra_imc"
        const val EXTRA_CLASIFICACION = "extra_clasificacion"
        private const val IMC_POR_DEFECTO = 0.0
    }
}

