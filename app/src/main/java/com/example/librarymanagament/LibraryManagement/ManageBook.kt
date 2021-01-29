package com.example.librarymanagament.LibraryManagement

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.librarymanagament.Model.Book
import com.example.librarymanagament.Model.DatabaseForLibrary
import com.example.librarymanagament.R
import kotlinx.android.synthetic.main.activity_add_book.*

class ManageBook : AppCompatActivity() {


    lateinit var handler : DatabaseForLibrary

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_book)


        handler = DatabaseForLibrary(this)


        buttonInsert.setOnClickListener{
            if (txtName.text.isEmpty() or  txtSurname.text.isEmpty() or  txtStart.text.isEmpty() or txtPhoneNo.text.isEmpty()){
                Toast.makeText(this,"Please Fill All Areas",Toast.LENGTH_SHORT).show()
            }
            else{
                var book = Book(txtName.text.toString(),txtStart.text.toString(),txtSurname.text.toString(),txtPhoneNo.text.toString())

                handler.insertBook(book)
                Toast.makeText(this,"Your Book İs Succesfully Added.",Toast.LENGTH_SHORT).show()
                txtSurname.text.clear()
                txtName.text.clear()
                txtStart.text.clear()
                txtPhoneNo.text.clear()
            }



        }
    }



}
