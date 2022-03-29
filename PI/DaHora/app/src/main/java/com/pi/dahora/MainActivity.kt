package com.pi.dahora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.room.Room
import com.pi.dahora.dao.UserDao
import com.pi.dahora.database.AppDatabase
import com.pi.dahora.model.User

class MainActivity : AppCompatActivity() {

    private lateinit var _userDao: UserDao
    private lateinit var _users: List<User>
    private lateinit var _db: AppDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        _db = getConnection()

        _userDao = _db.userDao()

        findViewById<Button>(R.id.login_btn).setOnClickListener{ login() }
    }

    private fun login(){
        val name: String = findViewById<EditText>(R.id.name).text.toString()
        val password: String = findViewById<EditText>(R.id.password).text.toString()

        try{

            _users = _userDao.getAll()

            _db.userDao().insertAll(User(name, password))

        }catch (e: Exception){
            println("Unexpected error: $e")
        }
    }


    private fun attHistory(){
        try{
            val userDao = _db.userDao()
            _users = userDao.getAll()
            findViewById<TextView>(R.id.name).text = _users[0].name
            findViewById<TextView>(R.id.password).text = _users[0].password

        }catch (e : Exception){
            println("Unexpected error: $e")
        }
    }


    private fun x(){
        setContentView(R.layout.activity_requirement_history_studant)
        findViewById<Button>(R.id.att).setOnClickListener{_ -> run { attHistory() } }


    }


    private fun getConnection(): AppDatabase {
//        return Room.databaseBuilder(
//            applicationContext,
//            AppDatabase::class.java, "database-name"
//        ).allowMainThreadQueries().fallbackToDestructiveMigration().build()

        fun main() {
            val database = Database.connect("jdbc:mysql://localhost:3306/DA_HORA", user = "root", password = "15975328A!")

            for (row in database.from(Employees).select()) {
                println(row[Employees.name])
            }
        }



    }



}