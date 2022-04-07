package com.example.anmp_projectuts_160419060.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.anmp_projectuts_160419060.R
import com.example.anmp_projectuts_160419060.model.Review
import kotlinx.android.synthetic.main.review_list_item.view.*

class ReviewListAdapter (val bookList: ArrayList<Review>) : RecyclerView
.Adapter<ReviewListAdapter.ReviewViewHolder>() {
    class ReviewViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.review_list_item, parent, false)
        return ReviewViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        val book = bookList[position]
        with (holder.view) {
            txtReviewRating.text = book.rating.toString()
            txtReviewDesc.text = book.description
            txtReviewNRP.text = book.nrp.toString()
        }
    }

    override fun getItemCount() = bookList.size

    fun updateBookReviewList(newBookReviewList: ArrayList<Review>) {
        bookList.clear()
        bookList.addAll(newBookReviewList)
        notifyDataSetChanged()
    }
}