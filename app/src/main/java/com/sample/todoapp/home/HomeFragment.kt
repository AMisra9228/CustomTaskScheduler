package com.sample.todoapp.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.sample.todoapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ProductViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : ProductViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.productsRv.layoutManager = LinearLayoutManager(requireContext())

        viewModel.productList.observe(viewLifecycleOwner){
            val productAdapter = ProductAdapter(requireContext(),it ,viewModel)
            binding.productsRv.adapter = productAdapter
            var productNumber = it.size.toString()
            binding.textViewResultNumber.text = "$productNumber sonuç bulundu"
        }

        return binding.root
    }

}