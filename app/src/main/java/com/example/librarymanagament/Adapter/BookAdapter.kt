package com.example.librarymanagament.Adapter

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.example.librarymanagament.LibraryManagement.BOOKS
import com.example.librarymanagament.Model.Book
import com.example.librarymanagament.R
import kotlinx.android.synthetic.main.a.view.*
import kotlinx.android.synthetic.main.book_info.view.*

class BookAdapter (context: Context, val books : ArrayList<Book>): RecyclerView.Adapter<BookAdapter.ViewHolder>(){


    val context = context

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val labelId  = itemView.txtReservationID
        val labelName = itemView.txtName
        val labelAuthor = itemView.txtSurname
        val labelCategory = itemView.txtStart
        val labelLocation = itemView.txtPhoneNo
        val btnUpdate = itemView.btnUpdate
        val btnDelete = itemView.btnDelete


    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): BookAdapter.ViewHolder {
        val v =  LayoutInflater.from(p0.context).inflate(R.layout.book_info,p0,false)

        return ViewHolder(v)



    }

    override fun getItemCount(): Int {
        return books.size
    }

    override fun onBindViewHolder(p0: BookAdapter.ViewHolder, p1: Int) {
        val book : Book = books[p1]
        p0.labelId.text = book.id.toString()
        p0.labelName.text = book.BookName
        p0.labelAuthor.text = book.Author
        p0.labelCategory.text = book.Category
        p0.labelLocation.text = book.Location

        p0.btnDelete.setOnClickListener{
            val bookName = book.BookName
            var alertDialog = AlertDialog.Builder(context)
                .setTitle("Warning")
                .setMessage("Are You Sure to Delete: $bookName")
                .setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, which ->
                    if(BOOKS.handler.deleteBook(book.id))
                    {


                        books.removeAt(p1)
                        notifyItemRemoved(p1)
                        notifyItemRangeChanged(p1,books.size)
                        Toast.makeText(context,"Book $bookName Deleted", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        Toast.makeText(context,"Error While Deleting", Toast.LENGTH_SHORT).show()
                    }
                })
                .setNegativeButton("No", DialogInterface.OnClickListener { dialog, which ->  })
                .setIcon(R.drawable.ic_warning_black_24dp).show()
        }
        p0.btnUpdate.setOnClickListener {
            val inflater = LayoutInflater.from(context)
            val view = inflater.inflate(R.layout.a,null)

            val txtEditBookName : TextView = view.findViewById(R.id.updateBookName)
            val txtEditCategory : TextView = view.findViewById(R.id.updateCategory)
            val txtEditAuthor : TextView = view.findViewById(R.id.updateAuthor)
            val txtEditLocation : TextView = view.findViewById(R.id.updateLocation)

            txtEditBookName.text = book.BookName
            txtEditCategory.text = book.Category
            txtEditAuthor.text = book.Author
            txtEditLocation.text = book.Location

            val builder = AlertDialog.Builder(context)
                .setTitle("Update Book İnfo")
                .setView(view)
                .setPositiveButton("Update", DialogInterface.OnClickListener { dialog, which ->
                    val isUpdate : Boolean = BOOKS.handler.updateBook(
                        book.id.toString(),
                        view.updateBookName.text.toString(),
                        view.updateCategory.text.toString(),
                        view.updateAuthor.text.toString(),
                        view.updateLocation.text.toString())
                    if (isUpdate == true){
                        books[p1].BookName = view.updateBookName.text.toString()
                        books[p1].Category = view.updateCategory.text.toString()
                        books[p1].Author = view.updateAuthor.text.toString()
                        books[p1].Location = view.updateLocation.text.toString()
                        notifyDataSetChanged()
                        Toast.makeText(context,"Update is succesfull",Toast.LENGTH_SHORT).show()
                    }
                    else{
                        Toast.makeText(context,"Error While Updating",Toast.LENGTH_SHORT).show()

                    }
                })
                .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which ->

                })
            val alert = builder.create()
            alert.show()
        }

    }


}