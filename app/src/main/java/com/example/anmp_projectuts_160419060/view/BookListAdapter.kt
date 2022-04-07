package com.example.anmp_projectuts_160419060.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.anmp_projectuts_160419060.R
import com.example.anmp_projectuts_160419060.model.Book
import com.example.anmp_projectuts_160419060.util.loadImage
import kotlinx.android.synthetic.main.book_list_item.view.*

class BookListAdapter (val bookList: ArrayList<Book>) :
    RecyclerView.Adapter<BookListAdapter.BookViewHolder>() {
    class BookViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.book_list_item, parent, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = bookList[position]
        val bookId = book.idbook
        with (holder.view) {
            txtTitle.text = book.title
            txtPage.text = book.page.toString()
            txtAuthor.text = book.author
            buttonDetail.setOnClickListener {
                val action = BookListFragmentDirections.actionBookDetail(bookId)
                Navigation.findNavController(it).navigate(action)
            }
            imageListBook.loadImage(book.photoURL, progressLoadingListBookPhoto)
        }
    }

    override fun getItemCount() = bookList.size

    fun updateBookList(newBookList: ArrayList<Book>) {
        bookList.clear()
        bookList.addAll(newBookList)
        notifyDataSetChanged()
    }

}