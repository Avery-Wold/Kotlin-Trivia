package com.example.averyw.triviaapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_results.*

class ResultsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)

        getResults()
    }

    private fun getResults(){
        val score = intent.getIntExtra(TOTAL_SCORE, -1)
        val numOfQuestion = 10

        var percentRight = score.toDouble() / numOfQuestion.toDouble()
        var totalpercentRight = percentRight * 100
        if (totalpercentRight < 40){
            results_textView.text = "Not Good"
        } else if(totalpercentRight < 70){
            results_textView.text = "Almost"
        } else if(totalpercentRight < 10){
            results_textView.text = "Nice job dumbass"
        } else{
            results_textView.text = "Good job!"
        }
        score_textView.text = "You got $score out of $numOfQuestion correct"
    }

    fun tryAgain(view: View){
        intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
