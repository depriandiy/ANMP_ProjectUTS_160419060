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
import com.example.anmp_projectuts_160419060.viewmodel.BookDetailViewModel
import kotlinx.android.synthetic.main.fragment_book_detail.*

class BookDetailFragment : Fragment() {
    private lateinit var viewModel: BookDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var bookId : String? = "test"
        arguments?.let {
            bookId = BookDetailFragmentArgs.fromBundle(requireArguments()).idbook
        }
        viewModel = ViewModelProvider(this).get(BookDetailViewModel::class.java)
        viewModel.fetch(bookId)

        observeViewModel()
        btnReview.setOnClickListener {
            val action = BookDetailFragmentDirections.actionReviewFragment() // idbook manual
            Navigation.findNavController(it).navigate(action)
        }
    }

    private fun observeViewModel() {
        viewModel.bookLiveData.observe(viewLifecycleOwner) {
            val student = viewModel.bookLiveData.value
            student?.let{
                imgBookDetail.loadImage(it.photoURL, progressBarKostDetail)
                txtTitleDetail.setText(it.title)
                txtAuthorDetail.setText(it.author)
                txtReleaseDetail.setText(it.release_year.toString())
                textDescriptionDetail.setText(it.description)
                txtPageDetail.setText(it.page.toString())
                txtStockDetail.setText(it.available_stock.toString())
            }
        }
    }
}