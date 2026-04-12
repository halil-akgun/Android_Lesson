package com.android_lesson

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.android_lesson.databinding.ActivityZ3Binding

class Z3Activity : AppCompatActivity() {

    // ViewBinding
//    private lateinit var viewBinding: ActivityZ3Binding

    // DataBinding
    private lateinit var dataBinding: ActivityZ3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // ViewBinding
//        viewBinding = ActivityZ3Binding.inflate(layoutInflater)
//        setContentView(viewBinding.root)

        // DataBinding
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_z3)
        // Passing Activity reference to XML via DataBinding (for handling events/clicks)
        dataBinding.z3Activity = this

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // ViewBinding way
//        viewBinding.textViewMVVMResult.text = "0"
//        viewBinding.buttonMVVMAdd.setOnClickListener {
//            val number1 = viewBinding.editTextMVVMNumber1.text.toString().toInt()
//            val number2 = viewBinding.editTextMVVMNumber2.text.toString().toInt()
//            val result = number1 + number2
//            viewBinding.textViewMVVMResult.text = result.toString()
//        }
//        viewBinding.buttonMVVMMul.setOnClickListener {
//            val number1 = viewBinding.editTextMVVMNumber1.text.toString().toInt()
//            val number2 = viewBinding.editTextMVVMNumber2.text.toString().toInt()
//            val result = number1 * number2
//            viewBinding.textViewMVVMResult.text = result.toString()
//        }

        // DataBinding way
//        dataBinding.textViewMVVMResult.text = "0"
        dataBinding.result = "0" // result variable is used in XML
        // fun buttonAdd()
        // fun buttonMul()
    }

    fun buttonAdd() {
        val number1 = dataBinding.editTextMVVMNumber1.text.toString().toInt()
        val number2 = dataBinding.editTextMVVMNumber2.text.toString().toInt()
        val result = number1 + number2
//        dataBinding.textViewMVVMResult.text = result.toString()
        dataBinding.result = result.toString() // result variable is used in XML
    }

    fun buttonMul() {
        val number1 = dataBinding.editTextMVVMNumber1.text.toString().toInt()
        val number2 = dataBinding.editTextMVVMNumber2.text.toString().toInt()
        val result = number1 * number2
//        dataBinding.textViewMVVMResult.text = result.toString()
        dataBinding.result = result.toString() // result variable is used in XML
    }
}