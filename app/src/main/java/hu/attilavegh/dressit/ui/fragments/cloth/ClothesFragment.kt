package hu.attilavegh.dressit.ui.fragments.cloth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import hu.attilavegh.dressit.R

class ClothesFragment : Fragment() {

    private lateinit var clothesViewModel: ClothesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        clothesViewModel = ViewModelProviders
            .of(this)
            .get(ClothesViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_clothes, container, false)
        val textView: TextView = root.findViewById(R.id.text_clothes)

        clothesViewModel.text.observe(this, Observer {
            textView.text = it
        })

        return root
    }
}