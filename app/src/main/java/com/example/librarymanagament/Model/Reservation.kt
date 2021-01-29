package com.example.librarymanagament.Model

class Reservation {

    var reservationID : Int = 0
    var Name : String = ""
    var Surname : String = ""
    var StartTime : String = ""
    var FinishTime: String = ""
    var Phone : Int = 0
    var Book : Book = Book()

    constructor(Name: String, Surname: String, StartTime: String, FinishTime: String, Phone: Int) {
        this.Name = Name
        this.Surname = Surname
        this.StartTime = StartTime
        this.FinishTime = FinishTime
        this.Phone = Phone
    }


    constructor(){

    }
}