package com.pi.dahora.coordinator

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.pi.dahora.ItemClickListener
import com.pi.dahora.Models.*
import com.pi.dahora.Models.Enum.Status
import com.pi.dahora.R
import com.pi.dahora.RequirementAdpter
import com.pi.dahora.ViewRequisitionFragment
import com.pi.dahora.databinding.FragmentPendingRequestsBinding
import com.pi.dahora.databinding.FragmentRequerimentHistoryStudantBinding
import com.pi.dahora.utils.LoginUser
import com.pi.dahora.utils.NetworkUtils
import com.pi.dahora.utils.Requirements
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction


class RequerimentPendingFragment : Fragment() {
    private lateinit var students: List<Student>
    private lateinit var courses: List<Course>
    private lateinit var binding: FragmentPendingRequestsBinding
    private lateinit var requirements : List<Requirement>


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentPendingRequestsBinding.inflate(layoutInflater)
        getDataCourses()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getDataCourses()

    }

    private fun recycleView(){

        val onClickListener = ItemClickListener{req ->
            val fragment = ViewRequisitionFragment(req)
            parentFragmentManager.beginTransaction().addToBackStack("requerimentPending").replace(R.id.fragmentContainerView_Coordinator, fragment).commit()
        }

        val recyclerView = binding.recycleViewC
        val adpter = RequirementAdpter(requirements, onClickListener)
        val layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false )

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adpter
    }

    private fun getDataCourses() {
        val retrofitClient = NetworkUtils.getRetrofitInstance("https://apidahora.herokuapp.com/api/")
        val endpoint = retrofitClient.create(EndpointCourse::class.java)
        val callback = endpoint.getCourses()

        callback.enqueue(object : Callback<List<Course>> {
            override fun onFailure(call: Call<List<Course>>, t: Throwable) {
            }

            override fun onResponse(call: Call<List<Course>>, response: Response<List<Course>>) {
                val coursesTemp = response.body()

                if (coursesTemp != null){
                    courses = filterCourses(coursesTemp)
                    getDataStudents()

                }
            }
        })
    }



    private fun getDataStudents() {
        val retrofitClient = NetworkUtils.getRetrofitInstance("https://apidahora.herokuapp.com/api/")
        val endpoint = retrofitClient.create(EndpointStudent::class.java)
        val callback = endpoint.getStudents()

        callback.enqueue(object : Callback<List<Student>> {
            override fun onFailure(call: Call<List<Student>>, t: Throwable) {
            }

            override fun onResponse(call: Call<List<Student>>, response: Response<List<Student>>) {
                val studentsTemp = response.body()

                if (studentsTemp != null){
                    students = filterStudents(studentsTemp)
                    getDataRequeriments()

                }
            }
        })
    }

    private fun getDataRequeriments() {

        val retrofitClient = NetworkUtils.getRetrofitInstance("https://apidahora.herokuapp.com/api/")
        val endpoint = retrofitClient.create(EndpointRequirement::class.java)
        val callback = endpoint.getRequirements()

        callback.enqueue(object : Callback<List<Requirement>> {
            override fun onFailure(call: Call<List<Requirement>>, t: Throwable) {
            }

            override fun onResponse(call: Call<List<Requirement>>, response: Response<List<Requirement>>) {
                val requirementsTemp = response.body()

                if (requirementsTemp != null){
                    requirements = filterRequirements(requirementsTemp)
                    recycleView()
                }
            }
        })
    }

    private fun filterCourses(courses: List<Course>): List<Course> {
        return courses.filter { x -> x.coordinator == LoginUser.userLogged.id.toLong() }
    }

    private fun filterStudents(students: List<Student>): List<Student> {
        var studentsFiltered : MutableList<Student> = arrayListOf()
        courses.forEach {course ->
            studentsFiltered.addAll(students.filter { student ->
                student.course == course.id.toLong()
            })
        }
        return studentsFiltered
    }


    private fun filterRequirements(requirements: List<Requirement>): List<Requirement> {

        var requirementsFiltered : MutableList<Requirement> = arrayListOf()


        students.forEach {student ->
            requirementsFiltered.addAll(requirements.filter { requirement ->
                requirement.student == student.id.toLong() && requirement.type == Status.CREATED.printableName
            })
        }
        return requirementsFiltered


    }

}