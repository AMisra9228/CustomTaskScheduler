package com.sample.todoapp.account

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.sample.todoapp.data.TaskDatabase
import com.sample.todoapp.databinding.FragmentAccountBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.apache.commons.net.ntp.NTPUDPClient
import java.io.IOException
import java.net.InetAddress
import java.util.Date


class AccountFragment : Fragment() {

    private var _binding: FragmentAccountBinding? = null
    private val binding get() = _binding!!
    private lateinit var accountViewModel: AccountViewModel

    val TIME_SERVER: String = "time-a.nist.gov"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAccountBinding.inflate(inflater, container, false)

        val accDao = TaskDatabase.getInstance(requireContext()).accountDao()
        val repository = AccountRepository(accDao)
        accountViewModel = AccountViewModelFactory(repository).create(AccountViewModel::class.java)

        binding.btnSave.setOnClickListener {
            saveInfo()
            /*CoroutineScope(Dispatchers.IO).launch {
                printTimes()
            }*/
        }

        return binding.root
    }

    private fun saveInfo() {
        val userName = binding.userName.text.toString()
        val userMail = binding.userMail.text.toString()

        if(userName.isEmpty() || userMail.isEmpty()){
            Toast.makeText(context, "Enter all details", Toast.LENGTH_SHORT).show()
            return
        } else  if (!Patterns.EMAIL_ADDRESS.matcher(userMail).matches()) {
            Toast.makeText(context, "Invalid Email", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show()
            accountViewModel.insertData(userName, userMail)
            binding.userName.text?.clear()
            binding.userMail.text?.clear()
        }
    }

    @Throws(IOException::class)
    suspend fun printTimes() {
        try {
            val timeClient = NTPUDPClient()
            val inetAddress = InetAddress.getByName(TIME_SERVER)
            val timeInfo = timeClient.getTime(inetAddress)
            val returnTime = timeInfo.message.transmitTimeStamp.time

            val time: Date = Date(returnTime)
            withContext(Dispatchers.Main) {
                Log.e("getCurrentNetworkTime", "Time from $TIME_SERVER: $time")

                Log.e("Local time", "Local time")
                Log.e("Local time", "Current time: " + Date(System.currentTimeMillis()))
                Log.e("Local time", "Time info: " + Date(timeInfo.returnTime))
                Log.e(
                    "Local time",
                    "GetOriginateTimeStamp: " + Date(timeInfo.message.originateTimeStamp.time)
                )

                Log.e("NTP time", "Time from $TIME_SERVER: $time")

                Log.e("Local time", "Time info: " + Date(timeInfo.message.receiveTimeStamp.time))
                Log.e(
                    "Local time",
                    "GetOriginateTimeStamp: " + Date(timeInfo.message.transmitTimeStamp.time)
                )
            }
        } catch (e: Exception) {
            // Handle network errors
            withContext(Dispatchers.Main) {
                Log.e("Error", "Error: ${e.message}")
            }
        }
    }
}