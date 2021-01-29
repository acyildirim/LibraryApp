package com.example.librarymanagament

import android.content.AsyncQueryHandler
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.librarymanagament.Model.DatabaseForLibrary
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    lateinit var handler: DatabaseForLibrary

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        handler = DatabaseForLibrary(this)
        val registerButton:Button = findViewById(R.id.buttonRegister)
        registerButton.setOnClickListener{
            val registerIntent = Intent(this,Register::class.java)
            startActivity(registerIntent)
        }
        val mTextUsername : EditText = findViewById(R.id.txtUsername)
        val mTextPassword : EditText = findViewById(R.id.txtPassword)

        buttonLogin.setOnClickListener{
            if (handler.userPresent(mTextUsername.text.toString(),mTextPassword.text.toString())){
                Toast.makeText(this,"Login Successfully",Toast.LENGTH_SHORT).show()
                val registerIntent = Intent(this,LibraryMenu::class.java)
                startActivity(registerIntent)
            }
            else{
                Toast.makeText(this,"Username Or Password is incorrect",Toast.LENGTH_SHORT).show()

            }
        }
     }

}
