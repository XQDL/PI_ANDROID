package com.pi.dahora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.room.Room
import com.pi.dahora.database.AppDatabase
import com.pi.dahora.model.User

class RequirementHistoryStudent : AppCompatActivity() {

    private lateinit var _users: List<User>
    private lateinit var _db: AppDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_requirement_history_studant)

        _db = getConnection()

        //_db.userDao().insertAll(User(1, "Andre", "123"),
          //  User(2, "Adailson", "123"))


        findViewById<Button>(R.id.att).setOnClickListener{attHistory()}

        try{
            val userDao = _db.userDao()
            _users = userDao.getAll()

            findViewById<TextView>(R.id.name).text = _users[0].name
            findViewById<TextView>(R.id.password).text = _users[0].password

        }catch (e : Exception){
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





    private fun getConnection(): AppDatabase {
        return Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
    }



}