package com.example.anmp_projectuts_160419060.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.anmp_projectuts_160419060.R
import com.example.anmp_projectuts_160419060.util.loadImage
import com.example.anmp_projectuts_160419060.viewmodel.StudentViewModel
import kotlinx.android.synthetic.main.fragment_student.*

class StudentFragment : Fragment() {
    private lateinit var viewModel: StudentViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(StudentViewModel::class.java)
        viewModel.fetch()
        observeViewModel()
        buttonBookmark.setOnClickListener {
            val action = StudentFragmentDirections.actionBookmarkFragment()
            Navigation.findNavController(it).navigate(action)
        }
        buttonUpdateStudentData.setOnClickListener {
            val action = StudentFragmentDirections.actionUpdateStudent()
            Navigation.findNavController(it).navigate(action)
        }
    }

    private fun observeViewModel() {
        viewModel.studentsLiveData.observe(viewLifecycleOwner) {
            val student = viewModel.studentsLiveData.value
            student?.let{
                txtPhoneProfile.setText(it.phone.toString())
                txtStudentName.setText(it.name.toString())
                imgStudentProfile.loadImage(it.photoURL, progressStudentProfile)
            }
        }
    }
}