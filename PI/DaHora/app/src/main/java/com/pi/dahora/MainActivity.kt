package com.pi.dahora

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pi.dahora.model.User
import org.ktorm.database.Database
import org.ktorm.dsl.from
import org.ktorm.dsl.select
import android.widget.Toast

import com.pi.dahora.model.Posts
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class MainActivity : AppCompatActivity() {


    private lateinit var _users: List<User>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        getData()

//        findViewById<Button>(R.id.login_btn).setOnClickListener{ login() }
    }

//    private fun login(){
//        val name: String = findViewById<EditText>(R.id.name).text.toString()
//        val password: String = findViewById<EditText>(R.id.password).text.toString()
//
//        try{
//
//            _users = _userDao.getAll()
//
//            _db.userDao().insertAll(User(name, password))
//
//        }catch (e: Exception){
//            println("Unexpected error: $e")
//        }
//    }


//    private fun attHistory(){
//        try{
//            val userDao = _db.userDao()
//            _users = userDao.getAll()
//            findViewById<TextView>(R.id.name).text = _users[0].name
//            findViewById<TextView>(R.id.password).text = _users[0].password
//
//        }catch (e : Exception){
//            println("Unexpected error: $e")
//        }
//    }


//    private fun x(){
//        setContentView(R.layout.activity_requirement_history_studant)
//        findViewById<Button>(R.id.att).setOnClickListener{_ -> run { attHistory() } }
//
//
//    }

    fun getData() {
        val retrofitClient = NetworkUtils
            .getRetrofitInstance("https://jsonplaceholder.typicode.com")

        val endpoint = retrofitClient.create(Endpoint::class.java)
        val callback = endpoint.getPosts()

        callback.enqueue(object : Callback<List<Posts>> {
            override fun onFailure(call: Call<List<Posts>>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<List<Posts>>, response: Response<List<Posts>>) {
                response.body()?.forEach {
                    println(response)
                }
            }
        })

    }
    private fun getConnection() {
//        return Room.databaseBuilder(
//            applicationContext,
//            AppDatabase::class.java, "database-name"
//        ).allowMainThreadQueries().fallbackToDestructiveMigration().build()

        //try{

//            var hostname = "localhost"
//            var databaseName = "da_hora"
//            var username = "root"
//            var password = "15975328A!"

//            Class.forName("com.mysql.cj.jdbc.Driver");
//            val jdbcUrl = "jdbc:mysql://$hostname:3306/$databaseName?user=$username&password=$password&useSSL=false"
//            val URL =
//                "jdbc:mysql://localhost:3306/da_hora?user=$username&password=$password&useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=GMT"
//
//            //val database = Database.connect("jdbc:mysql://localhost:3306/da_hora?user=root&password=15975328A!&useSSL=false")
//
//            val database = Database.connect(jdbcUrl)
//
//            for (row in database.from(User).select()) {
//                println(row[User.name])
//            }
//        } catch(e: Exception){
//            println(e)
//        }







    }



}