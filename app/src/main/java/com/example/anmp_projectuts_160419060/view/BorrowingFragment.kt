package com.example.anmp_projectuts_160419060.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.anmp_projectuts_160419060.R
import com.example.anmp_projectuts_160419060.viewmodel.BorrowingListViewModel
import kotlinx.android.synthetic.main.fragment_borrowing.*

class BorrowingFragment : Fragment() {
    private lateinit var  viewModel: BorrowingListViewModel
    private val borrowListAdapter = BorrowingListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_borrowing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(BorrowingListViewModel::class.java)
        viewModel.refresh()

        recViewBorrowing.layoutManager = LinearLayoutManager(context)
        recViewBorrowing.adapter = borrowListAdapter

        observeViewModel()

        refreshLayout.setOnRefreshListener {
            recViewBorrowing.visibility = View.GONE
            textErrorBorrowing.visibility = View.GONE
            progressBarImageBorrowing.visibility = View.VISIBLE
            viewModel.refresh()
            refreshLayout.isRefreshing = false
        }
    }

    private fun observeViewModel() {
        viewModel.borrowLiveData.observe(viewLifecycleOwner) {
            borrowListAdapter.updateBorrowingList(it)
        }
        viewModel.borrowLoadErrorLiveData.observe(viewLifecycleOwner) {
            textErrorBorrowing.visibility = if (it) View.VISIBLE else View.GONE
        }
        viewModel.loadingLiveData.observe(viewLifecycleOwner) {
            if (it) { //sedang loading
                recViewBorrowing.visibility = View.GONE
                progressBarImageBorrowing.visibility = View.VISIBLE
            } else {
                recViewBorrowing.visibility = View.VISIBLE
                progressBarImageBorrowing.visibility = View.GONE
            }
        }
    }
}