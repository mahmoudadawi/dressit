package hu.attilavegh.dressit.ui.fragments.shape

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import hu.attilavegh.dressit.R

class ShapeFragment : Fragment() {

    private lateinit var shapeViewModel: ShapeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        shapeViewModel = ViewModelProviders
            .of(this)
            .get(ShapeViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_shape, container, false)
        val textView: TextView = root.findViewById(R.id.text_shape)

        shapeViewModel.text.observe(this, Observer {
            textView.text = it
        })

        return root
    }
}