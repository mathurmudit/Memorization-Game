package com.example.project4

import android.content.Context
import android.content.SharedPreferences
import android.util.Log

class Game() {
    private var bestLevel : Int = 1
    private var currentLevel : Int = 1
    private var targetSequence : String = ""
    private var currentSequence : String = ""

    private var MA : String = "MainActivity"

    fun setTargetSequence() {
        targetSequence = generateSequence(currentLevel)
        Log.w(MA, "target sequence set to: " + targetSequence)
    }

    private fun generateSequence(length : Int) : String {
        // generate a random string of colors of the given length
        // be sure to include a space at the end
        var ret : String = ""

        for (i in 1 .. length) {
            var color = (1..4).random()
            if (color == 1) {
                ret += "red "
            } else if (color == 2) {
                ret += "green "
            } else if (color == 3) {
                ret += "yellow "
            } else if (color == 4) {
                ret += "blue "
            }

        }

        return ret
    }

    fun justStarted() : Boolean {
        return currentSequence == ""
    }

    fun getTargetSequence() : String {
        return targetSequence
    }

    private fun checkWon() {
        // check if the current sequence matches the target sequence
        // if so, start the next level
        if (currentSequence == targetSequence) {
            bestLevel += 1
            currentLevel +=1
            targetSequence += generateSequence(1)
            currentSequence = ""
            Log.w(MA, "user won level")
        } else {
            Log.w(MA, "user has not won yet")
        }
    }

    private fun checkLost() : Boolean {
        // check if the current sequence can no longer match the target sequence
        // if so, terminate the app

        val currentLength = currentSequence.length
        if (currentLength == 0) {
            Log.w(MA, "user continues playing because currentSequence length is 0")
            return false
        } else if (currentSequence.count { it == ' ' } == targetSequence.count { it == ' ' }) {
            Log.w(MA, "user lost because the currentSequence has the same # of colors as target, but not same string")
            return true
        }

        Log.w(MA, "checkLost is returning whether currentSequence is at the start of target")
        return targetSequence.indexOf(currentSequence) != 0
    }

    // each button press method here will add the color name to the currentSequence, then
    // checkWon() and checkLost()

    // button methods return true to continue play and false if the game is over

    fun redPress() : Boolean{
        currentSequence += "red "
        checkWon()
        return !(checkLost())
    }

    fun greenPress() : Boolean {
        currentSequence += "green "
        Log.w(MA, "green pressed, current sequence is now: " + currentSequence)
        checkWon()
        return !(checkLost())
    }

    fun yellowPress() : Boolean {
        currentSequence += "yellow "
        checkWon()
        return !(checkLost())
    }

    fun bluePress() : Boolean {
        currentSequence += "blue "
        checkWon()
        return !(checkLost())
    }

    fun resetPress() {
        bestLevel = 1
        currentLevel = 1
        targetSequence = generateSequence(currentLevel)
        currentSequence = ""
    }





    // call this every time a button is pushed, to store the current level in case they lose with
    // that push
    fun setPreferences(context: Context?) {
        // get a SharedPreferences.Editor
        var pref : SharedPreferences = context!!.getSharedPreferences(context.packageName + "_preferences", Context.MODE_PRIVATE)
        var editor : SharedPreferences.Editor = pref.edit()
        // with the editor, write the data (level stuff) to shared preferences
        Log.w(MA, "writing best level: " + bestLevel.toString() + " into shared preferences")
        editor.putInt("BEST_LEVEL", bestLevel)
        editor.commit()
    }

    // call this when instantiating the game instance in MainActivity, to retrieve the best level
    fun setLevel(context: Context?) {
        var pref : SharedPreferences = context!!.getSharedPreferences(context.packageName + "_preferences", Context.MODE_PRIVATE)
        currentLevel = pref.getInt("BEST_LEVEL", 1)
        bestLevel = currentLevel
        Log.w(MA, "current level set to: " + currentLevel.toString())
    }



}