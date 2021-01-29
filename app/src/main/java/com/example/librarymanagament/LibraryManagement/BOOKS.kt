package com.example.librarymanagament.LibraryManagement

import android.database.Cursor
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.SpannableString
import android.text.TextWatcher
import android.text.style.ClickableSpan
import android.widget.*
import com.example.librarymanagament.Adapter.BookAdapter
import com.example.librarymanagament.Model.Book
import com.example.librarymanagament.Model.DatabaseForLibrary
import com.example.librarymanagament.R
import kotlinx.android.synthetic.main.activity_add_book.*
import kotlinx.android.synthetic.main.activity_books.*

class BOOKS : AppCompatActivity() {

    companion object {
        lateinit var handler: DatabaseForLibrary


    }
    var bookList = ArrayList<Book>()
    lateinit var adapter : RecyclerView.Adapter<*>
    lateinit var rv : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books)

        handler = DatabaseForLibrary(this)
        viewBooks()

        txtSearch.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                var filteredBook = ArrayList<Book>()
                if (!txtSearch.text.isEmpty()){
                    for ( i in 0..bookList.size-1){
                        if (bookList.get(i).BookName!!.toLowerCase().contains(s.toString().toLowerCase())){
                            filteredBook.add(bookList[i])
                        }
                    }
                    adapter = BookAdapter(this@BOOKS,filteredBook)
                    rv.adapter = adapter
                }else{
                    adapter = BookAdapter(this@BOOKS,bookList)
                    rv.adapter = adapter
                }

            }


        })

    }

    private fun viewBooks(){
        bookList = handler.viewAllBook(this)
        adapter = BookAdapter(this,bookList)
        rv = findViewById(R.id.view)
        rv.layoutManager = LinearLayoutManager(this,LinearLayout.VERTICAL,false) as RecyclerView.LayoutManager
        rv.adapter = adapter
    }

    override fun onResume() {
        viewBooks()
        super.onResume()
    }

}





/*
    private fun populateListView() {
        val data   = handler.readData()
        val txtResult : TextView = findViewById(R.id.txtResult)
        txtResult.text = ""
        val text : String = "--------------------------------------------------------------------------------------------"

        for (i in 0..(data.size-1)){


            txtResult.append("ID :" + " " + data.get(i).id + "\n " + "BOOKNAME : " + " " + data.get(i).BookName +
                    "\n " + "CATEGORY : " + " " + data.get(i).Category + "\n " +
                   "AUTHOR NAME : " + " " + data.get(i).Author +"\n " + "LOCATİON : " + " " + data.get(i).Location + " \n"  + text + "\n")


         }
    }

*/


