package com.android_lesson

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import androidx.activity.addCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android_lesson.databinding.ActivityCBinding
import androidx.core.content.edit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class CActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityCBinding
    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        viewBinding = ActivityCBinding.inflate(layoutInflater)
        setContentView(viewBinding.root) // setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activityC)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Navigate to a new activity when the button is clicked
        viewBinding.goToD.setOnClickListener {
            val intent = Intent(this@CActivity, DActivity::class.java)
            startActivity(intent)
        }


        // data receiving from one activity to another
//        val name = intent.getStringExtra("name")
//        val age = intent.getIntExtra("age", 0)
//        val text = "$name ($age)"
        val person = intent.getSerializableExtra("person") as? Person
        if (person != null) {
            val text = "${person.name} (${person.age})"
            val textView = findViewById<TextView>(R.id.textView3)
            textView.text = text
        } else {
            val textView = findViewById<TextView>(R.id.textView3)
            textView.text = "No user information received."
        }

        //         *****  SHARED PREFERENCES *****
        // reach shared preferences
        val sharedPreferences = getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
        var name = sharedPreferences.getString("name", "name not found")
        var age = sharedPreferences.getInt("age", 0)
        var isAdult = sharedPreferences.getBoolean("isAdult", false)
        var isAgree = sharedPreferences.getBoolean("isAgree", false)
        var friendList = sharedPreferences.getStringSet("friends", emptySet())

        Log.e("SharedPreferences", "Name: $name")
        Log.e("SharedPreferences", "Age: $age")
        Log.e("SharedPreferences", "Is Adult: $isAdult")
        Log.e("SharedPreferences", "Is Agree: $isAgree")
        Log.e("SharedPreferences", "Friend List: $friendList")

        // remove shared preferences
        sharedPreferences.edit {
            remove("name")
            remove("age")
            remove("isAdult")
            remove("isAgree")
            remove("friends")
        }
        name = sharedPreferences.getString("name", "name not found")
        age = sharedPreferences.getInt("age", 0)
        isAdult = sharedPreferences.getBoolean("isAdult", false)
        isAgree = sharedPreferences.getBoolean("isAgree", false)
        friendList = sharedPreferences.getStringSet("friends", emptySet())
        Log.e("SharedPreferences", "Name: $name")
        Log.e("SharedPreferences", "Age: $age")
        Log.e("SharedPreferences", "Is Adult: $isAdult")
        Log.e("SharedPreferences", "Is Agree: $isAgree")
        Log.e("SharedPreferences", "Friend List: $friendList")



        // enable javaScript
//        viewBinding.webView.settings.javaScriptEnabled = true


        // Define the WebView
        webView = findViewById(R.id.webView)
        val webSettings: WebSettings = webView.settings

        // enable javaScript
        webSettings.javaScriptEnabled = true

        // Add a WebViewClient (for redirect control)
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url ?: "")
                return true // Keep the redirect inside the WebView
            }
        }

        viewBinding.btnHerkul.setOnClickListener {
            webView.loadUrl("https://herkul.org")
        }

        viewBinding.btnGoogle.setOnClickListener {
            webView.loadUrl("https://www.google.com")
        }

        // Handle back button press
        onBackPressedDispatcher.addCallback(this) {
            if (webView.canGoBack()) {
                webView.goBack() // Go to the previous page in the WebView
            } else {
                super.onBackPressed() // Normal back button behavior
            }
        }
    }
}