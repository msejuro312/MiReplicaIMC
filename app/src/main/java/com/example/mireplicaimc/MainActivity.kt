package com.example.mireplicaimc

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import com.example.mireplicaimc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private  lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonCalcular.setOnClickListener {
            calcularYMostrarResultado()
        }
    }

    private fun calcularYMostrarResultado(){
        val nombre = binding.editTextNombre.text.toString().trim()
        val peso = binding.editTextPeso.text.toString().toDoubleOrNull()
        val estatura = binding.editTextEstatura.text.toString().toDoubleOrNull()
        var formularioValido = true

        binding.inputLayoutNombre.error=null
        binding.inputLayoutPeso.error=null
        binding.inputLayoutEstatura.error=null

        if (nombre.isBlank()){
            binding.inputLayoutNombre.error = getString(R.string.error_nombre)
            formularioValido = false
        }
        if (peso == null || peso <= PESO_MINIMO){
            binding.inputLayoutPeso.error = getString(R.string.error_peso)
            formularioValido = false
        }
        if (estatura == null || estatura <= ESTATURA_MINIMA){
            binding.inputLayoutEstatura.error = getString(R.string.error_estatura)
            formularioValido = false
        }
        if (!formularioValido || peso == null || estatura == null){
            return
        }

        val imc = calcularIMC(peso,estatura)
        val clasificacion = clasificarIMC(imc)

        val intent = Intent(this, ResultadoActivity::class.java).apply {
            putExtra(ResultadoActivity.EXTRA_NOMBRE,nombre)
            putExtra(ResultadoActivity.EXTRA_IMC,imc)
            putExtra(ResultadoActivity.EXTRA_CLASIFICACION,clasificacion)
        }
        startActivity(intent)
    }

    private fun calcularIMC(peso: Double, estatura: Double): Double{
        return peso / (estatura*estatura)
    }

    private fun clasificarIMC(imc: Double): String{
        return when {
            imc< LIMITE_PESO_NORMAL -> getString(R.string.clasificacion_bajo_peso)
            imc< LIMITE_SOBREPESO -> getString(R.string.clasificacion_peso_normal)
            imc< LIMITE_OBESIDAD -> getString(R.string.clasificacion_sobrepeso)
            else -> getString(R.string.clasificacion_obesidad)

        }
    }

    companion object {
        private const val PESO_MINIMO = 0.0
        private const val ESTATURA_MINIMA = 0.0
        private const val LIMITE_PESO_NORMAL = 18.5
        private const val LIMITE_SOBREPESO = 25.0
        private const val LIMITE_OBESIDAD = 30.0

    }

}