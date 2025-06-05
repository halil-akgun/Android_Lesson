package com.android_lesson

import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import com.android_lesson.databinding.ActivitySBinding

class SActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivitySBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        viewBinding = ActivitySBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // load table
        var db = DbHelper(applicationContext)
        var persons = DbPersonDAO().getAll(db)
        loadTable(persons)

        // get random person
        val randomPerson = DbPersonDAO().getRandom(db, 2)
        for (prs1 in randomPerson) {
            Log.e("random person", "name: ${prs1.name}, age: ${prs1.age}")
        }

        db.close()

        // add person
        viewBinding.btnAddPerson.setOnClickListener {
            val name = viewBinding.textViewDbAddPersonName.text.toString().trim()
            val tel = viewBinding.textViewDbAddPersonTel.text.toString().trim()
            val ageText = viewBinding.textViewDbAddPersonAge.text.toString().trim()
            val heightText = viewBinding.textViewDbAddPersonHeight.text.toString().trim()

            if (name.isEmpty()) {
                Toast.makeText(applicationContext, "ENTER NAME", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (tel.isEmpty()) {
                Toast.makeText(applicationContext, "ENTER TEL", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (ageText.isEmpty()) {
                Toast.makeText(applicationContext, "ENTER AGE", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (heightText.isEmpty()) {
                Toast.makeText(applicationContext, "ENTER HEIGHT", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val age = try {
                ageText.toInt()
            } catch (e: Exception) {
                Toast.makeText(applicationContext, "AGE IS NOT A NUMBER", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val height = try {
                heightText.toDouble()
            } catch (e: Exception) {
                Toast.makeText(applicationContext, "HEIGHT IS NOT A NUMBER", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

            db = DbHelper(applicationContext)
            DbPersonDAO().save(db, name, tel, age, height)
            persons = DbPersonDAO().getAll(db)
            db.close()

            Toast.makeText(applicationContext, "Added person: $name", Toast.LENGTH_SHORT).show()

            // clear form
            viewBinding.textViewDbAddPersonName.text.clear()
            viewBinding.textViewDbAddPersonTel.text.clear()
            viewBinding.textViewDbAddPersonAge.text.clear()
            viewBinding.textViewDbAddPersonHeight.text.clear()

            loadTable(persons)
        }

        // search
        viewBinding.textViewDbAddPersonSearch.addTextChangedListener { editable ->
            val query = editable.toString()
            db = DbHelper(applicationContext)
            val filteredPersons = DbPersonDAO().search(db, query)
            db.close()

            loadTable(filteredPersons)
        }

    }

    private fun loadTable(persons: List<DbPerson> = emptyList()) {
        val table = findViewById<TableLayout>(R.id.tableLayout)
        table.removeAllViews()

        // header
        val header = TableRow(this)
        listOf("Name", "Phone", "Age", "Height").forEach {
            val tv = TextView(this)
            tv.text = it
            tv.setPadding(8, 8, 8, 8)
            tv.setTypeface(null, Typeface.BOLD)
            header.addView(tv)
        }
        table.addView(header)

        // rows
        for (person in persons) {
            val row = TableRow(this)
            listOf(
                person.name,
                person.tel,
                person.age.toString(),
                person.height.toString()
            ).forEach {
                val tv = TextView(this)
                tv.text = it
                tv.setPadding(8, 8, 8, 8)
                row.addView(tv)
            }

            row.setOnClickListener {
                showUpdateDialog(person)
            }

            table.addView(row)
        }
    }

    private fun showUpdateDialog(person: DbPerson) {
        val dialogView = layoutInflater.inflate(R.layout.dialog_update_person, null)

        val nameEdit = dialogView.findViewById<EditText>(R.id.editName)
        val telEdit = dialogView.findViewById<EditText>(R.id.editTel)
        val ageEdit = dialogView.findViewById<EditText>(R.id.editAge)
        val heightEdit = dialogView.findViewById<EditText>(R.id.editHeight)

        // Set initial values
        nameEdit.setText(person.name)
        telEdit.setText(person.tel)
        ageEdit.setText(person.age.toString())
        heightEdit.setText(person.height.toString())

        AlertDialog.Builder(this)
            .setTitle("Update or Delete")
            .setView(dialogView)
            .setPositiveButton("Update") { _, _ ->
                val db = DbHelper(applicationContext)
                DbPersonDAO().update(
                    db,
                    person.id,
                    nameEdit.text.toString(),
                    telEdit.text.toString(),
                    ageEdit.text.toString().toInt(),
                    heightEdit.text.toString().toDouble()
                )
                db.close()
                loadTable()
            }
            .setNegativeButton("Delete") { _, _ ->
                val db = DbHelper(applicationContext)
                DbPersonDAO().delete(db, person.id)
                db.close()
                loadTable()
            }
            .setNeutralButton("Cancel", null)
            .show()
    }
}