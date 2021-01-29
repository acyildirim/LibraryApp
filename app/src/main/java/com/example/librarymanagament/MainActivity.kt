package com.example.librarymanagament

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val buttonLibrary : Button = findViewById(R.id.button_library)
        buttonLibrary.setOnClickListener{
            val loginIntent = Intent(this,LoginActivity::class.java)
            startActivity(loginIntent)
        }
        val buttonUser: Button = findViewById(R.id.button_User)
        buttonUser.setOnClickListener{
            val userLoginIntent = Intent(this,LoginForUserActivity::class.java)
            startActivity(userLoginIntent)
        }

    }



}
