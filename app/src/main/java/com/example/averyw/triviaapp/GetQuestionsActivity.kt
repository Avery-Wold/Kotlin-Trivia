package com.example.averyw.triviaapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.text.Html
import android.view.View
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.questions_detail.*
import okhttp3.*
import java.io.IOException
import java.util.*

/**
 * Created by AveryW on 5/23/2019.
 */

const val TOTAL_SCORE = ""

class GetQuestionsActivity : AppCompatActivity() {

    var questions : MutableList<Questions> = ArrayList()
    private var index = -1
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.questions_detail)

        val cat = intent.getStringExtra(GET_CAT)
        supportActionBar?.title = cat + " Questions"

        button1.setOnClickListener(clickListener)
        button2.setOnClickListener(clickListener)
        button3.setOnClickListener(clickListener)
        button4.setOnClickListener(clickListener)

        sflMockContent.startShimmerAnimation()
        Handler().postDelayed( {
            sflMockContent.visibility = View.GONE
            llRealContent.visibility = View.VISIBLE
        },2000)

        fetchData()
    }

    private val clickListener: View.OnClickListener = View.OnClickListener { view ->
        var correctAnswer = Html.fromHtml(questions[index].correct_answer).toString()
        when (view.id) {
            R.id.button1-> {
                if(button1.text == correctAnswer){
                    score++
                }
            }
            R.id.button2-> {
                if(button2.text == correctAnswer){
                    score++
                }
            }
            R.id.button3-> {
                if(button3.text == correctAnswer){
                    score++
                }
            }
            R.id.button4-> {
                if(button4.text == correctAnswer){
                    score++
                }
            }
        }
        updateQuestion()
    }

    private fun fetchData() {
        var difficulty: String? = intent.getStringExtra(GET_DIFFICULTY)
        var id: Int? = intent.getIntExtra(GET_ID, -1)
        val questionsUrl = "https://opentdb.com/api.php?amount=10&category=$id&difficulty=$difficulty&type=multiple"
        val client = OkHttpClient()
        val request = Request.Builder().url(questionsUrl).build()

        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call?, response: Response?) {
                if(response?.code() == 200) {
                    val body = response?.body()?.string()
                    val gson = GsonBuilder().create()
                    val questionList = gson.fromJson(body, QuestionList::class.java)
                    runOnUiThread {
                        for (i in 0..questionList.results.size - 1) {
                            val currentObject = questionList.results.get(i)
                            val obj = Questions()
                            obj.question = currentObject.question
                            obj.incorrect_answers = currentObject.incorrect_answers
                            obj.correct_answer = currentObject.correct_answer
                            questions.add(obj)
                        }
                        getQuestion()
                    }
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

    private fun updateQuestion(){
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
            intent = Intent(this, ResultsActivity::class.java).apply {
                putExtra(TOTAL_SCORE, score)
            }
            startActivity(intent)
        }
    }

    // Extension to shuffle answers/questions
    private fun <T> Iterable<T>.shuffle(): List<T> {
        val list = this.toMutableList().apply {  }
        Collections.shuffle(list)
        return list
    }
}