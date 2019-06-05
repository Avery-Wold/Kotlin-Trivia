package com.example.averyw.triviaapp

import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.text.Html
import android.view.View
import android.widget.Toast
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_question.*
import kotlinx.android.synthetic.main.questions_detail.*
import okhttp3.*
import java.io.IOException
import java.util.*

/**
 * Created by AveryW on 5/23/2019.
 */
class GetQuestionsActivity : AppCompatActivity() {


    var questions : MutableList<Questions> = ArrayList()
//    val questionList: MutableList<Questions>
    // new
    var index = -1
    var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_question)
//        recyclerView_questions.layoutManager = LinearLayoutManager(this)
//        setContentView(R.layout.questions_detail)
//       recyclerView_questions.adapter = QuestionsAdapter()

        // new
        setContentView(R.layout.questions_detail)
        button1.setOnClickListener(clickListener)
        button2.setOnClickListener(clickListener)
        button3.setOnClickListener(clickListener)
        button4.setOnClickListener(clickListener)

        fetchData()
    }

    private val clickListener: View.OnClickListener = View.OnClickListener { view ->
        var correctAnswer = Html.fromHtml(questions[index].correct_answer).toString()
        when (view.id) {
            R.id.button1-> {
                if(button1.text == correctAnswer){
                    score++
                } else {
                    Toast.makeText(this, "Sorry, the correct answer was $correctAnswer", Toast.LENGTH_SHORT).show()
                }
            }
            R.id.button2-> {
                if(button2.text == correctAnswer){
                    score++
                } else {
                    Toast.makeText(this, "Sorry, the correct answer was $correctAnswer", Toast.LENGTH_SHORT).show()
                }
            }
            R.id.button3-> {
                if(button3.text == correctAnswer){
                    score++
                } else {
                    Toast.makeText(this, "Sorry, the correct answer was $correctAnswer", Toast.LENGTH_SHORT).show()
                }
            }
            R.id.button4-> {
                if(button4.text == correctAnswer){
                    score++
                } else {
                    Toast.makeText(this, "Sorry, the correct answer was $correctAnswer", Toast.LENGTH_SHORT).show()
                }
            }
        }
        updateQuestion()
    }

    private fun fetchData() {
        val difficulty = intent.getStringExtra(GET_DIFFICULTY)
        val questionsUrl = "https://opentdb.com/api.php?amount=10&difficulty=$difficulty&type=multiple"
        val client = OkHttpClient()
        val request = Request.Builder().url(questionsUrl).build()

        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call?, response: Response?) {
                val body = response?.body()?.string()
                val gson = GsonBuilder().create()
                val questionList = gson.fromJson(body, QuestionList::class.java)
                runOnUiThread {
                    for (i in 0..questionList.results.size - 1){
                        val currentObject = questionList.results[i]
                        val obj = Questions()
                        obj.question = currentObject.question
                        obj.incorrect_answers = currentObject.incorrect_answers
                        obj.correct_answer = currentObject.correct_answer
                        questions.add(obj)
//                        recyclerView_questions.adapter = QuestionsAdapter(questions)
                    }
                    // new
                    getQuestion()
                }
            }

            override fun onFailure(call: Call?, e: IOException?) {
                println("Failed to execute request")
            }
        })
    }

    fun getQuestion(){
        if (index == -1) {
            index++
            val question = questions[index]
            val rAanswer = listOf(question.correct_answer)
            val iAnswers = question.incorrect_answers
            val answers  = rAanswer.union(iAnswers)
            val randomAnswers = answers.shuffle()
            question_textView.text = Html.fromHtml(questions[index].question).toString()
            button1.text = Html.fromHtml(randomAnswers[0]).toString()
            button2.text = Html.fromHtml(randomAnswers[1]).toString()
            button3.text = Html.fromHtml(randomAnswers[2]).toString()
            button4.text = Html.fromHtml(randomAnswers[3]).toString()
            number_textView.text = "${index + 1} / ${questions.size}"
        }
    }

    fun updateQuestion(){
        index++
        if (index < questions.size){
            val question = questions[index]
            val rAanswer = listOf(question.correct_answer)
            val iAnswers = question.incorrect_answers
            val answers  = rAanswer.union(iAnswers)
            val randomAnswers = answers.shuffle()
            question_textView.text = Html.fromHtml(questions[index].question).toString()
            button1.text = Html.fromHtml(randomAnswers[0]).toString()
            button2.text = Html.fromHtml(randomAnswers[1]).toString()
            button3.text = Html.fromHtml(randomAnswers[2]).toString()
            button4.text = Html.fromHtml(randomAnswers[3]).toString()
            number_textView.text = "${index + 1} / ${questions.size}"
        } else {
            // add ending screen
            val dialog = AlertDialog.Builder(this)
            dialog.setTitle("Your score")
            dialog.setMessage("You have answered $score out of ${questions.size} correct")
            dialog.setPositiveButton("Close", DialogInterface.OnClickListener(){
                dialogInterface: DialogInterface, i: Int -> dialogInterface.dismiss()
                finish()
            })
            dialog.show()
        }
    }

    // new
    // Extension to shuffle answers/questions
    fun <T> Iterable<T>.shuffle(): List<T> {
        val list = this.toMutableList().apply {  }
        Collections.shuffle(list)
        return list
    }
}