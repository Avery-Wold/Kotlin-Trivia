package com.example.averyw.triviaapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_question.*
import okhttp3.*
import java.io.IOException

/**
 * Created by AveryW on 5/23/2019.
 */
class GetQuestionsActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)
        recyclerView_questions.layoutManager = LinearLayoutManager(this)
//       recyclerView_questions.adapter = QuestionsAdapter()
        fetchData()
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
                println(body)
                val questionList = gson.fromJson(body, QuestionList::class.java)
                runOnUiThread {
                    recyclerView_questions.adapter = QuestionsAdapter(questionList)
                }
            }

            override fun onFailure(call: Call?, e: IOException?) {
                println("Failed to execute request")
            }
        })
    }


}