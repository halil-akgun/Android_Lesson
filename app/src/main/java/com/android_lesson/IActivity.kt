package com.android_lesson

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android_lesson.databinding.ActivityIBinding

class IActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityIBinding

    private val countries = ArrayList<String>()

    private lateinit var dataAdapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        viewBinding = ActivityIBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        countries.add("Belgium")
        countries.add("Palestine")
        countries.add("India")
        countries.add("USA")
        countries.add("UAE")
        countries.add("Nepal")
        countries.add("Pakistan")
        countries.add("Sri Lanka")

        // 3 ways to create an adapter
//        dataAdapter = ArrayAdapter(this@IActivity, android.R.layout.simple_spinner_item, android.R.id.text1, countries)
//        dataAdapter = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, android.R.id.text1, countries)
//        dataAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, android.R.id.text1, countries)
        dataAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1, countries)

        viewBinding.spinner.adapter = dataAdapter

        viewBinding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, index: Int, id: Long) {
                Toast.makeText(applicationContext, "You selected ${countries[index]}", Toast.LENGTH_LONG).show()
            }
        }

        viewBinding.buttonSpinnerShow.setOnClickListener {
            Toast.makeText(applicationContext, "You selected: ${countries[viewBinding.spinner.selectedItemPosition]}", Toast.LENGTH_LONG).show()
        }
    }
}