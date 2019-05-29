package com.example.averyw.triviaapp

/**
 * Created by AveryW on 5/23/2019.
 */


class QuestionList(val results: List<Questions>)

class Questions(val question: String, val correct_answer: String, val incorrect_answers: List<String>)

