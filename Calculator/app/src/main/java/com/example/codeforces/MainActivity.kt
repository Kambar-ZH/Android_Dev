package com.example.codeforces

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        val mToolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(mToolbar)

        var button = findViewById<Button>(R.id.more_operations_panel_button)
        button.setOnClickListener {
            val param: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.MATCH_PARENT, 1.0f
            )
            val param2: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.MATCH_PARENT, 0.0f
            )
            var panel = findViewById<LinearLayout>(R.id.operations_panel)
            var panel2 = findViewById<LinearLayout>(R.id.more_operations_panel)
            if (button.text == "⟩") {
                panel.layoutParams = param
                panel2.layoutParams = param2
                button.text = "⟨"
            } else {
                panel.layoutParams = param2
                panel2.layoutParams = param
                button.text = "⟩"
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // TODO
        return super.onOptionsItemSelected(item)
    }

    fun buttonClick(view: View) {
        val button = view as Button
        val text = button.text;

        viewModel.processSignal(text.toString())

        var equationText = findViewById<TextView>(R.id.equation_text_view)
        equationText.text = viewModel.equationText

        var resultText = findViewById<TextView>(R.id.result_text_view)
        resultText.text = viewModel.resultText
    }
}