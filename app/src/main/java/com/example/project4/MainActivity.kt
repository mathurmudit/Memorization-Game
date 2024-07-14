package com.example.project4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import android.widget.Toast.makeText

class MainActivity : AppCompatActivity() {

    var game : Game = Game()
    private var MA : String = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        game.setLevel(this)
        game.setTargetSequence()
        var toast: Toast = makeText(this, game.getTargetSequence(), Toast.LENGTH_LONG)
        toast.show()
    }

    // set up methods for each of the buttons — they will call the Game functions
    // if a button function returns false, finish()
    // in each button function, call game.setPreferences() before

    // IF A BUTTON PRESS TRIGGERS A WIN, DISPLAY THE NEW LEVEL TOAST

    fun redPress(v: View) {
        game.setPreferences(this)

        var keepGoing = game.redPress()
        if (!keepGoing) {
            finish()
        }

        if (game.justStarted()) {
            var toast: Toast = makeText(this, game.getTargetSequence(), Toast.LENGTH_LONG)
            toast.show()
        }
    }

    fun greenPress(v: View) {
        Log.w(MA, "green pressed")
        game.setPreferences(this)

        var keepGoing = game.greenPress()
        Log.w(MA, "keepGoing returned " + keepGoing.toString())
        if (!keepGoing) {
            finish()
        }

        if (game.justStarted()) {
            var toast: Toast = makeText(this, game.getTargetSequence(), Toast.LENGTH_LONG)
            toast.show()
        }
    }

    fun yellowPress(v: View) {
        game.setPreferences(this)

        var keepGoing = game.yellowPress()
        if (!keepGoing) {
            finish()
        }

        if (game.justStarted()) {
            var toast: Toast = makeText(this, game.getTargetSequence(), Toast.LENGTH_LONG)
            toast.show()
        }
    }

    fun bluePress(v: View) {
        game.setPreferences(this)

        var keepGoing = game.bluePress()
        if (!keepGoing) {
            finish()
        }

        if (game.justStarted()) {
            var toast: Toast = makeText(this, game.getTargetSequence(), Toast.LENGTH_LONG)
            toast.show()
        }
    }

    fun resetPress(v: View) {
        game.resetPress()
        game.setPreferences(this)

        var toast: Toast = makeText(this, game.getTargetSequence(), Toast.LENGTH_LONG)
        toast.show()
    }
}