package com.pi.dahora.utils

import java.util.*

class TimeUtils {
    companion object{
        fun getAtualHour(): Date{
            val date = Calendar.getInstance().time
            return date
        }
    }
}