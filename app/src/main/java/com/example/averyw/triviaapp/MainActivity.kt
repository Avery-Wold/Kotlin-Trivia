package com.example.averyw.triviaapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import com.example.averyw.triviaapp.R.array.category_list
import kotlinx.android.synthetic.main.activity_main.*

const val GET_DIFFICULTY = "GET_DIFFICULTY"
const val GET_ID = "GET_ID"
const val GET_CAT = "GET_CAT"

class MainActivity : AppCompatActivity() {
    var id : Int = 0
    var cat: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = ArrayAdapter.createFromResource(this, category_list, R.layout.custom_spinner)
        spinner.adapter = adapter
    }

    fun getEasy(view: View) {
        getCategories()
        val doEasy = "easy"
        if(id != 100) {
            val intent = Intent(this, GetQuestionsActivity::class.java).apply {
                putExtra(GET_DIFFICULTY, doEasy)
                putExtra(GET_ID, id)
                putExtra(GET_CAT, cat)
            }
            startActivity(intent)
        } else {
            showDialog()
        }
    }

    fun getMed(view: View) {
        getCategories()
        val doMed = "medium"
        if(id != 100) {
            val intent = Intent(this, GetQuestionsActivity::class.java).apply {
                putExtra(GET_DIFFICULTY, doMed)
                putExtra(GET_ID, id)
                putExtra(GET_CAT, cat)
            }
            startActivity(intent)
        } else {
            showDialog()
        }
    }

    fun getHard(view: View) {
        getCategories()
        val doHard = "hard"
        if(id != 100) {
            val intent = Intent(this, GetQuestionsActivity::class.java).apply {
                putExtra(GET_DIFFICULTY, doHard)
                putExtra(GET_ID, id)
                putExtra(GET_CAT, cat)
            }
            startActivity(intent)
        } else {
            showDialog()
        }
    }

    private fun getCategories(){
        id = spinner.selectedItemId.toInt()
        cat = spinner.selectedItem.toString()
        when(id){
            0 -> id = 100
            1 -> id = 0
            2 -> id = 10
            3 -> id = 26
            4 -> id = 18
            5 -> id = 9
            6 -> id = 23
            7 -> id = 11
            8 -> id = 12
            9 -> id = 17
            10 -> id = 21
            11 -> id = 14
            12 -> id = 15
        }
    }

    private fun showDialog() {
        val builder = android.app.AlertDialog.Builder(this, R.style.AlertDialogCustom)
        builder.setTitle("Uh Oh")
        builder.setMessage("You need to select a category!")
        builder.setPositiveButton("OK"){_,_ -> }
        val dialog: android.app.AlertDialog = builder.create()
        dialog.setCancelable(false)
        dialog.setCanceledOnTouchOutside(false)
        dialog.show()
    }
}
