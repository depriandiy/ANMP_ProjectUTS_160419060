package com.example.anmp_projectuts_160419060.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.anmp_projectuts_160419060.R
import com.example.anmp_projectuts_160419060.model.Borrowing
import com.example.anmp_projectuts_160419060.util.loadImage
import kotlinx.android.synthetic.main.book_borrowed_item.view.*

class BorrowingListAdapter(val borrowingList: ArrayList<Borrowing>) :
    RecyclerView.Adapter<BorrowingListAdapter.BorrowingViewHolder>() {
    class BorrowingViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BorrowingViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.book_borrowed_item, parent, false)
        return BorrowingViewHolder(view)
    }

    override fun onBindViewHolder(holder:BorrowingViewHolder, position: Int) {
        val book = borrowingList[position]
        val bookId = book.idbook
        with (holder.view) {
            txtStatusBorrowed.text = book.status
            txtTitleBorrowed.text = book.title
            txtAuthorBorrowed.text = book.author

            buttonDetailBorrowing.setOnClickListener {
                val action = BorrowingFragmentDirections.actionBorrowingDetailFragment(bookId)
                Navigation.findNavController(it).navigate(action)
            }
            imageBookBorrowed.loadImage(book.photoURL, progressImageBorrowedList)
        }
    }

    override fun getItemCount() = borrowingList.size

    fun updateBorrowingList(newBorrowingList: ArrayList<Borrowing>) {
        borrowingList.clear()
        borrowingList.addAll(newBorrowingList)
        notifyDataSetChanged()
    }
}