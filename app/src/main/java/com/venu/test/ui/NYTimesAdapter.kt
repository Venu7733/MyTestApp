package com.venu.test.ui

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.venu.test.R
import com.venu.test.data.Book
import com.venu.test.utils.Constants
import java.util.*

var nyTimesBookList = ArrayList<Book>()

class NYTimesAdapter(
        books: List<Book>?) : RecyclerView.Adapter<NYTimesAdapter.NYITimesBookViewHolder>() {

  init {
    nyTimesBookList = books as ArrayList<Book>
  }

  override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): NYITimesBookViewHolder {
    val itemView = LayoutInflater.from(parent?.context).inflate(R.layout.nytimes_list_item,
            parent, false)
    return NYITimesBookViewHolder(itemView)
  }

  override fun getItemCount(): Int {
    return nyTimesBookList.size
  }

  override fun onBindViewHolder(holder: NYITimesBookViewHolder?, position: Int) {
    val nyTimesBookItem = nyTimesBookList[position]
    holder?.NYTimesListItem(nyTimesBookItem)
  }

  fun addNYTimesBookData(books: List<Book>){
    val initPosition = nyTimesBookList.size
    nyTimesBookList.addAll(books)
    notifyItemRangeInserted(initPosition, nyTimesBookList.size)
  }

  class NYITimesBookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
    var bookTitleTV = itemView.findViewById<TextView>(R.id.bookTitleTV)!!
    var authorTV = itemView.findViewById<TextView>(R.id.authorTV)!!
    var summaryTV = itemView.findViewById<TextView>(R.id.summaryTV)!!
    init {
      itemView.setOnClickListener(this)
    }

    fun NYTimesListItem(nyTimesBookItem: Book) {
      bookTitleTV.text = nyTimesBookItem.name
      authorTV.text = nyTimesBookItem.author
      summaryTV.text = nyTimesBookItem.byline
    }

    override fun onClick(view: View?) {
      val intent = Intent(view!!.context, DisplayNYTimesDetailsActivity::class.java)
      intent.putExtra(Constants.INTENT_NAME, nyTimesBookList[adapterPosition].name)
      intent.putExtra(Constants.INTENT_AUTHOR, nyTimesBookList[adapterPosition].author)
      intent.putExtra(Constants.INTENT_SUMMARY, nyTimesBookList[adapterPosition].byline)
      view.context.startActivity(intent)
    }
  }
}
