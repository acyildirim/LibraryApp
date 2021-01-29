package com.example.librarymanagament.Model

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build.ID
import android.util.Log
import android.widget.Toast
import java.lang.Exception


class DatabaseForLibrary(var context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {

    companion object {
        val DATABASE_NAME = "LibraryManagament.db"
        val TABLE_NAME = "LibraryTable"
        val COL_1 = "ID"
        val COL_2 = "USERNAME"
        val COL_3 = "EMAIL"
        val COL_4 = "PASSWORD"

        val TABLE_USER = "UserTable"
        val TABLE_BOOK = "Books"

        val TABLE_RESERVATION = "Reservation"
        val RESERVATION_ID = "reservationID"
        val NAME = "FIRSTNAME"
        val SURNAME = "SECONDNAME"
        val STARTTIME = "StartTime"
        val FinishTıme = "FinishTime"
        val PHONE = "PHONE"


    }
    override fun onCreate(db: SQLiteDatabase?) {


        db?.execSQL("CREATE TABLE IF NOT EXISTS $TABLE_BOOK(ID INTEGER PRIMARY KEY AUTOINCREMENT, BOOKNAME VARCHAR(30), CATEGORY VARCHAR(20),AUTHOR VARCHAR(30),LOCATION VARCHAR(30) )")

        db?.execSQL("CREATE TABLE IF NOT EXISTS $TABLE_USER(ID INTEGER PRIMARY KEY AUTOINCREMENT, USERNAME VARCHAR(30), PASSWORD VARCHAR(100),EMAIL VARCHAR(30),firstName VARCHAR(30),lastName VARCHAR(30) )")

        db?.execSQL("CREATE TABLE IF NOT EXISTS $TABLE_NAME(ID INTEGER PRIMARY KEY AUTOINCREMENT, USERNAME VARCHAR(30),EMAIL VARCHAR(100), PASSWORD VARCHAR(30) )")


        db?.execSQL("CREATE TABLE IF NOT EXISTS $TABLE_RESERVATION ($RESERVATION_ID INTEGER PRIMARY KEY AUTOINCREMENT, $NAME VARCHAR(30) NOT NULL, $SURNAME VARCHAR(30) NOT NULL,$STARTTIME LONG NOT NULL,$FinishTıme LONG NOT NULL,$PHONE VARCHAR(30) NOT NULL,$COL_1 INTEGER NOT NULL)")

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_USER")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_BOOK")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_RESERVATION")

        onCreate(db)


    }

    fun insertData(username: String, email: String, password: String): Boolean {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_2, username)
        values.put(COL_3, email)
        values.put(COL_4, password)
        val res = db.insert(TABLE_NAME, null, values)
        return !res.equals(-1)
    }


    fun insertUserData(
        username: String,
        password: String,
        email: String,
        firstName: String,
        lastName: String
    ): Boolean {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put("USERNAME", username)
        values.put("PASSWORD", password)
        values.put("EMAIL", email)
        values.put("firstName", firstName)
        values.put("lastName", lastName)
        val res = db.insert("UserTable", null, values)
        return !res.equals(-1)
    }

    fun insertBook(book: Book) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put("BOOKNAME", book.BookName)
        values.put("CATEGORY", book.Category)
        values.put("AUTHOR", book.Author)
        values.put("LOCATION", book.Location)
        val res = db.insert("Books", null, values)

        if (res == -1.toLong()) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()

        }


    }
    fun insertReservation(name : String, surname : String,startTıme : String ,finishTime : String,phone :String , bookId: String) {
        val db = this.writableDatabase
        val book : Book
        val values = ContentValues()
        values.put(NAME, name)
        values.put(SURNAME, surname)
        values.put(STARTTIME, startTıme)
        values.put(FinishTıme, finishTime)
        values.put(PHONE, phone)
        values.put(COL_1, bookId)
        val res = db.insert(TABLE_RESERVATION, null, values)

        if (res == -1.toLong()) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()

        }


    }

    fun readData(bookId: Int): MutableList<Book> {
        var list: MutableList<Book> = ArrayList()
        val db = this.readableDatabase
        val query = "Select From $TABLE_BOOK where $COL_1 = $bookId"
        val result = db.rawQuery(query, null)

        if (result.moveToFirst()) {
            do {
                var book = Book()
                book.id = result.getString(result.getColumnIndex("ID")).toInt()
                book.BookName = result.getString(result.getColumnIndex("BOOKNAME"))
                book.Category = result.getString(result.getColumnIndex("CATEGORY"))
                book.Author = result.getString(result.getColumnIndex("AUTHOR"))
                book.Location = result.getString(result.getColumnIndex("LOCATION"))
                list.add((book))

            } while (result.moveToNext())
        }
        result.close()
        db.close()
        return list

    }


    fun deleteBook(bookId :Int):Boolean {
        val query = "Delete From $TABLE_BOOK where $COL_1 = $bookId"
        val db = this.writableDatabase
        var result : Boolean = false
        try
        {
            val cursor = db.execSQL(query)
            result = true
        }
        catch (e:Exception)
        {
            Log.e(ContentValues.TAG,"Error While Deleting")
        }
        db.close()
        return result
    }

    fun updateBook(id : String,bookName : String,category: String,author : String, location : String) : Boolean
    {
        val db = this.writableDatabase
        val query = "Select * from " + TABLE_BOOK

        val contentValues = ContentValues()
        contentValues.put("BOOKNAME",bookName)
        contentValues.put("CATEGORY",category)
        contentValues.put("AUTHOR",author)
        contentValues.put("LOCATION",location)
        var result : Boolean = false

        try {
            db.update(TABLE_BOOK,contentValues,"$COL_1 =?", arrayOf(id))
            result = true

        }
        catch (e : Exception){
            Log.e(ContentValues.TAG,"Error While Updating")

            result = false

        }
        return result

    }

    fun viewAllBook(context: Context): ArrayList<Book> {
        val db = this.readableDatabase
        val query = "SELECT * FROM " + TABLE_BOOK
        val data: Cursor = db.rawQuery(query, null)
        val books = ArrayList<Book>()

        if (data.count == 0) {
            Toast.makeText(context, "No Records Found", Toast.LENGTH_SHORT).show()
        }
        else
        {
            data.moveToFirst()
            while (!data.isAfterLast())
            {
                val book = Book()
                book.id = data.getInt(data.getColumnIndex("ID"))
                book.BookName = data.getString(data.getColumnIndex("BOOKNAME"))
                book.Category = data.getString(data.getColumnIndex("CATEGORY"))
                book.Author = data.getString(data.getColumnIndex("AUTHOR"))
                book.Location = data.getString(data.getColumnIndex("LOCATION"))
                books.add(book)
                data.moveToNext()
            }
            Toast.makeText(context,"${data.count.toString()} Records Found",Toast.LENGTH_SHORT).show()

        }
        data.close()
        db.close()
        return  books

    }

    fun userPresent(username: String, password: String): Boolean {
        val db = writableDatabase
        val query = "select * from LibraryTable where USERNAME = '$username' and PASSWORD = '$password'"
        val cursor = db.rawQuery(query, null)

        if (cursor.count <= 0) {
            cursor.close()
            return false

        }
        cursor.close()
        return true

    }

    fun userLibraryPresent(username: String, password: String): Boolean {
        val db = writableDatabase
        val query = "select * from UserTable where USERNAME = '$username' and PASSWORD = '$password' "
        val cursor = db.rawQuery(query, null)
        if (cursor.count <= 0) {
            cursor.close()
            return false

        }
        cursor.close()
        return true
    }




    val allBooks: List<Book>
        get() {
            val lstBooks = ArrayList<Book>()
            val selectALLQuery = "SELECT * FROM " + TABLE_BOOK
            val db=this.writableDatabase
            val cursor  = db.rawQuery(selectALLQuery, null)
            if(cursor.moveToFirst()){
                do{
                    val book = Book()
                    book.BookName = cursor.getString(cursor.getColumnIndex("BookName"))
                    book.Category = cursor.getString(cursor.getColumnIndex("CATEGORY"))
                    book.Author = cursor.getString(cursor.getColumnIndex("Author"))
                    book.Location = cursor.getString(cursor.getColumnIndex("LOCATION"))
                    lstBooks.add(book)
                }while (cursor.moveToNext())

            }
            return lstBooks
        }

}

