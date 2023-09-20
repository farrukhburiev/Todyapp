package project.todyapp.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import project.todyapp.R
import project.todyapp.database.AppDataBase
import project.todyapp.database.entity.Task

class TaskAdapter(
    var array: List<Task>,
    var context: Context,
    var listener: ItemClick
) : RecyclerView.Adapter<TaskAdapter.MyHolder>() {

    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var another_layout = itemView.findViewById<View>(R.id.another_layout)
        var title = itemView.findViewById<TextView>(R.id.paragraph)
        var date = itemView.findViewById<TextView>(R.id.date_task)
        var task = itemView.findViewById<TextView>(R.id.text_task)
        var time = itemView.findViewById<TextView>(R.id.time_clock)
        var more_btn = itemView.findViewById<ImageButton>(R.id.more_btn)
        var spinner = itemView.findViewById<Spinner>(R.id.spinner_options)
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
        var items = arrayOf("", "delete", "edit","camera","choose photo")

        holder.another_layout.setOnClickListener {
            listener.OnItemClick(temp,position)
        }

        holder.more_btn.setOnClickListener {
//            listener.OnItemClick(temp,position)
            listener.OnButtonClick(temp,position)
            holder.spinner.visibility = View.VISIBLE
            var arrayAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item,items)
            holder.spinner.adapter = arrayAdapter

            holder.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    posit: Int,
                    id: Long
                ) {
                    if (items[posit] == "delete"){
                        delete(array.get(position))

                    }

                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }


            }
        }



    }
    val appDataBase: AppDataBase by lazy {
        AppDataBase.getInstance(context)
    }


    interface ItemClick {
        fun OnItemClick(
            task: Task,

            position: Int
        )
        fun OnButtonClick(
            task: Task,

            position: Int
        )}

    fun delete(task: Task){
        appDataBase.getTaskDao().deleteTask(task)
    }
}

