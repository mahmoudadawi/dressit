package hu.attilavegh.dressit.ui.activities

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.Selection
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import hu.attilavegh.dressit.Application
import hu.attilavegh.dressit.R
import hu.attilavegh.dressit.models.UserModel
import hu.attilavegh.dressit.services.firebase.FirebaseDataService
import javax.inject.Inject


class InitialDataEntryActivity : AppCompatActivity() {

    @Inject lateinit var firebaseService: FirebaseDataService

    private lateinit var saveDataButton: Button

    private lateinit var sizeSpinner: Spinner
    private lateinit var genderSpinner: Spinner

    private lateinit var heightData: EditText
    private lateinit var weightData: EditText
    private lateinit var chestData: EditText
    private lateinit var waistData: EditText
    private lateinit var hipsData: EditText
    private lateinit var sleevesData: EditText

    private lateinit var measurementFields: List<EditText>

    private val measurementSuffix = " cm"


    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as Application).appComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_initial_data_entry)

        setTitle(R.string.title_measurements)

        sizeSpinner = findViewById(R.id.size_spinner)
        genderSpinner = findViewById(R.id.gender_spinner)

        heightData = findViewById(R.id.heightData)
        weightData = findViewById(R.id.weightData)
        chestData = findViewById(R.id.chestData)
        waistData = findViewById(R.id.waistData)
        hipsData = findViewById(R.id.hipsData)
        sleevesData = findViewById(R.id.sleevesData)

        measurementFields = listOf(
            heightData, weightData, chestData, waistData, hipsData, sleevesData
        )

        saveDataButton = findViewById(R.id.saveInitialDetails)
        saveDataButton.setOnClickListener{ saveData() }

        measurementFields.forEach { field ->
            field.setText(measurementSuffix)

            field.addTextChangedListener(object : TextWatcher {
                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
                override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

                override fun afterTextChanged(s: Editable) {
                    var editedText = s.toString()

                    if (editedText.matches(Regex("^ cm.$"))) {
                        field.setSelection(0)
                        editedText = editedText.dropLast(1)
                        field.setText(editedText)
                    }

                    if (!editedText.contains(measurementSuffix)) {
                        field.setText(measurementSuffix)
                        Selection.setSelection(field.text, field.text.length)
                    }

                    if (editedText.removeSuffix(" cm").length > 3) {
                        field.setText(editedText.slice(0..2) + measurementSuffix)
                        Selection.setSelection(field.text, 3)
                    }
                }
            })

            field.setOnClickListener {
                if (field.text.length > 3) {
                    field.setSelection(field.text.length - 3)
                } else {
                    field.setSelection(0)
                }
            }

            field.setOnFocusChangeListener { view, hasFocus ->
                if (hasFocus) {
                    field.post {
                        if (field.text.length > 3) {
                            field.setSelection(field.text.length - 3)
                        } else {
                            field.setSelection(0)
                        }
                    }
                }
            }
        }
    }

    fun saveData() {
        var hasError = false

        measurementFields.forEach {
            if (it.text.length < 4 || it.text.startsWith("0") || !it.text.removeSuffix(measurementSuffix).matches(Regex("^[0-9]*$"))) {
                it.error = "Invalid data"
                hasError = true
            }
        }

        if (hasError) {
            return
        }

        val userData = gatherUserData()

        val dataSubscription = firebaseService.setUser(userData).take(1).subscribe({
            val intent = Intent(this, MainActivity::class.java)
            this.startActivity(intent)
            finish()
        }, {
            print("Error $it")
        })
    }

    private fun gatherUserData(): UserModel {
        return UserModel(
            "id",
            "name",
            genderSpinner.selectedItem.toString(),
            sizeSpinner.selectedItem.toString(),
            convertMeasurementText(heightData),
            convertMeasurementText(weightData),
            convertMeasurementText(chestData),
            convertMeasurementText(waistData),
            convertMeasurementText(hipsData),
            convertMeasurementText(sleevesData)
        )
    }

    private fun convertMeasurementText(measurement: EditText): Int {
        return Integer.parseInt(measurement.text.removeSuffix(" cm").toString())
    }
}
