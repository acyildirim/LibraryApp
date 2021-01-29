package com.example.librarymanagament.Fragments

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.librarymanagament.LibraryManagement.BOOKS

import com.example.librarymanagament.R
import com.example.librarymanagament.UserManagement.Books_for_user
import com.example.librarymanagament.UserManagement.MakeReservation
import kotlinx.android.synthetic.main.book_info.*
import kotlinx.android.synthetic.main.book_info.view.*
import kotlinx.android.synthetic.main.fragment_fragment_user_management.*


class FragmentUserManagement : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        val view : View = inflater.inflate(R.layout.fragment_fragment_user_management, container, false)

        val button : Button = view.findViewById(R.id.buttonBooks)
        val buttonReservation : Button = view.findViewById(R.id.buttonMakeReservation)

        buttonReservation.setOnClickListener(View.OnClickListener {


            val intent = Intent(activity, MakeReservation::class.java)
            startActivity(intent)
        })

        button.setOnClickListener(View.OnClickListener {


            val intent = Intent(activity, Books_for_user::class.java)
            startActivity(intent)
        })


        return view
    }


}
