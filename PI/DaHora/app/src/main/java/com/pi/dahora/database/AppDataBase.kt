package com.pi.dahora.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pi.dahora.dao.UserDao
import com.pi.dahora.model.User

@Database(entities = [User::class], version = 3)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}