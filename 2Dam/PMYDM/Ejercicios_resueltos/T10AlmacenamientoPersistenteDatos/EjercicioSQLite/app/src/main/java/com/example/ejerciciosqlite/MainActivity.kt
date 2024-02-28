package com.example.ejerciciosqlite

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast


class MainActivity : AppCompatActivity() {

    //Variables globales
    private lateinit var inputCodigo: EditText
    private lateinit var inputDescripcion: EditText
    private lateinit var inputStock: EditText
    private lateinit var inputPrecio: EditText
    private lateinit var botonAlta: Button
    private lateinit var botonBaja: Button
    private lateinit var botonModificacion: Button
    private lateinit var botonConsultaCodigo: Button
    private lateinit var botonConsultaDescripcion: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //LÓGICA==========================================
        //Recupero los componentes
        inputCodigo = findViewById(R.id.inputCodigo)
        inputDescripcion = findViewById(R.id.inputDescripcion)
        inputStock = findViewById(R.id.inputStock)
        inputPrecio = findViewById(R.id.inputPrecio)
        botonAlta = findViewById(R.id.botonAlta)
        botonBaja = findViewById(R.id.botonBaja)
        botonModificacion = findViewById(R.id.botonModificacion)
        botonConsultaCodigo = findViewById(R.id.botonConsultaPorCodigo)
        botonConsultaDescripcion = findViewById(R.id.botonConsultaPorDescripcion)

        //Acción botón alta
        botonAlta.setOnClickListener {
            alta()
        }
        //Acción botón baja
        botonBaja.setOnClickListener {
            baja()
        }

        //Acción botón modificación
        botonModificacion.setOnClickListener {
            modificacion()
        }

        //Acción botón consulta por código
        botonConsultaCodigo.setOnClickListener {
            consultaPorCodigo()
        }

        //Acción botón consulta por descripción
        botonConsultaDescripcion.setOnClickListener {
            consultaPorDescripcion()
        }

    }

    private fun alta() {
        //Recuperamos los datos
        val codigo: Int? = inputCodigo.text.toString().toIntOrNull()
        val descripcion: String = inputDescripcion.text.toString()
        val stock: Int? = inputStock.text.toString().toIntOrNull()
        val precio: Float? = inputPrecio.text.toString().toFloatOrNull()

        //Comprobamos que no sean nulos
        if (codigo != null && !descripcion.isNullOrEmpty() && stock != null && precio != null) {
            //Creo objeto AdministracionSQLiteOpenHelper
            val admin: AdministracionSQLiteOpenHelper =
                AdministracionSQLiteOpenHelper(applicationContext, "tienda", 1)
            //Recupero la bd
            val bd: SQLiteDatabase = admin.writableDatabase
            //Creo el ContentValues para meter los datos recuperados
            val registro: ContentValues = ContentValues()
            registro.put("codigo", codigo)
            registro.put("descripcion", descripcion)
            registro.put("stock", stock)
            registro.put("precio", precio)
            //Hago el insert
            bd.insert("articulos", null, registro)
            //Libero recursos
            bd.close()
            //Limpio EditText
            inputCodigo.setText("")
            inputDescripcion.setText("")
            inputPrecio.setText("")
            inputStock.setText("")
            Toast.makeText(applicationContext, "Todo ok", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(applicationContext, getString(R.string.toast_no_datos), Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun baja() {
        val codigo: Int? = inputCodigo.text.toString().toIntOrNull()
        if (codigo == null) {
            Toast.makeText(applicationContext, "¡Debes introducir un código!", Toast.LENGTH_SHORT)
                .show()
        } else {
            val admin: AdministracionSQLiteOpenHelper =
                AdministracionSQLiteOpenHelper(applicationContext, "tienda", 1)
            val bd: SQLiteDatabase = admin.writableDatabase
            val registrosBorrados: Int =
                bd.delete("articulos", "codigo=$codigo", null)
            if (registrosBorrados == 1) {
                //Limpio EditText
                inputCodigo.setText("")
                inputDescripcion.setText("")
                inputPrecio.setText("")
                inputStock.setText("")
                Toast.makeText(applicationContext, "Todo ok", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(applicationContext, "No hay artículos con ese código", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun modificacion() {
        val codigo: Int? = inputCodigo.text.toString().toIntOrNull()
        val descripcion: String = inputDescripcion.text.toString()
        val stock: Int? = inputStock.text.toString().toIntOrNull()
        val precio: Float? = inputPrecio.text.toString().toFloatOrNull()

        val admin: AdministracionSQLiteOpenHelper =
            AdministracionSQLiteOpenHelper(applicationContext, "tienda", 1)
        val bd: SQLiteDatabase = admin.writableDatabase
        val registro = ContentValues()
        registro.put("codigo", codigo)
        registro.put("descripcion", descripcion)
        registro.put("stock", stock)
        registro.put("precio", precio)
        val registrosModificados: Int =
            bd.update("articulos", registro, "codigo=$codigo", null)
        bd.close()

        if (registrosModificados == 1) {
            //Limpio EditText
            inputCodigo.setText("")
            inputDescripcion.setText("")
            inputPrecio.setText("")
            inputStock.setText("")
            Toast.makeText(applicationContext, "Todo ok", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(applicationContext, "No hay artículos con ese código", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun consultaPorCodigo() {
        val codigo: Int? = inputCodigo.text.toString().toIntOrNull()
        if (codigo == null) {
            Toast.makeText(applicationContext, "¡Debes introducir un código!", Toast.LENGTH_SHORT)
                .show()
        } else {
            val admin: AdministracionSQLiteOpenHelper =
                AdministracionSQLiteOpenHelper(applicationContext, "tienda", 1)
            val bd: SQLiteDatabase = admin.writableDatabase
            val consulta: String =
                "SELECT descripcion, stock, precio FROM articulos WHERE codigo=$codigo"
            val cursorConsulta: Cursor = bd.rawQuery(consulta, null)

            if (cursorConsulta.moveToFirst()) {
                inputDescripcion.setText(cursorConsulta.getString(0))
                inputStock.setText(cursorConsulta.getString(1))
                inputPrecio.setText(cursorConsulta.getString(2))
            } else {
                Toast.makeText(applicationContext, "No hay artículos con ese código", Toast.LENGTH_SHORT)
                    .show()
            }
            bd.close()
        }
    }

    private fun consultaPorDescripcion() {
        val descripcion: String = inputDescripcion.text.toString()
        if (descripcion == "") {
            Toast.makeText(applicationContext, "¡Debes introducir una descripcion!", Toast.LENGTH_SHORT)
                .show()
        } else {
            val admin: AdministracionSQLiteOpenHelper =
                AdministracionSQLiteOpenHelper(applicationContext, "tienda", 1)
            val bd: SQLiteDatabase = admin.writableDatabase
            val consulta: String =
                "SELECT codigo, stock, precio FROM articulos WHERE descripcion='$descripcion'"
            val cursorConsulta: Cursor = bd.rawQuery(consulta, null)

            if (cursorConsulta.moveToFirst()) {
                inputCodigo.setText(cursorConsulta.getString(0))
                inputStock.setText(cursorConsulta.getString(1))
                inputPrecio.setText(cursorConsulta.getString(2))
            } else {
                Toast.makeText(applicationContext, "No existe esa descripción", Toast.LENGTH_SHORT)
                    .show()
            }
            bd.close()
        }
    }


}