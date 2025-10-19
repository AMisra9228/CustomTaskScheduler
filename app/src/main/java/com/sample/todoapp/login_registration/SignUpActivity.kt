package com.sample.todoapp.login_registration

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.sample.todoapp.MainActivity
import com.sample.todoapp.MyApp
import com.sample.todoapp.databinding.ActivitySignUpBinding
import java.util.Calendar
import javax.inject.Inject

class SignUpActivity : AppCompatActivity() {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var viewModel: UserViewModel
    private var locTime: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        // Dagger injection must happen before super.onCreate()
        (application as MyApp).appComponent.inject(this)

        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        // Now you can use the injected sharedPreferences
        val savedName = sharedPreferences.getString("user_name", "")
        val savedPwd = sharedPreferences.getString("pwd", "")

        if (!savedName.equals("") && !savedPwd.equals("")) {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
            // It's good practice to finish the activity to prevent the user from navigating back
            finish()
        }

        binding.btnSignUp.setOnClickListener {
            saveUserInfo()
        }

        binding.tvSignIn.setOnClickListener {
            val i = Intent(this, SignInActivity::class.java)
            startActivity(i)
            finish()
        }
    }

    private fun saveUserInfo() {
        try {
            val userName = binding.atvUsernameReg.text.toString()
            val userEmail = binding.atvEmailReg.text.toString()
            val userPassword = binding.atvPasswordReg.text.toString()

            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH) + 1 // Calendar.MONTH is 0-based
            val day = c.get(Calendar.DAY_OF_MONTH)
            val hour = c.get(Calendar.HOUR_OF_DAY)
            val minute = c.get(Calendar.MINUTE)
            locTime = "$day-$month-$year $hour:$minute"

            if (userName.isEmpty() || userEmail.isEmpty() || userPassword.isEmpty()) {
                Toast.makeText(this, "Enter all details", Toast.LENGTH_SHORT).show()
            } else if (!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()) {
                Toast.makeText(this, "Invalid Email", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.insertUserInfo(this, userName, userEmail, userPassword, locTime)
                Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show()
                binding.atvUsernameReg.text?.clear()
                binding.atvEmailReg.text?.clear()
                binding.atvPasswordReg.text?.clear()

                val i = Intent(this, SignInActivity::class.java)
                startActivity(i)
                finish()
            }

        } catch (e: Exception) {
            Log.e("Error", "Error: ${e.message}")
        }
    }
}
