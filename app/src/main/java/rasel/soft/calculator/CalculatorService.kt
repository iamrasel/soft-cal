package rasel.soft.calculator

import android.annotation.SuppressLint
import android.inputmethodservice.InputMethodService
import android.text.Editable
import android.view.View
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText


class CalculatorService : InputMethodService() {
    private lateinit var layout: View
    @SuppressLint("InflateParams")
    override fun onCreateInputView(): View {
        layout = layoutInflater.inflate(R.layout.input, null)

        listenKeyEvents(id = R.id.one, str = "1")
        listenKeyEvents(id = R.id.two, str = "2")
        listenKeyEvents(id = R.id.three, str = "3")
        listenKeyEvents(id = R.id.four, str = "4")
        listenKeyEvents(id = R.id.five, str = "5")
        listenKeyEvents(id = R.id.six, str = "6")
        listenKeyEvents(id = R.id.seven, str = "7")
        listenKeyEvents(id = R.id.eight, str = "8")
        listenKeyEvents(id = R.id.nine, str = "9")
        listenKeyEvents(id = R.id.zero, str = "0")
        listenKeyEvents(id = R.id.dot, str = ".")
        listenKeyEvents(id = R.id.back)
        listenKeyEvents(id = R.id.equals)
        listenKeyEvents(id = R.id.operators)
        listenKeyEvents(id = R.id.clear)

        return layout
    }

    private fun listenKeyEvents(id: Int = 0, str: String = "string") {
        layout.findViewById<MaterialButton>(id).setOnClickListener {
            layout.findViewById<TextInputEditText>(R.id.inputs).apply {
                when (id) {
                    R.id.one, R.id.two, R.id.three, R.id.four, R.id.five, R.id.six,
                    R.id.seven, R.id.eight, R.id.nine, R.id.zero, R.id.dot -> text = text?.append(str)
                    R.id.back -> text?.takeIf { it.isNotEmpty() }?.let {
                        text = Editable.Factory.getInstance().newEditable(it.dropLast(1))
                        setSelection(it.length - 1)
                    }
                    R.id.equals -> return@setOnClickListener // return the calculated result
                    R.id.operators -> return@setOnClickListener // input mathematical operators
                    R.id.clear -> text?.clear()
                }
            }
        }
    }
}