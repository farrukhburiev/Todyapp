package project.todyapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import project.todyapp.R

class TaskAdapter(
    var array: List<project.todyapp.database.entity.Task>,
    var context: Context,
    var listener: ItemClick
) : RecyclerView.Adapter<TaskAdapter.MyHolder>() {

    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var task_layout = itemView.findViewById<ImageView>(R.id.task_layout)
        var title = itemView.findViewById<TextView>(R.id.paragraph)
        var date = itemView.findViewById<TextView>(R.id.date_task)
        var task = itemView.findViewById<TextView>(R.id.text_task)
        var time = itemView.findViewById<TextView>(R.id.time_clock)
        var more_btn = itemView.findViewById<ImageButton>(R.id.more_btn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        var view =
            LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false)
        return MyHolder(view)
    }

    override fun getItemCount(): Int {
        return array.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {

        var temp = array.get(position)

        holder.date.text = temp.date
        holder.title.text = temp.title
        holder.time.text = temp.time
        holder.task.text = temp.task

        holder.task_layout.setOnClickListener {
            listener.OnItemClick(temp,position)
        }

        holder.more_btn.setOnClickListener {
            listener.OnItemClick(temp,position)
        }



    }

    interface ItemClick {
        fun OnItemClick(
            task: project.todyapp.database.entity.Task,
//            state: Boolean,
            position: Int
        ) }
}

