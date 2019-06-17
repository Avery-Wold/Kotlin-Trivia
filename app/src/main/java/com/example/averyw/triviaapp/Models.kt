package com.example.averyw.triviaapp

/**
 * Created by AveryW on 5/23/2019.
 */


class QuestionList(val results: List<Questions>)

class Questions(
        var question: String = "",
        var correct_answer: String = "",
        var incorrect_answers: List<String> = emptyList()
)

data class Categories(
        var category: String,
        var id: Int
)


