package com.example.librarymanagament.UserManagement

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.widget.LinearLayout
import com.example.librarymanagament.Adapter.BookAdapterUser
import com.example.librarymanagament.Model.Book
import com.example.librarymanagament.Model.DatabaseForLibrary
import com.example.librarymanagament.R
import kotlinx.android.synthetic.main.activity_books.*

class Books_for_user : AppCompatActivity() {

    companion object {
        lateinit var handler: DatabaseForLibrary


    }
    var bookList = ArrayList<Book>()
    lateinit var adapter : RecyclerView.Adapter<*>
    lateinit var rv : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books_for_user)

        handler = DatabaseForLibrary(this)
        viewBooks()

        txtSearch.addTextChangedListener(object : TextWatcher {
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
                    adapter = BookAdapterUser(this@Books_for_user,filteredBook)
                    rv.adapter = adapter
                }else{
                    adapter = BookAdapterUser(this@Books_for_user,bookList)
                    rv.adapter = adapter
                }

            }


        })
    }
    private fun viewBooks(){
        bookList = Books_for_user.handler.viewAllBook(this)
        adapter = BookAdapterUser(this,bookList)
        rv = findViewById(R.id.view)
        rv.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL,false) as RecyclerView.LayoutManager
        rv.adapter = adapter
    }

    override fun onResume() {
        viewBooks()
        super.onResume()
    }
}
