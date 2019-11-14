package hu.attilavegh.dressit.ui.fragments.shape

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShapeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is shape Fragment"
    }
    val text: LiveData<String> = _text
}