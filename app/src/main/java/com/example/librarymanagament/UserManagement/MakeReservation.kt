package com.example.librarymanagament.UserManagement

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.example.librarymanagament.Model.DatabaseForLibrary
import com.example.librarymanagament.R
import kotlinx.android.synthetic.main.activity_make_reservation.*
import kotlinx.android.synthetic.main.activity_make_reservation.buttonInsert
import java.text.DateFormat
import java.util.*

class MakeReservation : AppCompatActivity() {
    lateinit var handler: DatabaseForLibrary
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_make_reservation)
        handler = DatabaseForLibrary(this)

        val c : Calendar = Calendar.getInstance();
        val x : String = DateFormat.getDateInstance().format(c.time)


        val txtTıme : EditText = this.findViewById(R.id.txtStartAt)
        txtTıme.setText(x)

        buttonInsert.setOnClickListener{
            if (txtName.text.isEmpty() || txtSurname.text.isEmpty() || txtStartAt.text.isEmpty() || txtFinishAt.text.isEmpty()
                || txtPhoneNo.text.isEmpty() || txtReservationID.text.isEmpty()){
                Toast.makeText(this,"Please Fill All Areas", Toast.LENGTH_SHORT).show()

            }
            else{
                handler.insertReservation(txtName.text.toString(),txtSurname.text.toString(),txtStartAt.text.toString()
                ,txtFinishAt.text.toString(),txtPhoneNo.text.toString(),txtReservationID.text.toString())
                Toast.makeText(this,"SuccessFully Created", Toast.LENGTH_SHORT).show()
                txtName.text.clear()
                txtPhoneNo.text.clear()
                txtReservationID.text.clear()
                txtFinishAt.text.clear()
                txtStartAt.text.clear()
                txtSurname.text.clear()



            }

        }
    }

}
