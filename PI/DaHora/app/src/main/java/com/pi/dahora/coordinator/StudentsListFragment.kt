package com.pi.dahora.coordinator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import com.pi.dahora.Models.EndpointStudent
import com.pi.dahora.Models.Student
import com.pi.dahora.Utils.ItemClickListener
import com.pi.dahora.Utils.StudentAdpter
import com.pi.dahora.databinding.FragmentCoordinatorProfileBinding
import com.pi.dahora.databinding.FragmentStudentsListBinding
import com.pi.dahora.utils.LoginUser
import com.pi.dahora.utils.NetworkUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StudentsListFragment : Fragment() {
    private lateinit var binding: FragmentStudentsListBinding
    private lateinit var students: List<Student>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentStudentsListBinding.inflate(inflater)

        getData()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun recycleView(){
        val onClickListener = ItemClickListener{student ->
            bottomSheet(student)
//            val fragment = ViewStudentFragment(student)
//
//            parentFragmentManager.beginTransaction().replace(R.id.fragmentContainerView_Student, fragment).commit()
        }

        val recyclerView = binding.recycleViewSt
        val adpter = StudentAdpter(students, onClickListener)
        val layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false )

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adpter
    }


    private fun getData() {
        Snackbar.make(binding.root,"Carregando...", Snackbar.LENGTH_INDEFINITE).show()

        val retrofitClient =
            NetworkUtils.getRetrofitInstance("https://apidahora.herokuapp.com/api/")
        val endpoint = retrofitClient.create(EndpointStudent::class.java)
        var id = LoginUser.userLogged.id.toLong()

        val callback = endpoint.getStudentsByCoordinator(id)

        callback.enqueue(object : Callback<List<Student>> {
            override fun onFailure(call: Call<List<Student>>, t: Throwable) {
            }

            override fun onResponse(call: Call<List<Student>>, response: Response<List<Student>>) {
                val studentsTemp = response.body()

                if (studentsTemp != null) {
                    students = studentsTemp
                    recycleView()
                    Snackbar.make(binding.root,"", 1).show()

                }
            }
        })
    }

    private fun bottomSheet(student: Student){

        val bottomSheetBinding : FragmentCoordinatorProfileBinding =  FragmentCoordinatorProfileBinding.inflate(layoutInflater)

        val bottomSheetDialog = BottomSheetDialog(requireContext())
        bottomSheetDialog.setContentView(bottomSheetBinding.root)

        bottomSheetBinding.profileName.text = student.name
        bottomSheetBinding.description.text = student.course.toString()
        bottomSheetBinding.email.text = student.email
        bottomSheetBinding.numberPhone.text = student.password

        bottomSheetDialog.show()
    }



}