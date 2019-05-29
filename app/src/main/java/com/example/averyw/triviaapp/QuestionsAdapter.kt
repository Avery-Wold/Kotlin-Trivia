package com.example.averyw.triviaapp

import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.averyw.triviaapp.R.id.*
import kotlinx.android.synthetic.main.questions_detail.*
import kotlinx.android.synthetic.main.questions_detail.view.*
import java.net.URLDecoder
import java.util.*
import java.util.Random

/**
 * Created by AveryW on 5/23/2019.
 */
class QuestionsAdapter(val questionList: QuestionList): RecyclerView.Adapter<QuestionViewHolder>(){

    var currentQuestion = 1

    override fun getItemCount(): Int {
        return 1
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int):QuestionViewHolder {
        val layoutInflator = LayoutInflater.from(parent?.context)
        val customView = layoutInflator.inflate(R.layout.questions_detail, parent, false)
        return QuestionViewHolder(customView)
    }

    override fun onBindViewHolder(holder: QuestionViewHolder?, position: Int) {
        val question = questionList.results.get(position)
        val rAanswer = listOf(question.correct_answer)
        val iAnswers = question.incorrect_answers
        val answers  = rAanswer.union(iAnswers)
        val randomAnswers = answers.shuffle()

        holder?.customView?.question_textView?.text = Html.fromHtml(question.question).toString()
        holder?.customView?.button1?.text = randomAnswers[0]
        holder?.customView?.button2?.text = randomAnswers[1]
        holder?.customView?.button3?.text = randomAnswers[2]
        holder?.customView?.button4?.text = randomAnswers[3]

        holder?.customView?.number_textView?.text = currentQuestion.toString() + " / " + questionList.results.size
        currentQuestion++
    }

    // Extension to shuffle answers/questions
    fun <T> Iterable<T>.shuffle(): List<T> {
        val list = this.toMutableList().apply {  }
        Collections.shuffle(list)
        return list
    }

}

class QuestionViewHolder(val customView: View): RecyclerView.ViewHolder(customView), View.OnClickListener {

//    internal lateinit var question_view: QuestionView
//    internal  lateinit var iOnRecyclerViewItemClickListener:
//
//
    override fun onClick(p0: View?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}