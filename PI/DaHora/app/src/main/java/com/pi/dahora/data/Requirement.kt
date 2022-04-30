package com.pi.dahora.data

import com.pi.dahora.utils.Reason
import java.util.*

data class Requirement(
    val id : Int,
    val title : String,
    val start_date : Date,
    val end_date : Date,
    val workload : Int,
    val comments : String,
    val attachment_address : String,
    val institution_name : String,
    val creatad_time: Date,
    val approved_time : Date,
    val reason: Reason
)
