package project.todyapp.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
class Task(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var task:String,
    var time:String,
    var date:String,
    var title:String
) : Serializable