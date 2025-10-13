package com.sample.todoapp.login_registration

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.sample.todoapp.MainActivity
import com.sample.todoapp.MyApp
import com.sample.todoapp.R
import com.sample.todoapp.data.TaskDatabase
import com.sample.todoapp.databinding.ActivitySignUpBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.apache.commons.net.ntp.NTPUDPClient
import java.net.InetAddress
import java.time.LocalDateTime
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

class SignUpActivity : AppCompatActivity() {


    @Inject
    lateinit var sharedPreferences: SharedPreferences

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var viewModel: UserViewModel
    var locTime: String = ""
    lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        //DI implementation
        (application as MyApp).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        //val sharedPreferences = getSharedPreferences("UserLog", MODE_PRIVATE)
        val savedName = sharedPreferences.getString("user_name", "")
        val savedPwd = sharedPreferences.getString("pwd", "")

        if (!savedName.equals("") && !savedPwd.equals("")) {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
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

            context = this@SignUpActivity
            val userName = binding.atvUsernameReg.text.toString()
            val userEmail = binding.atvEmailReg.text.toString()
            val userPassword = binding.atvPasswordReg.text.toString()

            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)
            val hour = c.get(Calendar.HOUR_OF_DAY)
            val minute = c.get(Calendar.MINUTE)
            locTime = "$day-$month-$year $hour:$minute"

            if (userName.isEmpty() || userEmail.isEmpty() || userPassword.isEmpty()) {
                Toast.makeText(context, "Enter all details", Toast.LENGTH_SHORT).show()
            } else  if (!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()) {
                Toast.makeText(context, "Invalid Email", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.insertUserInfo(context, userName, userEmail, userPassword, locTime)
                Toast.makeText(context, "Registration successful", Toast.LENGTH_SHORT).show()
                binding.atvUsernameReg.text?.clear()
                binding.atvEmailReg.text?.clear()
                binding.atvPasswordReg.text?.clear()

                val i = Intent(context, SignInActivity::class.java)
                startActivity(i)
                finish()
            }

        } catch (e: Exception) {
            Log.e("Error", "Error: ${e.message}")
        }
    }
}