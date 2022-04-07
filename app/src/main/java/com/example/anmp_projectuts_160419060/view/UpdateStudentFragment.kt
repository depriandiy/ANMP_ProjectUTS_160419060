package com.example.anmp_projectuts_160419060.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.anmp_projectuts_160419060.R
import com.example.anmp_projectuts_160419060.util.loadImage
import com.example.anmp_projectuts_160419060.viewmodel.StudentViewModel
import kotlinx.android.synthetic.main.fragment_update_student.*

class UpdateStudentFragment : Fragment() {
    private lateinit var  viewModel: StudentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_update_student, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        var nrp : Int? = 0
//        var password : String? = "test"
        arguments?.let {
//            nrp = UpdateStudentFragmentArgs.fromBundle(requireArguments()).nrp
//            password = UpdateStudentFragmentArgs.fromBundle(requireArguments()).password
        }
        viewModel = ViewModelProvider(this).get(StudentViewModel::class.java)
        viewModel.fetch()

        observeViewModel()
    }
    private fun observeViewModel() {
        viewModel.studentsLiveData.observe(viewLifecycleOwner) {
            val student = viewModel.studentsLiveData.value
            student?.let{
                editNRP.setText(it.nrp.toString())
                editPassword.setText(it.password)
                editPhone.setText(it.phone.toString())
                imgUpdateProfile.loadImage(it.photoURL, progressLoadUpdateProfile)
            }
        }
    }
}