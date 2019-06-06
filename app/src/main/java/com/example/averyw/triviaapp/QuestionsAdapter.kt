package com.example.averyw.triviaapp

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.questions_detail.view.*
import java.util.*

/**
 * Created by AveryW on 5/23/2019.
 */
class QuestionsAdapter(val questionList: MutableList<Questions>): RecyclerView.Adapter<QuestionViewHolder>(){

    private var currentQuestion = 1

    override fun getItemCount(): Int = questionList.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int):QuestionViewHolder {
        val layoutInflator = LayoutInflater.from(parent?.context)
        val customView = layoutInflator.inflate(R.layout.questions_detail, parent, false)
        return QuestionViewHolder(customView)
    }

    override fun onBindViewHolder(holder: QuestionViewHolder?, position: Int) {
        val question = questionList.get(position)
        val rAanswer = listOf(question.correct_answer)
        val iAnswers = question.incorrect_answers
        val answers  = rAanswer.union(iAnswers)
        val randomAnswers = answers.shuffle()

        holder?.customView?.question_textView?.text = Html.fromHtml(question.question).toString()
        holder?.customView?.button1?.text = randomAnswers[0]
        holder?.customView?.button2?.text = randomAnswers[1]
        holder?.customView?.button3?.text = randomAnswers[2]
        holder?.customView?.button4?.text = randomAnswers[3]

        holder?.customView?.number_textView?.text = currentQuestion.toString() + " / " + 10
        currentQuestion++
        //holder?.correctAnswer = question.correct_answer
        notifyDataSetChanged()
    }

    // Extension to shuffle answers/questions
    fun <T> Iterable<T>.shuffle(): List<T> {
        val list = this.toMutableList().apply {  }
        Collections.shuffle(list)
        return list
    }
}

class QuestionViewHolder(val customView: View, answer: Questions? = null): RecyclerView.ViewHolder(customView) {
//    private var score = 0
//    var intent = Intent()
//
//    companion object {
//        val SCORE = "SCORE"
//    }
//    var correctAnswer = answer?.correct_answer
//
//    init {
//        customView.button1.setOnClickListener {
//            if (customView.button1.text == correctAnswer) {
//                score++
////                intent = Intent(customView.context, GetQuestionsActivity::class.java).apply {
////                    putExtra(SCORE, score)
////                }
////                customView.context.startActivity(intent)
//                Toast.makeText(customView.context, "You got it correct!", Toast.LENGTH_SHORT).show()
//            }
//            else{
//                intent = Intent(customView.context, GetQuestionsActivity::class.java).apply {
//                    putExtra(SCORE, score)
//                }
////                customView.context.startActivity(intent)
//                Toast.makeText(customView.context, "You got it wrong! The answer is: $correctAnswer", Toast.LENGTH_SHORT).show()
//            }
//            customView.context.startActivity(intent)
//        }
//
//        customView.button2.setOnClickListener {
//            if (customView.button2.text == correctAnswer) {
//                score++
//                intent = Intent(customView.context, GetQuestionsActivity::class.java).apply {
//                    putExtra(SCORE, score)
//                }
////                customView.context.startActivity(intent)
//                Toast.makeText(customView.context, "You got it correct!", Toast.LENGTH_SHORT).show()
//            }
//            else{
//                intent = Intent(customView.context, GetQuestionsActivity::class.java).apply {
//                    putExtra(SCORE, score)
//                }
////                customView.context.startActivity(intent)
//                Toast.makeText(customView.context, "You got it wrong! The answer is: $correctAnswer", Toast.LENGTH_SHORT).show()
//            }
//            customView.context.startActivity(intent)
//        }
//
//        customView.button3.setOnClickListener {
//            if (customView.button3.text == correctAnswer) {
//                score++
//                intent = Intent(customView.context, GetQuestionsActivity::class.java).apply {
//                    putExtra(SCORE, score)
//                }
////                customView.context.startActivity(intent)
//                Toast.makeText(customView.context, "You got it correct!", Toast.LENGTH_SHORT).show()
//            }
//            else{
//                intent = Intent(customView.context, GetQuestionsActivity::class.java).apply {
//                    putExtra(SCORE, score)
//                }
////                customView.context.startActivity(intent)
//                Toast.makeText(customView.context, "You got it wrong! The answer is: $correctAnswer", Toast.LENGTH_SHORT).show()
//            }
//            customView.context.startActivity(intent)
//        }
//
//        customView.button4.setOnClickListener {
//            if (customView.button4.text == correctAnswer) {
//                score++
//                intent = Intent(customView.context, GetQuestionsActivity::class.java).apply {
//                    putExtra(SCORE, score)
//                }
////                customView.context.startActivity(intent)
//                Toast.makeText(customView.context, "You got it correct!", Toast.LENGTH_SHORT).show()
//            }
//            else{
//                intent = Intent(customView.context, GetQuestionsActivity::class.java).apply {
//                    putExtra(SCORE, score)
//                }
////                customView.context.startActivity(intent)
//                Toast.makeText(customView.context, "You got it wrong! The answer is: $correctAnswer", Toast.LENGTH_SHORT).show()
//            }
//            customView.context.startActivity(intent)
//        }
//    }

}