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
import com.example.anmp_projectuts_160419060.viewmodel.BorrowingDetailViewModel
import kotlinx.android.synthetic.main.book_borrowed_item.*
import kotlinx.android.synthetic.main.fragment_borrowing_detail.*

class BorrowingDetailFragment : Fragment() {
    private lateinit var viewModel: BorrowingDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_borrowing_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var bookId : String? = "test"
        arguments?.let {
            bookId = BorrowingDetailFragmentArgs.fromBundle(requireArguments()).idbook
        }
        viewModel = ViewModelProvider(this).get(BorrowingDetailViewModel::class.java)
        viewModel.fetch(bookId)

        observeViewModel()
        buttonDetailBookBorrowing.setOnClickListener {
            val action = BorrowingDetailFragmentDirections.actionFromBorrowingToBookDetail(
                viewModel.borrowLiveData.value?.idbook
            )
            Navigation.findNavController(it).navigate(action)
        }
        btnFeedback.setOnClickListener {
            val action = BorrowingDetailFragmentDirections.actionFeedbackFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }

    private fun observeViewModel() {
        viewModel.borrowLiveData.observe(viewLifecycleOwner) {
            val borrow = viewModel.borrowLiveData.value
            borrow?.let{
                txtTitleDetailBorrowing.setText(it.title)
                txtAuthorDetailBorrowing.setText(it.author)
                txtMonth.setText(it.borrowing_month.toString())
                txtYear.setText(it.borrowing_year.toString())
                txtPageDetailBorrowing.text = it.page.toString()
                txtStatusDetailBorrowing.setText(it.status)
                imgDetailBorrowing.loadImage(it.photoURL, progressLoadImageDetailBorrowing)
            }
        }
    }
}