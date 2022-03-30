package com.example.quizapp

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.ImageView
import android.widget.*
import android.widget.Toast
import androidx.core.content.ContextCompat

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {

    // Default and the first question position
    private var mCurrentPosition: Int = 1
    private var mQuestionsList: ArrayList<Questions>? = null
    private var mSelectedOptionPosition: Int = 0


    private lateinit var tvQuestion: TextView
    private lateinit var ivImage: ImageView
    private lateinit var progressBar: ProgressBar
    private lateinit var tvProgress: TextView
    private lateinit var optionOne: TextView
    private lateinit var optionTwo: TextView
    private lateinit var optionThree: TextView
    private lateinit var optionFour: TextView
    private lateinit var btnSubmit: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        mQuestionsList = Constant.getQuestions()
        initView()
        setQuestion()
        setListener()
        defaultOptionsView()

        optionOne.setOnClickListener(this)
        optionTwo.setOnClickListener(this)
        optionThree.setOnClickListener(this)
        optionFour.setOnClickListener(this)
        // Adding a click event for submit button.)
        // START
        btnSubmit.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_option_one -> {
                selectOptionView(optionOne, 1)
            }
            R.id.tv_option_two -> {
                selectOptionView(optionTwo, 2)
            }
            R.id.tv_option_three -> {
                selectOptionView(optionThree, 3)
            }
            R.id.tv_option_four -> {
                selectOptionView(optionFour, 4)
            }
            // Adding a click event for submit button. And change the questions and check the selected answers.)
            // START
            R.id.btn_submit -> {

                if (mSelectedOptionPosition == 0) {
                    mCurrentPosition++

                    when {
                        mCurrentPosition <= mQuestionsList!!.size -> {
                            setQuestion()
                        }
                        else -> {
                            Toast.makeText(this, "Quiz Completed", Toast.LENGTH_SHORT).show()
                        }

                    }
                } else {
                    val question = mQuestionsList?.get(mCurrentPosition - 1)
                    // This is to check if the answer is wrong
                    if (question!!.correctAnswer != mSelectedOptionPosition) {
                        answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
                    }
                    // This is for correct answer
                    answerView(question.correctAnswer, R.drawable.correct_option_border_bg)

                    if (mCurrentPosition == mQuestionsList!!.size) {
                        btnSubmit.text = "FINISH"
                    } else {
                        btnSubmit.text = "GO TO NEXT QUESTION"
                    }
                    mSelectedOptionPosition = 0
                }
            }
        }
    }

    //A function to set the view of selected option view.
    private fun selectOptionView(tv: TextView, selectedOptionNum: Int) {

        mSelectedOptionPosition = selectedOptionNum
        tv.setTextColor(
            Color.parseColor("#363A43")
        )
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this@QuizQuestionsActivity,
            R.drawable.select_option_border_bg
        )

    }


        private fun initView() {


            tvQuestion.findViewById<TextView>(R.id.tv_question)
            optionOne.findViewById<TextView>(R.id.tv_option_one)
            //optionTwo.findViewById<TextView>(R.id.tv_option_two)
            optionThree.findViewById<TextView>(R.id.tv_option_three)
            optionFour.findViewById<TextView>(R.id.tv_option_four)
            btnSubmit.findViewById<Button>(R.id.btn_submit)
        }



    private fun setListener() {
        optionOne.setOnClickListener(this)
        optionTwo.setOnClickListener(this)
        optionThree.setOnClickListener(this)
        optionFour.setOnClickListener(this)
        // Adding a click event for submit button.)
        // START
        btnSubmit.setOnClickListener(this)
    }

    private fun setQuestion() {
        setContentView(R.layout.activity_quiz_questions)

        // Getting the question from the list with the help of current position
        val question = mQuestionsList!![mCurrentPosition-1]

        defaultOptionsView()

        // Check here if the position of question is last then change the text of the button.)
        // START
        if (mCurrentPosition == mQuestionsList!!.size) {
            btnSubmit.text = "FINISH"
        } else {
            btnSubmit.text = "SUBMIT"
        }

        progressBar.progress = mCurrentPosition
        tvProgress.text = "$mCurrentPosition" + "/" + progressBar.max
        tvQuestion.text = question.questions
        ivImage.setImageResource(question.image)
        optionOne.text = question.optionOne
        optionTwo.text = question.optionTwo
        optionThree.text = question.optionThree
        optionFour.text = question.optionFour

    }

    /**
     * A function to set default options view when the new question is loaded or when the answer is reselected.
     */
    private fun defaultOptionsView() {

        val options = ArrayList<TextView>()
        options.add(0, optionOne)
        options.add(1, optionTwo)
        options.add(2, optionThree)
        options.add(3, optionFour)

        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this@QuizQuestionsActivity,
                R.drawable.default_option_border_bg
            )
        }
    }

    /**
     * A function for answer view which is used to highlight the answer is wrong or right.
     */

    private fun answerView(answer: Int, drawableView: Int) {

        when (answer) {

            1 -> {
                optionOne.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
            2 -> {
                optionOne.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
            3 -> {
                optionOne.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
            4 -> {
                optionOne.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
        }
    }

}

