package rasel.soft.calculator

import android.annotation.SuppressLint
import android.inputmethodservice.InputMethodService
import android.text.Editable
import android.view.KeyEvent
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
        listenKeyEvents(id = R.id.clear)
        listenKeyEvents(id = R.id.back, keycode = KeyEvent.KEYCODE_DEL)
        listenKeyEvents(id = R.id.equals, keycode = KeyEvent.KEYCODE_NUMPAD_EQUALS)
        listenKeyEvents(id = R.id.operators, keycode = KeyEvent.KEYCODE_PLUS)

        return layout
    }

    private fun listenKeyEvents(id: Int = 0, str: String = "string", keycode: Int = 0) {
        layout.findViewById<MaterialButton>(id).setOnClickListener {
            layout.findViewById<TextInputEditText>(R.id.inputs).apply {
                if (id != R.id.clear && id != R.id.back && id != R.id.equals && id != R.id.operators) {
                    text = text?.append(str)
                } else if (id == R.id.clear) {
                    text?.clear()
                } else if (id == R.id.back) {
                    text?.takeIf { it.isNotEmpty() }?.let {
                        text = Editable.Factory.getInstance().newEditable(it.dropLast(1))
                        setSelection(it.length - 1)
                    }
                } else if (id == R.id.equals) {
                    // return the calculated result
                } else if (id == R.id.operators) {
                    // input mathematical operators
                }
            }

            //this.sendDownUpKeyEvents(keycode)
        }
    }
}