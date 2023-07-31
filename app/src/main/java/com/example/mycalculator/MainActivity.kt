package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.ViewTreeObserver
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.example.mycalculator.databinding.ActivityMainBinding
import net.objecthunter.exp4j.Expression
import net.objecthunter.exp4j.ExpressionBuilder
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onNumberClicked()
        onOperatorClicked()
    }


    private fun appendText(text: String) {

        if (text == "+" && binding.textViewJavab.text.isNotEmpty()) {
            binding.textViewExpression.text = ""
            binding.textViewExpression.text = binding.textViewJavab.text.substring(2)
            binding.textViewJavab.text = ""
            binding.textViewExpression.append("+")
        } else if (text == "-" && binding.textViewJavab.text.isNotEmpty()) {
            binding.textViewExpression.text = ""
            binding.textViewExpression.text = binding.textViewJavab.text.substring(2)
            binding.textViewJavab.text = ""
            binding.textViewExpression.append("-")
        } else if (text == "*" && binding.textViewJavab.text.isNotEmpty()) {
            binding.textViewExpression.text = ""
            binding.textViewExpression.text = binding.textViewJavab.text.substring(2)
            binding.textViewJavab.text = ""
            binding.textViewExpression.append("*")
        } else if (text == "/" && binding.textViewJavab.text.isNotEmpty()) {
            binding.textViewExpression.text = ""
            binding.textViewExpression.text = binding.textViewJavab.text.substring(2)
            binding.textViewJavab.text = ""
            binding.textViewExpression.append("/")
        } else {
            binding.textViewExpression.append(text)
        }
        scrollToEndForTextViewjavab()
        scrollToEndForTextViewExpression()
    }

    private fun onOperatorClicked() {

        binding.buttonJam.setOnClickListener {
            if (binding.textViewExpression.text.isNotEmpty() && binding.textViewExpression.text.last() != '+' && binding.textViewExpression.text.last() != '-' && binding.textViewExpression.text.last() != '*' && binding.textViewExpression.text.last() != '/') {
                appendText("+")
            }
        }

        binding.buttonMenha.setOnClickListener {
            if (binding.textViewExpression.text.isNotEmpty() && binding.textViewExpression.text.last() != '+' && binding.textViewExpression.text.last() != '-' && binding.textViewExpression.text.last() != '*' && binding.textViewExpression.text.last() != '/') {
                appendText("-")
            }
        }

        binding.buttonZarb.setOnClickListener {
            if (binding.textViewExpression.text.isNotEmpty() && binding.textViewExpression.text.last() != '+' && binding.textViewExpression.text.last() != '-' && binding.textViewExpression.text.last() != '*' && binding.textViewExpression.text.last() != '/') {
                appendText("*")
            }
        }

        binding.buttonTaghsim.setOnClickListener {
            if (binding.textViewExpression.text.isNotEmpty() && binding.textViewExpression.text.last() != '+' && binding.textViewExpression.text.last() != '-' && binding.textViewExpression.text.last() != '*' && binding.textViewExpression.text.last() != '/') {
                appendText("/")
            }
        }

        binding.buttonMosavi.setOnClickListener {
            try {
                val expression =
                    ExpressionBuilder(binding.textViewExpression.text.toString()).build()

                val doubleResault = expression.evaluate()
                val longResault = doubleResault.toLong()


                val showDoubleResault = "= $doubleResault"
                val showLongResault = "= $longResault"


                if (doubleResault == longResault.toDouble()) {
                    binding.textViewJavab.text = showLongResault
                } else {
                    binding.textViewJavab.text = showDoubleResault
                }
            } catch (e: Exception) {
                Toast.makeText(this, "مقادیر وارد شده نامعتبر است!", Toast.LENGTH_LONG).show()
            }

        }

        binding.buttonParantezBaz.setOnClickListener {
            appendText("(")
        }

        binding.buttonParantezBaste.setOnClickListener {
            appendText(")")
        }

        binding.buttonC.setOnClickListener {
            binding.textViewExpression.text = ""
            binding.textViewJavab.text = ""
        }

        binding.buttonDelete.setOnClickListener {
            binding.textViewJavab.text = ""
            val oldText = binding.textViewExpression.text.toString()
            if (binding.textViewExpression.text.isNotEmpty()) {
                binding.textViewExpression.text = oldText.substring(0, oldText.length - 1)
            }

        }
    }

    private fun onNumberClicked() {
        binding.button0.setOnClickListener {
            if (binding.textViewExpression.text.isNotEmpty()) {
                appendText("0")
            }
        }

        binding.button1.setOnClickListener {
            appendText("1")
        }

        binding.button2.setOnClickListener {
            appendText("2")
        }

        binding.button3.setOnClickListener {
            appendText("3")
        }

        binding.button4.setOnClickListener {
            appendText("4")
        }

        binding.button5.setOnClickListener {
            appendText("5")
        }

        binding.button6.setOnClickListener {
            appendText("6")
        }

        binding.button7.setOnClickListener {
            appendText("7")
        }

        binding.button8.setOnClickListener {
            appendText("8")
        }

        binding.button9.setOnClickListener {
            appendText("9")
        }

        binding.buttonDot.setOnClickListener {
            if (binding.textViewExpression.text.isEmpty()) {

                appendText("0.")
            } else if (binding.textViewExpression.text.last() == '+' || binding.textViewExpression.text.last() == '-'
                || binding.textViewExpression.text.last() == '*' || binding.textViewExpression.text.last() == '÷'
            ) {
                appendText("0.")
            } else if (!binding.textViewExpression.text.contains(".")) {
                appendText(".")
            }

        }
    }

    private fun scrollToEndForTextViewjavab() {
        val viewTree: ViewTreeObserver = binding.horizontalScrollViewJavab.viewTreeObserver
        viewTree.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                binding.horizontalScrollViewJavab.viewTreeObserver.removeOnGlobalLayoutListener(
                    this
                )
                binding.horizontalScrollViewJavab.scrollTo(binding.textViewJavab.width, 0)
            }
        })
    }

    private fun scrollToEndForTextViewExpression() {
        val viewTree: ViewTreeObserver = binding.horizontalScrollViewExpression.viewTreeObserver
        viewTree.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                binding.horizontalScrollViewExpression.viewTreeObserver.removeOnGlobalLayoutListener(
                    this
                )
                binding.horizontalScrollViewExpression.scrollTo(binding.textViewExpression.width, 0)
            }
        })
    }


}