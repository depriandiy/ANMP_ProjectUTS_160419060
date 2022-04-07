package com.example.anmp_projectuts_160419060.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.anmp_projectuts_160419060.viewmodel.BookmarkListViewModel
import com.example.anmp_projectuts_160419060.R
import kotlinx.android.synthetic.main.fragment_bookmark.*

class BookmarkFragment : Fragment() {
    private val bookListAdapter = BookmarkListAdapter(arrayListOf())
    private lateinit var viewModel: BookmarkListViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bookmark, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var nrp : Int? = 160419060
        arguments?.let {
//            nrp = BookmarkFragmentArgs.fromBundle(requireArguments()).nrp
        }
        viewModel = ViewModelProvider(this).get(BookmarkListViewModel::class.java)
        viewModel.refresh()

        recViewBookmark.layoutManager = LinearLayoutManager(context)
        recViewBookmark.adapter = bookListAdapter

        observeViewModel()

        refreshLayout.setOnRefreshListener {
            recViewBookmark.visibility = View.GONE
            txtErrorBookmark.visibility = View.GONE
            progressLoadBookmarkList.visibility = View.VISIBLE
            viewModel.refresh()
            refreshLayout.isRefreshing = false
        }
    }
    private fun observeViewModel() {
        viewModel.bookLiveData.observe(viewLifecycleOwner) {
            bookListAdapter.updateBookmarkList(it)
        }
        viewModel.bookLoadErrorLiveData.observe(viewLifecycleOwner) {
            txtErrorBookmark.visibility = if (it) View.VISIBLE else View.GONE
        }
        viewModel.loadingLiveData.observe(viewLifecycleOwner) {
            if (it) { //sedang loading
                recViewBookmark.visibility = View.GONE
                progressLoadBookmarkList.visibility = View.VISIBLE
            } else {
                recViewBookmark.visibility = View.VISIBLE
                progressLoadBookmarkList.visibility = View.GONE
            }
        }
    }
}