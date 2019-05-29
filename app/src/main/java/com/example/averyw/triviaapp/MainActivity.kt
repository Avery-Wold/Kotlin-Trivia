package com.example.averyw.triviaapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

const val GET_DIFFICULTY = ""

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun getEasy(view: View) {
        val doEasy = "easy"
        val intent = Intent(this, GetQuestionsActivity::class.java).apply {
            putExtra(GET_DIFFICULTY, doEasy)
        }
        startActivity(intent)
    }

    fun getMed(view: View) {
        val doMed = "medium"
        val intent = Intent(this, GetQuestionsActivity::class.java).apply {
            putExtra(GET_DIFFICULTY, doMed)
        }
        startActivity(intent)
    }

    fun getHard(view: View) {
        val doHard = "hard"
        val intent = Intent(this, GetQuestionsActivity::class.java).apply {
            putExtra(GET_DIFFICULTY, doHard)
        }
        startActivity(intent)
    }
}
