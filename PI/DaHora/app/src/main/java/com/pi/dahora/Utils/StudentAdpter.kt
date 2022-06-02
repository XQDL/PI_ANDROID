package com.pi.dahora.Utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pi.dahora.Models.Requirement
import com.pi.dahora.Models.Student
import com.pi.dahora.R
import com.pi.dahora.databinding.ItemRequirementBinding
import com.pi.dahora.databinding.ItemStudentBinding
import com.pi.dahora.utils.TimeUtils

class StudentAdpter (
    private val list : List<Student>,
    private val onItemClickListener : ItemClickListener
    ): RecyclerView.Adapter<StudentViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_student, parent, false)

            return StudentViewHolder(view)
        }

        override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
            val student = list[position]
            holder.bind(student, onItemClickListener)
        }

        override fun getItemCount(): Int = list.size
    }

    class StudentViewHolder(private val view: View) : RecyclerView.ViewHolder(view){
        private var binding : ItemStudentBinding = ItemStudentBinding.bind(view)

        fun bind(student: Student, onItemClickListener: ItemClickListener) {

            view.apply {
                setOnClickListener{ onItemClickListener.onClick(student)}
                binding.ImageViewIconStatus.setImageResource(setImage(student.hasCompletedHours))
                binding.TextViewTittle.text = student.name
                binding.TextViewExtra.text = student.email
                binding.TextViewHours.text = formatHour(student.additionalHoursPerformed.toString()) + " Horas"
            }
        }
        private fun formatHour(hour: String): String {
            var splited = hour.split(".")
            var formattedHour : String

            if(splited[1] == "0"){
                formattedHour = splited[0]
            } else{
                formattedHour = hour
            }

            return formattedHour
        }
        private fun setImage(completed: Boolean): Int{
            val drawable = when (completed){
                false -> R.drawable.ic_baseline_remove_circle_outline_24
                true -> R.drawable.ic_baseline_check_circle_outline_24

            }
            return drawable
        }
    }

fun interface ItemClickListener{
    fun onClick(student: Student)
}
