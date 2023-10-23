package rasel.soft.calculator

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import rasel.soft.calculator.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @SuppressLint("QueryPermissionsNeeded")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.enable.setOnClickListener {
            if (isKeyboardEnabled) {
                Toast.makeText(this, R.string.keyboard_enabled, Toast.LENGTH_SHORT).show()
            } else {
                try {
                    startActivity(Intent(Settings.ACTION_INPUT_METHOD_SETTINGS))
                } catch (e: ActivityNotFoundException) {
                    Toast.makeText(this, R.string.manage_keyboard_intent_failed, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private val isKeyboardEnabled: Boolean get() {
        val inputMethodManager =
            this.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        for (inputMethodInfo in inputMethodManager.enabledInputMethodList) {
            if (inputMethodInfo.packageName == this.packageName) return true
        }
        return false
    }
}