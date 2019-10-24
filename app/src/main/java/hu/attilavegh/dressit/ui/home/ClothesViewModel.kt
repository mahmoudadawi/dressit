package hu.attilavegh.dressit.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ClothesViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is my clothes Fragment"
    }
    val text: LiveData<String> = _text
}