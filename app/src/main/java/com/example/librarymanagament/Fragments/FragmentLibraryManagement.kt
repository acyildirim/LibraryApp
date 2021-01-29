package com.example.librarymanagament.Fragments

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.librarymanagament.LibraryManagement.BOOKS

import com.example.librarymanagament.LibraryManagement.ManageBook

import com.example.librarymanagament.R


class FragmentLibraryManagement : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view : View = inflater.inflate(R.layout.fragment_fragment_library_managemet, container, false)
        val button : Button =  view.findViewById(R.id.buttonNewBook)

        val buttonBooks : Button = view.findViewById(R.id.buttonBooks)

        buttonBooks.setOnClickListener(View.OnClickListener {

            val intent = Intent(activity,BOOKS::class.java)
            startActivity(intent)

        })


        button.setOnClickListener(View.OnClickListener {
            val intent = Intent(activity,ManageBook::class.java)
            startActivity(intent)

        })
        return view
    }


}
