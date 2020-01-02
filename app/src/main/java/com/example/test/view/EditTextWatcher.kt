package com.example.test.view
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText

class EditTextWatcher: TextWatcher{
    lateinit var editTexts: List<EditText>
    lateinit var button: View

    constructor(edittexts: List<EditText>, button: Button){
        this.editTexts = edittexts
        this.button = button
    }
    override fun afterTextChanged(s: Editable?) {
        for (editText in editTexts){
            if (editText.text.toString().trim().length <=0){
                button.isEnabled = false
                break;
            }
            else {
                button.isEnabled = true
            }
        }
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

    }

}