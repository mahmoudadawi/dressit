package hu.attilavegh.dressit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class InitialDataEntryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_initial_data_entry)

        setTitle(R.string.title_measurements)
    }
}
