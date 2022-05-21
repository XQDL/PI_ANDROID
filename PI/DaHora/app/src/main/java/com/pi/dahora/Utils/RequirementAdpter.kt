package com.pi.dahora

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pi.dahora.Models.Requirement
import com.pi.dahora.databinding.ItemRequirementBinding
import com.pi.dahora.utils.TimeUtils


class RequirementAdpter(
    private val list : List<Requirement>,
    private val onItemClickListener : ItemClickListener
): RecyclerView.Adapter<RequirementViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequirementViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_requirement, parent, false)

        return RequirementViewHolder(view)
    }

    override fun onBindViewHolder(holder: RequirementViewHolder, position: Int) {
        val requeriment = list[position]
        holder.bind(requeriment, onItemClickListener)
    }

    override fun getItemCount(): Int = list.size
}

class RequirementViewHolder(private val view: View) : RecyclerView.ViewHolder(view){
    private var binding : ItemRequirementBinding = ItemRequirementBinding.bind(view)

    fun bind(requirement: Requirement, onItemClickListener: ItemClickListener) {

        view.apply {
            setOnClickListener{ onItemClickListener.onClick(requirement)}
            binding.ImageViewIconStatus.setImageResource(setImage(requirement.type))
            binding.TextViewTittle.text = requirement.tittle
            binding.TextViewExtra.text = formatHour(requirement.workLoad.toString()) + " Horas"
            binding.TextViewDate.text = dateFormater(requirement.createdTime)
        }
    }

    private fun dateFormater(date : String): String{
        var splitedDate = date.split("T")[0].split("-")

        var day = splitedDate[2].toInt()
        var month = splitedDate[1].toInt()
        var year = splitedDate[0].toInt()

        return makeDateString(day, month, year)
    }

    private fun makeDateString(day: Int, month: Int, year: Int): String {
        return TimeUtils.getMonthString(month) + "/" + day + "/" + year
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

    private fun setImage(staus: String?): Int{
        val drawable = when (staus){
            "created" -> R.drawable.ic_baseline_remove_circle_outline_24
            "approved" -> R.drawable.ic_baseline_check_circle_outline_24
            "denied" -> R.drawable.ic_baseline_do_not_disturb_alt_24
            else -> R.drawable.ic_baseline_remove_circle_outline_24
        }
        return drawable
    }
}

fun interface ItemClickListener{
    fun onClick(requirement: Requirement)
}