package hu.attilavegh.dressit.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
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

        saveDataButton = findViewById(R.id.saveInitialDetails)
        saveDataButton.setOnClickListener{ saveData() }
    }

    fun saveData() {
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
            Integer.parseInt(heightData.text.toString()),
            Integer.parseInt(weightData.text.toString()),
            Integer.parseInt(chestData.text.toString()),
            Integer.parseInt(waistData.text.toString()),
            Integer.parseInt(hipsData.text.toString()),
            Integer.parseInt(sleevesData.text.toString())
        )
    }
}
