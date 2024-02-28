package com.example.t08ejercicio11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {

    //Componentes
    private lateinit var editTextTask: EditText
    private lateinit var botonAddTask: Button
    private lateinit var task1: LinearLayout
    private lateinit var showTask1: ImageButton
    private lateinit var removeTask1: ImageButton
    private lateinit var task2: LinearLayout
    private lateinit var showTask2: ImageButton
    private lateinit var removeTask2: ImageButton
    private lateinit var task3: LinearLayout
    private lateinit var showTask3: ImageButton
    private lateinit var removeTask3: ImageButton

    //ArrayList TASK y layouts
    private var tasks: ArrayList<String?> = arrayListOf(null, null, null)
    private lateinit var tasksLayout: ArrayList<LinearLayout?>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //LÓGICA ===========================================

        //Insertamos la barra de herramientas
        setSupportActionBar(findViewById(R.id.toolbar))
        //Recuperamos los componentes
        recuperarComponentes()
        //Actualizo la visibilidad de las acciones
        actualizarVisibilidadTasks()
        //Implemento las acciones de los botones
        implementarAcciones()
    }

    //Función que recupera los componentes por su id y inicia el tasksLayout
    private fun recuperarComponentes() {
        editTextTask = findViewById(R.id.editTextTask)
        botonAddTask = findViewById(R.id.buttonAddTodo)
        task1 = findViewById(R.id.task1)
        showTask1 = findViewById(R.id.imageButton)
        removeTask1 = findViewById(R.id.imageButton2)
        task2 = findViewById(R.id.task2)
        showTask2 = findViewById(R.id.imageButton3)
        removeTask2 = findViewById(R.id.imageButton4)
        task3 = findViewById(R.id.task3)
        showTask3 = findViewById(R.id.imageButton5)
        removeTask3 = findViewById(R.id.imageButton6)
        tasksLayout = arrayListOf(task1, task2, task3)
    }

    //Esta función comprueba el ArrayList tasks mostrando las opciones de los task si existen
    private fun actualizarVisibilidadTasks() {
        for (i in 0 until tasks.size) {
            if (tasks[i] == null) {
                tasksLayout[i]?.visibility = View.INVISIBLE
            } else {
                tasksLayout[i]?.visibility = View.VISIBLE
            }
        }
    }

    //Este método implementa las acciones de los botones
    private fun implementarAcciones() {
        //AddTasks
        botonAddTask.setOnClickListener {
            addTask()
        }

        //Visualizar Tasks
        showTask1.setOnClickListener {
            visualizarTask(0)
        }
        showTask2.setOnClickListener {
            visualizarTask(1)
        }
        showTask3.setOnClickListener {
            visualizarTask(2)
        }

        //Eliminar Tasks
        removeTask1.setOnClickListener {
            eliminarTask(0)
        }
        removeTask2.setOnClickListener {
            eliminarTask(1)
        }
        removeTask3.setOnClickListener {
            eliminarTask(2)
        }
    }

    //Añadir una task
    private fun addTask() {
        var texto: String = editTextTask.text.toString()

        //Controlo que el task no esté vacio
        if (texto == "") {
            Toast.makeText(applicationContext, getText(R.string.multiLineVacia), Toast.LENGTH_SHORT)
                .show()
        } else {
            //Controlo que haya capacidad para añadir mas Tasks
            var posicion: Int = posicionVacia()
            if (posicion != -1) {
                //Añado el task a la lista
                tasks[posicion] = texto
                //Borro el texto
                editTextTask.setText("")
                //Visualizo las acciones
                tasksLayout[posicion]?.visibility = View.VISIBLE
                //Muestro mensaje
                Toast.makeText(applicationContext, getText(R.string.taskAniadida), Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(applicationContext, getText(R.string.capacidadMaxima), Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    //Visualiza una task
    private fun visualizarTask(posicion: Int) {
        tasks[posicion]?.let { mostrarCuadroDialogo(it) }
    }

    //Elimina una task
    private fun eliminarTask(posicion: Int) {
        tasks[posicion] = null
        tasksLayout[posicion]?.visibility = View.INVISIBLE
        //Muestro mensaje
        Toast.makeText(applicationContext, getText(R.string.taskEliminada), Toast.LENGTH_SHORT)
            .show()
    }

    //Función que elimina todas las task
    private fun borrarTasks() {
        //Creamos cuadroDialogo
        val cuadroDialogo: AlertDialog.Builder = AlertDialog.Builder(this)
        //Establecemos título y mensaje
        cuadroDialogo.setTitle("Eliminar")
        cuadroDialogo.setMessage("¿Desea eliminar todas las Tasks?")

        //Configuramos el positiveButton
        cuadroDialogo.setPositiveButton("SI") { dialog, which ->
            //Elimino todos los tasks
            tasks = arrayListOf(null, null, null)
            //Actualizo visibilidad de las acciones
            actualizarVisibilidadTasks()
            //Muestro mensaje
            Toast.makeText(applicationContext, getText(R.string.tasksEliminadas), Toast.LENGTH_SHORT)
                .show()
            dialog.cancel()
        }

        //Configuramos el negativeButton
        cuadroDialogo.setNegativeButton("NO") { dialog, which ->
            dialog.cancel()
        }

        //Mostramos
        cuadroDialogo.show()
    }

    //Función que devuelve el primer indice vacío si existe, sino devuelve -1
    private fun posicionVacia(): Int {
        for (i in 0 until tasks.size) {
            if (tasks[i] == null) {
                return i
            }
        }
        return -1
    }

    //Función que genera un cuadro de diálogo
    private fun mostrarCuadroDialogo(texto: String) {
        //Creamos cuadroDialogo
        val cuadroDialogo: AlertDialog.Builder = AlertDialog.Builder(this)
        //Establecemos título y mensaje
        cuadroDialogo.setTitle("Task")
        cuadroDialogo.setMessage(texto)
        //Configuramos el botón aceptar
        cuadroDialogo.setPositiveButton("Salir") { dialog, which ->
            dialog.cancel()
        }
        //Mostramos
        cuadroDialogo.show()
    }

    //Métodos para la implementación del menu en el toolbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.opcion1 -> {
                borrarTasks()
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }
}