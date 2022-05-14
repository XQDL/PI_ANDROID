package com.pi.dahora.utils

import java.util.*

class TimeUtils {
    companion object{
        fun getAtualHour(): Date{
            val date = Calendar.getInstance().time

            val cal = Calendar.getInstance()
            val day = cal[Calendar.DATE]
            val month = cal[Calendar.MONTH] + 1
            val year = cal[Calendar.YEAR]
            val dow = cal[Calendar.DAY_OF_WEEK]
            val dom = cal[Calendar.DAY_OF_MONTH]
            val doy = cal[Calendar.DAY_OF_YEAR]

            return date
        }
    }
}