package project.todyapp.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import project.todyapp.database.entity.Task

@Dao
interface TaskDao {
    @Query("select * from task")
    fun getTasks():List<Task>
    @Insert
    fun addTask(task: Task)
}