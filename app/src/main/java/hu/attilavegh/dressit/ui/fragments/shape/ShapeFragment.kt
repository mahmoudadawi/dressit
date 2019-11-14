package hu.attilavegh.dressit.ui.fragments.shape

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import hu.attilavegh.dressit.Application
import hu.attilavegh.dressit.R
import hu.attilavegh.dressit.models.UserModel
import hu.attilavegh.dressit.services.firebase.FirebaseDataService
import javax.inject.Inject

class ShapeFragment : Fragment() {

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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        (context!!.applicationContext as Application).appComponent.inject(this)

        val root = inflater.inflate(R.layout.fragment_shape, container, false)

        sizeSpinner = root.findViewById(R.id.size_spinner)
        genderSpinner = root.findViewById(R.id.gender_spinner)

        heightData = root.findViewById(R.id.heightData)
        weightData = root.findViewById(R.id.weightData)
        chestData = root.findViewById(R.id.chestData)
        waistData = root.findViewById(R.id.waistData)
        hipsData = root.findViewById(R.id.hipsData)
        sleevesData = root.findViewById(R.id.sleevesData)

        saveDataButton = root.findViewById(R.id.saveInitialDetails)
        saveDataButton.setOnClickListener{ saveData() }

        return root
    }

    fun saveData() {
        val userData = gatherUserData()

        val dataSubscription = firebaseService.setUser(userData).take(1).subscribe({
            Toast.makeText(context, "Saved", Toast.LENGTH_LONG).show()
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