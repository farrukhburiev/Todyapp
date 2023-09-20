package project.todyapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import project.todyapp.database.dao.TaskDao
import project.todyapp.database.entity.Task


@Database(entities = [Task::class], version = 1)

abstract class AppDataBase:RoomDatabase() {
    abstract fun getTaskDao():TaskDao

    companion object{
        var instance:AppDataBase? = null

        fun getInstance(context: Context):AppDataBase{
            if(instance == null){
                instance = Room.databaseBuilder(context,AppDataBase::class.java,"app_db")
                    .allowMainThreadQueries().build()
            }
            return instance!!
        }
    }

}