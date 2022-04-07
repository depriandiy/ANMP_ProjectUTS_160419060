package com.example.anmp_projectuts_160419060.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.anmp_projectuts_160419060.R
import com.example.anmp_projectuts_160419060.viewmodel.ReviewListViewModel
import kotlinx.android.synthetic.main.fragment_review.*

class ReviewFragment : Fragment() {
    private lateinit var  viewModel: ReviewListViewModel
    private val reviewListAdapter = ReviewListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_review, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var bookId : String? = "test"
        arguments?.let {
//            bookId = ReviewFragmentArgs.fromBundle(requireArguments()).idbook
        }
        viewModel = ViewModelProvider(this).get(ReviewListViewModel::class.java)
//        viewModel.refresh(bookId)
        viewModel.refresh()

        recViewReview.layoutManager = LinearLayoutManager(context)
        recViewReview.adapter = reviewListAdapter

        observeViewModel()

        refreshLayout.setOnRefreshListener {
            recViewReview.visibility = View.GONE
            textErrorReview.visibility = View.GONE
            progressLoadReview.visibility = View.VISIBLE
            viewModel.refresh()
            refreshLayout.isRefreshing = false
        }
    }
    private fun observeViewModel() {
        viewModel.reviewLiveData.observe(viewLifecycleOwner) {
            reviewListAdapter.updateBookReviewList(it)
        }
        viewModel.reviewLoadErrorLiveData.observe(viewLifecycleOwner) {
            textErrorReview.visibility = if (it) View.VISIBLE else View.GONE
        }
        viewModel.loadingLiveData.observe(viewLifecycleOwner) {
            if (it) { //sedang loading
                recViewReview.visibility = View.GONE
                progressLoadReview.visibility = View.VISIBLE
            } else {
                recViewReview.visibility = View.VISIBLE
                progressLoadReview.visibility = View.GONE
            }
        }
    }
}