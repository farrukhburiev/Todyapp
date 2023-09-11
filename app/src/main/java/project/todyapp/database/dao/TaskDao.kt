package project.todyapp.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import project.todyapp.database.entity.Task

@Dao
interface TaskDao {
    @Query("select * from task")
    fun getTasks():List<Task>
    @Insert
    fun addTask(task: Task)
    @Update
    fun updateTask(task: Task)
    @Delete
    fun deleteTask(task: Task)

}