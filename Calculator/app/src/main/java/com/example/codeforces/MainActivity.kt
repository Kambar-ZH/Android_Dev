package com.example.codeforces

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import java.util.*

class MainActivity : AppCompatActivity() {

    enum class State {
        Zero, AccumulateDigit, ComputePending, Compute, Separator, Clear
    }

    private var digits = arrayOf<String>("0", "1", "2", "3", "4", "5", "6", "7", "8", "9")
    var nonZeroDigits = arrayOf<String>("1", "2", "3", "4", "5", "6", "7", "8", "9")
    var operations = arrayOf<String>("+", "-", "×", "÷")
    var equals = arrayOf<String>("=")
    var separators = arrayOf<String>(".")
    private var clear = arrayOf<String>("⌫")

    var stackOfNumbers: Stack<String> = Stack<String>()
    var stackOfOperations: Stack<String> = Stack<String>()

    var currentState: State = State.Zero

    private fun processSignal(message: String) {
        when (currentState) {
            State.Zero -> processZeroState(message, false)
            State.AccumulateDigit -> processAccumulateDigitState(message, false)
            State.ComputePending -> processComputePendingState(message, false)
            State.Compute -> processAccumulateDigitState(message, false)
            State.Separator -> processSeparatorState(message, false)
            State.Clear -> processClearState(message, false)
        }
    }

    private fun processClearState(msg: String, incoming: Boolean) {
        if (incoming) {
            currentState = State.Clear
            stackOfNumbers.clear()
            stackOfOperations.clear()
            displayEquation("0")
        } else {
            processZeroState(msg, false)
        }
    }

    private fun processZeroState(msg: String, incoming: Boolean) {
        if (incoming) {
            currentState = State.Zero
        } else {
            if (nonZeroDigits.contains(msg)) {
                processAccumulateDigitState(msg, true)
            }
        }
    }

    private fun processAccumulateDigitState(msg: String, incoming: Boolean) {
        if (incoming) {
            currentState = State.AccumulateDigit
            if (stackOfNumbers.count() == 0) {
                stackOfNumbers.push(msg)
            } else {
                val number: String = stackOfNumbers.peek() + msg
                stackOfNumbers.pop()
                stackOfNumbers.push(number)
            }
            displayEquation(stackOfNumbers.peek())
        } else {
            when{
                digits.contains(msg) -> processAccumulateDigitState(msg, true)
                operations.contains(msg) -> processComputePendingState(msg, true)
                equals.contains(msg) -> processComputeState(msg, true)
                separators.contains(msg) -> processSeparatorState(msg, true)
                clear.contains(msg) -> processClearState(msg, true)
            }
        }
    }

    private fun processComputePendingState(msg: String, incoming: Boolean) {
        if (incoming) {
            currentState = State.ComputePending
            stackOfNumbers.push("")
            stackOfOperations.push(msg)
        } else {
            if (digits.contains(msg)) {
                processAccumulateDigitState(msg, true)
            }
            if (clear.contains(msg)) {
                processClearState(msg, true)
            }
        }
    }

    private fun processSeparatorState(msg: String, incoming: Boolean) {
        if (incoming) {
            currentState = State.Separator
            if (!stackOfNumbers.peek().contains(separators[0])) {
                val number: String = stackOfNumbers.peek() + msg
                stackOfNumbers.pop()
                stackOfNumbers.push(number)
                displayEquation(stackOfNumbers.peek().toString() + "0")
            }
        } else {
            processAccumulateDigitState(msg, false)
        }
    }

    private fun processComputeState(msg: String, incoming: Boolean) {
        if (incoming) {
            currentState = State.Compute
            if (stackOfNumbers.count() <= 1) {
                return
            }
            val remainingNumbers = Stack<String>()
            val remainingOperations = Stack<String>()
            while (stackOfNumbers.count() > 1) {
                val b = stackOfNumbers.peek().toFloat()
                stackOfNumbers.pop()
                val a = stackOfNumbers.peek().toFloat()
                stackOfNumbers.pop()
                var currentNumber = if (stackOfOperations.peek() == "×") {
                    (a * b).toString()
                } else if (stackOfOperations.peek() == "÷") {
                    (a / b).toString()
                } else {
                    remainingNumbers.push(b.toString())
                    stackOfNumbers.push(a.toString())
                    remainingOperations.push(stackOfOperations.peek())
                    continue
                }
                stackOfNumbers.push(currentNumber)
                stackOfOperations.pop()
            }
            remainingNumbers.push(stackOfNumbers.peek())
            stackOfNumbers.pop()
            while (remainingNumbers.count() > 1) {
                var b = remainingNumbers.peek().toFloat()
                remainingNumbers.pop()
                var a = remainingNumbers.peek().toFloat()
                remainingNumbers.pop()
                var currentNumber = ""
                if (remainingOperations.peek() == "+") {
                    currentNumber = (a + b).toString()
                } else if (remainingOperations.peek() == "-") {
                    currentNumber = (b - a).toString()
                }
                remainingNumbers.push(currentNumber)
                remainingOperations.pop()
            }
            stackOfNumbers.push(remainingNumbers.peek())
            stackOfOperations.clear()
            displayResult(stackOfNumbers.peek())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mToolbar = findViewById<Toolbar>(R.id.my_toolbar);
        setSupportActionBar(mToolbar);
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return super.onOptionsItemSelected(item)
    }

    private fun displayEquation(text: String) {
        val mainTextView = findViewById<TextView>(R.id.equation_text_view)
        mainTextView.text = text
    }
    private fun displayResult(text: String) {
        val mainTextView = findViewById<TextView>(R.id.result_text_view)
        mainTextView.text = text
    }


    fun buttonClick(view: View) {
        val button = view as Button
        val text = button.text;

        processSignal(text.toString())
    }
}