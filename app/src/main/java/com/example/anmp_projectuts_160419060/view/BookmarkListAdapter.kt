package com.example.anmp_projectuts_160419060.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.anmp_projectuts_160419060.R
import com.example.anmp_projectuts_160419060.model.Bookmark
import com.example.anmp_projectuts_160419060.util.loadImage
import kotlinx.android.synthetic.main.bookmark_list_item.view.*
import kotlinx.android.synthetic.main.fragment_bookmark.view.*
import kotlinx.android.synthetic.main.book_list_item.view.*

class BookmarkListAdapter (val bookList: ArrayList<Bookmark>) :
    RecyclerView.Adapter<BookmarkListAdapter.FavoriteViewHolder>() {
    class FavoriteViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.bookmark_list_item, parent, false)
        return FavoriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val book = bookList[position]
        val nrp = book.nrp
        with(holder.view) {
            txtTitleItem.setText(book.title)
            txtPageItemBookmark.setText(book.page.toString())
            txtAuthorItemBookmark.setText(book.author)
            buttonDetailBookmark.setOnClickListener {
                val action = BookmarkFragmentDirections.actionFromBookmarkToBookDetailFragment(nrp.toString())
                Navigation.findNavController(it).navigate(action)
            }
            imgBookmarkItem.loadImage(book.photoURL, progressPhotoBookmark)
        }
    }

    override fun getItemCount() = bookList.size

    fun updateBookmarkList(newBookmarkList: ArrayList<Bookmark>) {
        bookList.clear()
        bookList.addAll(newBookmarkList)
        notifyDataSetChanged()
    }
}