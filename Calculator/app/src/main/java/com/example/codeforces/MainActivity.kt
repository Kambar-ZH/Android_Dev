package com.example.codeforces

import android.app.Activity
import android.content.res.Configuration
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import com.example.codeforces.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        handleOrientation(this.resources.configuration.orientation)

        setSupportActionBar(binding.toolbar)

        setMoreOperationsListener()

        setScrollingTextViews()
    }

    private fun setMoreOperationsListener() {
        val button = binding.moreOperationsPanelButton
        button.setOnClickListener {
            val operationsPanel = binding.operationsPanel.root
            val moreOperationsPanel = binding.moreOperationsPanel.root

            toggleButton(button)
            toggleVisibility(operationsPanel)
            toggleVisibility(moreOperationsPanel)
        }
    }

    private fun setScrollingTextViews() {
        binding.equationTextView.movementMethod = ScrollingMovementMethod()
        binding.resultTextView.movementMethod = ScrollingMovementMethod()
    }

    private fun toggleButton(button: Button) {
        button.text = if (button.text == getString(R.string.left_arrow)) getString(R.string.right_arrow) else getString(R.string.left_arrow)
    }

    private fun toggleVisibility(view: View) {
        view.visibility = if (view.visibility == View.GONE) View.VISIBLE else View.GONE
    }

    fun buttonClick(view: View) {
        viewModel.processSignal(view.id)

        binding.equationTextView.text = viewModel.equationText

        binding.resultTextView.text = viewModel.resultText
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        handleOrientation(newConfig.orientation)
    }

    private fun handleOrientation(orientation: Int) {
        if (orientation === Configuration.ORIENTATION_LANDSCAPE) {
            binding.moreOperationsPanelButton.visibility = View.GONE
            binding.operationsPanel.root.visibility = View.VISIBLE
            binding.moreOperationsPanel.root.visibility = View.VISIBLE
        } else if (orientation === Configuration.ORIENTATION_PORTRAIT) {
            binding.moreOperationsPanelButton.visibility = View.VISIBLE
            binding.operationsPanel.root.visibility = View.VISIBLE
            binding.moreOperationsPanel.root.visibility = View.GONE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString("equationText", binding.equationTextView.text.toString())
        outState.putString("resultText", binding.resultTextView.text.toString())
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        binding.equationTextView.text = savedInstanceState.getString("equationText")
        binding.resultTextView.text = savedInstanceState.getString("resultText")
        super.onRestoreInstanceState(savedInstanceState)
    }
}