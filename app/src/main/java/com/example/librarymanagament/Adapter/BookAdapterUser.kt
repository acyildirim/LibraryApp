package com.example.librarymanagament.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.librarymanagament.Model.Book
import com.example.librarymanagament.R
import kotlinx.android.synthetic.main.book_info.view.*

class BookAdapterUser (context: Context, val books : ArrayList<Book>): RecyclerView.Adapter<BookAdapterUser.ViewHolder>(){


    val context = context

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val labelId  = itemView.txtReservationID
        val labelName = itemView.txtName
        val labelAuthor = itemView.txtSurname
        val labelCategory = itemView.txtStart
        val labelLocation = itemView.txtPhoneNo


    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): BookAdapterUser.ViewHolder {
        val v =  LayoutInflater.from(p0.context).inflate(R.layout.book_info_user,p0,false)

        return ViewHolder(v)



    }

    override fun getItemCount(): Int {
        return books.size
    }

    override fun onBindViewHolder(p0: BookAdapterUser.ViewHolder, p1: Int) {
        val book : Book = books[p1]
        p0.labelId.text = book.id.toString()
        p0.labelName.text = book.BookName
        p0.labelAuthor.text = book.Author
        p0.labelCategory.text = book.Category
        p0.labelLocation.text = book.Location


    }




}