package com.sample.todoapp.category

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import com.sample.todoapp.R
import com.sample.todoapp.account.AccountRepository
import com.sample.todoapp.account.AccountViewModel
import com.sample.todoapp.account.AccountViewModelFactory
import com.sample.todoapp.data.TaskDatabase
import com.sample.todoapp.databinding.FragmentAccountBinding
import com.sample.todoapp.databinding.FragmentCategoryBinding
import org.xml.sax.Parser
import java.util.Calendar

class CategoryFragment : Fragment() {

    private var _binding: FragmentCategoryBinding? = null
    private val binding get() = _binding!!
    private lateinit var categoryViewModel: CategoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        val itemDao = TaskDatabase.getInstance(requireContext()).itemDao()
        val repository = CategoryRepo(itemDao)
        categoryViewModel = CategoryViewModelFact(repository).create(CategoryViewModel::class.java)

        binding.radioGroup1.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener { group, checkedId ->
                if(checkedId == R.id.radioButton1) {
                    Toast.makeText(context, "Low", Toast.LENGTH_SHORT).show()
                } else if(checkedId == R.id.radioButton2) {
                    Toast.makeText(context, "Medium", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "High", Toast.LENGTH_SHORT).show()
                }
            })

        binding.dtSelect.setOnClickListener{
            var c = Calendar.getInstance()
            var year : Int = c.get(Calendar.YEAR)
            var month : Int = c.get(Calendar.MONTH)
            var day : Int = c.get(Calendar.DAY_OF_MONTH)

                context?.let { it1 ->
                    DatePickerDialog(it1, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                        // Display Selected date in textbox
                        binding.taskDt.setText("" + dayOfMonth + " - " + (Integer.valueOf(monthOfYear) + 1) + " - " + year)
                    }, year, month, day).show()
                }

        }

        binding.btnSave.setOnClickListener {

        }
        return binding.root
    }

    private fun saveItemInfo() {
        val taskName = binding.taskName.getEditText().toString()
        val taskDescription = binding.taskDesp.getEditText().toString()


        /*if(userName.isEmpty() || userMail.isEmpty()){
            Toast.makeText(context, "Enter all details", Toast.LENGTH_SHORT).show()
            return
        } else  if (!Patterns.EMAIL_ADDRESS.matcher(userMail).matches()) {
            Toast.makeText(context, "Invalid Email", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show()
            accountViewModel.insertData(userName, userMail)
            binding.userName.text?.clear()
            binding.userMail.text?.clear()
        }*/
    }
}