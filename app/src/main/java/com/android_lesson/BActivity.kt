package com.android_lesson

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android_lesson.databinding.ActivityBBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Calendar

class BActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityBBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        viewBinding = ActivityBBinding.inflate(layoutInflater)
        setContentView(viewBinding.root) // setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activityB)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        viewBinding.switch1.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                Log.e("BActivity", "Agree")
            } else {
                Log.e("BActivity", "Disagree")
            }
        }

        viewBinding.toggleButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                Log.e("BActivity", "Adult")
            } else {
                Log.e("BActivity", "Child")
            }
        }

        viewBinding.checkBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                Log.e("BActivity", "Checkbox Checked")
            } else {
                Log.e("BActivity", "Checkbox Unchecked")
            }
        }

        viewBinding.radioButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                Log.e("BActivity", "RadioButton Checked")
            } else {
                Log.e("BActivity", "RadioButton Unchecked")
            }
        }

        viewBinding.radioButton2.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                Log.e("BActivity", "RadioButton2 Checked")
            } else {
                Log.e("BActivity", "RadioButton2 Unchecked")
            }
        }

        viewBinding.editTextDate.setOnClickListener {
            val calender = Calendar.getInstance()
            val year = calender.get(Calendar.YEAR)
            val month = calender.get(Calendar.MONTH)
            val day = calender.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(this, { _, year, month, day ->
                viewBinding.editTextDate.setText("$year-${month + 1}-$day")
            }, year, month, day)
            datePickerDialog.setTitle("Select Date")
            datePickerDialog.setButton(DatePickerDialog.BUTTON_POSITIVE, "OK", datePickerDialog)
            datePickerDialog.setButton(DatePickerDialog.BUTTON_NEGATIVE, "Cancel", datePickerDialog)
            datePickerDialog.show()
        }

        viewBinding.editTextTime.setOnClickListener {
            val calender = Calendar.getInstance()
            val hour = calender.get(Calendar.HOUR_OF_DAY)
            val minute = calender.get(Calendar.MINUTE)

            val timePickerDialog = TimePickerDialog(this, { _, hour, minute ->
                viewBinding.editTextTime.setText("$hour:$minute")
            }, hour, minute, true)
            timePickerDialog.setTitle("Select Time")
            timePickerDialog.setButton(TimePickerDialog.BUTTON_POSITIVE, "OK", timePickerDialog)
            timePickerDialog.setButton(TimePickerDialog.BUTTON_NEGATIVE, "Cancel", timePickerDialog)
            timePickerDialog.show()
        }


        // data sending from one activity to another
        viewBinding.button11.setOnClickListener {
            val intent = Intent(this@BActivity, CActivity::class.java)
            val name = viewBinding.editTextText.text.toString()
            val age = viewBinding.editTextNumber.text.toString().toIntOrNull()
            val isAdult = viewBinding.toggleButton.isChecked
            val isAgree = viewBinding.switch1.isChecked
            val checkbox = viewBinding.checkBox.isChecked
            val radioButton = viewBinding.radioButton.isChecked
            val radioButton2 = viewBinding.radioButton2.isChecked
//            intent.putExtra("name", name)
//            intent.putExtra("age", age)
            val person = Person(name, age ?: 0)
            intent.putExtra("person", person)
            Log.e("BActivity", "isAdult: $isAdult")
            Log.e("BActivity", "isAgree: $isAgree")
            Log.e("BActivity", "checkbox: $checkbox")
            Log.e("BActivity", "radioButton: $radioButton")
            Log.e("BActivity", "radioButton2: $radioButton2")

            // "shared preferences" is a way to store data on the device (location: data/data/package_name/shared_prefs)
            val sp = getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
            // old way
//            val editor = sp.edit()
//            editor.putString("name", name)
//            editor.putInt("age", age ?: 0)
//            editor.putBoolean("isAdult", isAdult)
//            editor.putBoolean("isAgree", isAgree)
//            editor.putBoolean("checkbox", checkbox)
//            editor.putBoolean("radioButton", radioButton)
//            editor.putBoolean("radioButton2", radioButton2)
//            editor.apply()

            // new way
            sp.edit {
                putString("name", name)
                putInt("age", age ?: 0)
                putBoolean("isAdult", isAdult)
                putBoolean("isAgree", isAgree)
                putStringSet("friends", setOf("John", "Jane"))
            }

            //         *****  PREFERENCES DATASTORE  *****
            // "Preferences DataStore" is a way to store data on the device (location: data/data/package_name/files)
            val ap = AppPref(this)
            val job = CoroutineScope(Dispatchers.Main).launch {
                /*
                When you use CoroutineScope in two separate classes to access
                the same DataStore file, it can cause a conflict.
                DataStore must be a singleton because only one active instance is allowed for a given file.
                To avoid this, always create a single shared instance of DataStore and
                access it across your app instead of creating a new one in each class.
                 */

                // save
                ap.saveName(name)
                ap.saveAge(age ?: 0)
                ap.saveIsAdult(isAdult)
                ap.saveFriends(setOf("John", "Jane"))

                // read
                var dsName = ap.getName()
                var dsAge = ap.getAge()
                var dsIsAdult = ap.getIsAdult()
                var dsHeight = ap.getHeight()
                var dsFriends = ap.getFriends()
                Log.e("Preferences Datastore", "Name: $dsName")
                Log.e("Preferences Datastore", "Age: $dsAge")
                Log.e("Preferences Datastore", "Is Adult: $dsIsAdult")
                Log.e("Preferences Datastore", "Height: $dsHeight")
                Log.e("Preferences Datastore", "Friends: $dsFriends")

                // remove
                ap.deleteName()
                ap.deleteAge()
                ap.deleteIsAdult()
                ap.deleteHeight()
                ap.deleteFriends()
                dsName = ap.getName()
                dsAge = ap.getAge()
                dsIsAdult = ap.getIsAdult()
                dsHeight = ap.getHeight()
                dsFriends = ap.getFriends()
                Log.e("Preferences Datastore", "Name: $dsName")
                Log.e("Preferences Datastore", "Age: $dsAge")
                Log.e("Preferences Datastore", "Is Adult: $dsIsAdult")
                Log.e("Preferences Datastore", "Height: $dsHeight")
                Log.e("Preferences Datastore", "Friends: $dsFriends")
            }

            startActivity(intent)
        }
    }
}