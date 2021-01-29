package com.example.librarymanagament

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.EditText
import android.widget.Toast
import com.example.librarymanagament.Model.DatabaseForLibrary
import kotlinx.android.synthetic.main.activity_register.*

class Register : AppCompatActivity() {

    lateinit var handler: DatabaseForLibrary

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        handler = DatabaseForLibrary(this)



        buttonRegister.setOnClickListener{


            if(txtUsername.text.isEmpty() or txtEmail.text.isEmpty() or txtPassword.text.isEmpty() ){

                Toast.makeText(this,"Please Fill All Areas",Toast.LENGTH_SHORT).show()

            }
            else{
                handler.insertData(txtUsername.text.toString(),txtEmail.text.toString(),txtPassword.text.toString())
                Toast.makeText(this,"You Are Successfully Registered",Toast.LENGTH_SHORT).show()

                val loginIntent = Intent(this,LoginActivity::class.java)
                startActivity(loginIntent)
                txtEmail.text.clear()
                txtPassword.text.clear()
                txtUsername.text.clear()
            }

        }
    }



}
