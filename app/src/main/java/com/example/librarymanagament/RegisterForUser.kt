package com.example.librarymanagament

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.example.librarymanagament.Model.DatabaseForLibrary
import kotlinx.android.synthetic.main.activity_register_for_user.*
import kotlin.math.log

class RegisterForUser : AppCompatActivity() {

    lateinit var handler : DatabaseForLibrary
    lateinit var TextUsername:EditText
    lateinit var TextPassword :EditText
    lateinit var TextEmail :EditText
    lateinit var TextFirstName:EditText
    lateinit var TextLastname :EditText




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_for_user)

        handler = DatabaseForLibrary(this)

        TextUsername = findViewById(R.id.txtUsername)
        TextPassword= findViewById(R.id.txtPassword)
        TextEmail = findViewById(R.id.txtEmail)
        TextFirstName = findViewById(R.id.txtFirstName)
        TextLastname= findViewById(R.id.txtLastName)


        buttonRegister.setOnClickListener{

            if(TextUsername.text.isEmpty() or TextPassword.text.isEmpty() or TextEmail.text.isEmpty() or TextFirstName.text.isEmpty() or TextLastname.text.isEmpty()){
                Toast.makeText(this,"Please Fill All Areas",Toast.LENGTH_SHORT).show()

            }
            else{
                handler.insertUserData(TextUsername.text.toString(),TextPassword.text.toString(),TextEmail.text.toString(),TextFirstName.text.toString(),TextLastname.text.toString())
                Toast.makeText(this,"You Are Successfully Registered",Toast.LENGTH_SHORT).show()

                val loginIntent = Intent(this,LoginForUserActivity::class.java)
                startActivity(loginIntent)
                TextUsername.text.clear()
                TextPassword.text.clear()
                TextEmail.text.clear()
                TextFirstName.text.clear()
                TextLastname.text.clear()
            }




        }
    }
}
