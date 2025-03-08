package com.example.juegofinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.example.juegofinal.databinding.ActivityMultijuegosBinding


class Multijuegos : AppCompatActivity() {

    enum class Turn{
        NOUGHT,
        CROSS
    }
    private var firstTurn=Turn.CROSS
    private var currentTurn=Turn.CROSS

    private var crossesScore=0
    private var noughtsScore = 0

    private var boardList = mutableListOf<Button>()

    private lateinit var binding: ActivityMultijuegosBinding //Enlace de los archivos

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMultijuegosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBoard()
    }


    private fun initBoard() {
        boardList.add(binding.a1)
        boardList.add(binding.a2)
        boardList.add(binding.a3)
        boardList.add(binding.b1)
        boardList.add(binding.b2)
        boardList.add(binding.b3)
        boardList.add(binding.c1)
        boardList.add(binding.c2)
        boardList.add(binding.c3)
    }

    //JUEGO COMPLETADO
    fun boardTapped(view: View)
    {
        if(view !is Button)
            return
        addToBoard(view)

        if(checkForVictory(NOUGHT))
        {
            noughtsScore++
            result("$NOUGHT A GANADO!")
        }
        else if(checkForVictory(CROSS))
        {
            crossesScore++
            result("$CROSS A GANADO!")
        }

        if(fullBoard())
        {
            result("JUEGO EMPATADO")
        }

    }

    private fun checkForVictory(s: String): Boolean {
        //Horizontal Victory
        if(match(binding.a1,s) && match(binding.a2,s) && match(binding.a3,s))
            return true
        if(match(binding.b1,s) && match(binding.b2,s) && match(binding.b3,s))
            return true
        if(match(binding.c1,s) && match(binding.c2,s) && match(binding.c3,s))
            return true

        //Vertical Victory
        if(match(binding.a1,s) && match(binding.b1,s) && match(binding.c1,s))
            return true
        if(match(binding.a2,s) && match(binding.b2,s) && match(binding.c2,s))
            return true
        if(match(binding.a3,s) && match(binding.b3,s) && match(binding.c3,s))
            return true

        //Diagonal Victory
        if(match(binding.a1,s) && match(binding.b2,s) && match(binding.c3,s))
            return true
        if(match(binding.a3,s) && match(binding.b2,s) && match(binding.c1,s))
            return true

        return false
    }
    private fun match(button: Button, symbol: String)=button.text==symbol

    //Funcion definida para mostrar la alerta
    private fun result(title: String) {
        val message = "\n$NOUGHT = $noughtsScore\n\n$CROSS = $crossesScore"
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("Reiniciar juego"){ _,_ ->
                resetBoard()
            }
            .setCancelable(false)
            .show()
    }

    //Funcion definida para seleccionar el orden de los turnos en la nueva partida
    private fun resetBoard() {
        for(button in boardList){
            button.text=""
        }

        if(firstTurn == Multijuegos.Turn.NOUGHT)
            firstTurn= Multijuegos.Turn.CROSS
        else if(firstTurn == Multijuegos.Turn.CROSS)
            firstTurn= Multijuegos.Turn.NOUGHT

        currentTurn =firstTurn
        setTurnLabel()
    }
    //Funcion para verificar que cada campo tenga texto
    private fun fullBoard(): Boolean {
        for (button in boardList){
            if(button.text =="")
                return false
        }
        return true
    }

    //Funcion para agregar el texto ('X' '🕷️') al respectivo campo
    private fun addToBoard(button: Button) {

        if(button.text != "")
            return

        if(currentTurn == Multijuegos.Turn.NOUGHT){
            button.text=NOUGHT
            currentTurn = Multijuegos.Turn.CROSS
        }

        else if(currentTurn == Multijuegos.Turn.CROSS){
            button.text=CROSS
            currentTurn = Multijuegos.Turn.NOUGHT
        }
        setTurnLabel()

    }

    //Funcion para definir el siguiente turno
    private fun setTurnLabel() {
        var turnText=""
        if(currentTurn == Multijuegos.Turn.CROSS)
            turnText="TURNO DE $CROSS"
        else if(currentTurn == Multijuegos.Turn.NOUGHT)
            turnText="TURNO DE $NOUGHT"

        binding.turnTV.text= turnText
    }

    companion object {
        const val NOUGHT = "🕷"
        const val CROSS = "X️"
    }

}
