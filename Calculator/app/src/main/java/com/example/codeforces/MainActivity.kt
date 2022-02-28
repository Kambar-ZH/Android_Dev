package com.example.codeforces

import android.opengl.Visibility
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

    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        val mToolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(mToolbar)

        var button = findViewById<Button>(R.id.more_operations_panel_button)
        button.setOnClickListener {
            var operationsPanel = findViewById<LinearLayout>(R.id.operations_panel)
            var moreOperationsPanel = findViewById<LinearLayout>(R.id.more_operations_panel)
            toggleButton(button)
            toggleVisibility(operationsPanel)
            toggleVisibility(moreOperationsPanel)
        }
    }

    private fun toggleButton(button: Button) {
        if (button.text == "⟨") {
            button.text = "⟩"
        } else {
            button.text = "⟨"
        }
    }

    private fun toggleVisibility(linearLayout: LinearLayout) {
        if (linearLayout.visibility == View.GONE) {
            linearLayout.visibility = View.VISIBLE
        } else {
            linearLayout.visibility = View.GONE
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

        val equationText = findViewById<TextView>(R.id.equation_text_view)
        equationText.text = viewModel.equationText

        val resultText = findViewById<TextView>(R.id.result_text_view)
        resultText.text = viewModel.resultText
    }
}