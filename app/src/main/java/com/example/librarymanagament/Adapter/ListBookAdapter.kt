package com.example.librarymanagament.Adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.EditText
import com.example.librarymanagament.Model.Book
import com.example.librarymanagament.R

class ListBookAdapter(internal var activity: Activity,
                      internal var lstBook: List<Book>,
                      internal var txtBookName: EditText,
                      internal var txtCategory: EditText,
                      internal var txtAuthor: EditText,
                      internal var txtLocation: EditText): BaseAdapter() {

    internal var inflater:LayoutInflater

    init {
        inflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {


        val rowView:View
        rowView = inflater.inflate(R.layout.a,null)
        /*

        rowView.row_id.text = lstBook[position].id.toString()
        rowView.txt_BookName.text = lstBook[position].BookName

        rowView.txt_Category.text = lstBook[position].Category
        rowView.txt_Author.text = lstBook[position].Author
        rowView.txt_Location.text = lstBook[position].Location

        rowView.setOnClickListener{
            txtBookName.setText(rowView.txt_BookName.text.toString())
            txtCategory.setText(rowView.txt_Category.text.toString())
            txtAuthor.setText(rowView.txt_Author.text.toString())
            txtLocation.setText(rowView.txt_Location.text.toString())

        }
         */
        return rowView


    }

    override fun getItem(position: Int): Any {
        return lstBook[position]
    }

    override fun getItemId(position: Int): Long {
        return lstBook[position].id.toLong()
    }

    override fun getCount(): Int {
        return lstBook.size
    }



}