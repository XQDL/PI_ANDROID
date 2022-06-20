package com.pi.dahora.utils

import com.pi.dahora.studant.CreateRequerimentFragment
import java.util.*

class TimeUtils {

    companion object{

        fun getAtualHour(): String{
            //val date = Calendar.getInstance().time

            val date = Calendar.getInstance()
            val day = date[Calendar.DATE]
            val month = date[Calendar.MONTH] + 1
            val year = date[Calendar.YEAR]
            val hour = date[Calendar.HOUR]
            val min = date[Calendar.MINUTE]
            val seg = date[Calendar.SECOND]

            val stringDate : String = year.toString() + "-" + retorneString(month) + "-" +
                    retorneString(day) + "T" + retorneString(hour) + ":" + retorneString(min) + ":" + retorneString(seg)
            
            return stringDate
        }

        fun retorneString(n : Int) : String{
            var stringNumber: String = n.toString()

            if (n<10){
                stringNumber = "0" + n.toString()
            }
            return stringNumber
        }

        fun getMonthString(month: Int): String {
            val monthString = when (month) {
                1 -> "JAN"
                2 -> "FEV"
                3 -> "MAR"
                4 -> "ABR"
                5 -> "MAIO"
                6 -> "JUN"
                7 -> "JUL"
                8 -> "AGO"
                9 -> "SET"
                10 -> "OUT"
                11 -> "NOV"
                else -> "DEZ"
            }
            return monthString
        }

        fun getMonthInt(month: String): Int {
            val monthString = when (month) {
                "JAN" -> 1
                "FEV" -> 2
                "MAR" -> 3
                "ABR" -> 4
                "MAIO" -> 5
                "JUN" -> 6
                "JUL" -> 7
                "AGO" -> 8
                "SET" -> 9
                "OUT" -> 10
                "NOV" -> 11
                else -> 12
            }
            return monthString
        }

        fun formatHour(hour: String): String {
            var splited = hour.split(".")
            var formattedHour : String

            if(splited[1] == "0"){
                formattedHour = splited[0]
            } else{
                formattedHour = hour
            }

            return formattedHour+" Horas"
        }

        fun dateFormater(date : String): String{
            var splitedDate = date.split("T")[0].split("-")

            var day = splitedDate[2].toInt()
            var month = splitedDate[1].toInt()
            var year = splitedDate[0].toInt()

            return makeDateString(day, month, year)
        }

        fun toPortugues(type: String): String {
            when (type) {
                "approved" -> return "APROVADO"
                "denied" -> return "REPROVADO"
                else -> return "CRIADO"
            }
        }

        private fun makeDateString(day: Int, month: Int, year: Int): String {
            return  formatDay(day) + "/" + TimeUtils.getMonthString(month) + "/" + year
        }

        private fun formatDay(day: Int) : String{
            if (day < 10){
                return "0$day"
            }
            return day.toString()
        }
    }
}