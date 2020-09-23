package com.alexisberrio.formulario

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    companion object {
        private const val EMPTY = ""
        private const val SPACE = " "
    }

    private var fechaNacimiento: String = ""
    private var c = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setIcon(R.mipmap.ic_launcher)

        val dateSetListener =
            DatePickerDialog.OnDateSetListener { view, year, month, day ->
                c.set(Calendar.YEAR, year)
                c.set(Calendar.MONTH, month)
                c.set(Calendar.DAY_OF_MONTH, day)

                val format = "MM/dd/yyyy"
                val sdf = SimpleDateFormat(format, Locale.US)
                fechaNacimiento = sdf.format(c.time).toString()
                fecha_nacimiento_button.text = fechaNacimiento
            }

        fecha_nacimiento_button.setOnClickListener {
            DatePickerDialog(
                this,
                dateSetListener,
                c.get(Calendar.YEAR),
                c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH)
            ).show()

        }

        registrar_button.setOnClickListener {
            val nombre = nombre_edit_text.text.toString()
            val correo = correo_edit_text.text.toString()
            val telefono = telefono_edit_text.text.toString()
            val contrasena = contraseña_edit_text.text.toString()
            val repContrasena = repcontraseña_edit_text.text.toString()
            val genero =
                if (masculino_radioButton.isChecked) getString(R.string.masculino) else getString(R.string.femenino)

            var pasatiempos = EMPTY
            if (nadar_checkBox.isChecked) pasatiempos += getString(R.string.nadar) + SPACE
            if (leer_checkBox.isChecked) pasatiempos += getString(R.string.leer) + SPACE
            if (comer_checkBox.isChecked) pasatiempos += getString(R.string.comer) + SPACE
            if (viajar_checkBox.isChecked) pasatiempos += getString(R.string.viajar)

            val ciudadDeNacimiento = lugar_nacimieto_spinner.selectedItem

            if (contrasena != repContrasena) {
                contraseña_edit_text.error = getString(R.string.error_contrasena)
            } else if (nombre.isEmpty()) nombre_edit_text.error = getString(R.string.campo_vacio)
            else if (correo.isEmpty()) correo_edit_text.error = getString(R.string.campo_vacio)
            else if (telefono.isEmpty()) telefono_edit_text.error = getString(R.string.campo_vacio)
            else if (contrasena.isEmpty()) contraseña_edit_text.error =
                getString(R.string.campo_vacio)
            else if (repContrasena.isEmpty()) repcontraseña_edit_text.error =
                getString(R.string.campo_vacio)
            else if (ciudadDeNacimiento == "Seleccione una ciudad") {
                Toast.makeText(
                    applicationContext,
                    getString(R.string.no_ciudad),
                    Toast.LENGTH_SHORT
                ).show()
            } else if (fechaNacimiento.isEmpty()) {
                Toast.makeText(
                    applicationContext,
                    getString(R.string.no_fecha),
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                mostrar_textView.text = resources.getString(
                    R.string.respuesta,
                    nombre,
                    correo,
                    telefono,
                    genero,
                    pasatiempos,
                    ciudadDeNacimiento,
                    fechaNacimiento
                )
                Toast.makeText(
                    applicationContext,
                    getString(R.string.registro_guardado),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    }
}