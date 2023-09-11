package project.todyapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.LinearLayoutManager
import project.todyapp.adapter.TaskAdapter
import project.todyapp.database.AppDataBase
import project.todyapp.database.entity.Task
import project.todyapp.databinding.FragmentCreateTaskBinding
import java.text.SimpleDateFormat
import java.util.Calendar

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CreateTaskFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CreateTaskFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: Task? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getSerializable(ARG_PARAM2) as Task
        }
    }

    val appDatabase: AppDataBase by lazy {
        AppDataBase.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        var tasks: List<Task> = appDatabase.getTaskDao().getTasks()
        val animation = AnimationUtils.loadAnimation(requireContext(),R.anim.anim)
        val binding = FragmentCreateTaskBinding.inflate(inflater,container,false)
        var calendar: Calendar = Calendar.getInstance()
        var simpleDateFormat = SimpleDateFormat.getInstance()

        var date:String = simpleDateFormat.format(calendar.time)
        binding.createTask.startAnimation(animation)

        binding.createTask.setOnClickListener{
            parentFragmentManager.beginTransaction().replace(R.id.create,TaskFragment()).commit()
        }

        val adapter = TaskAdapter(tasks, requireContext(), object : TaskAdapter.ItemClick {
            override fun OnItemClick(task: Task, position: Int) {
                parentFragmentManager.beginTransaction().replace(R.id.create,TaskFragment.newInstance(date,task))
                    .commit()
            }
        })

        var manager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = manager


        return binding.root
    }



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CreateTaskFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String) =
            CreateTaskFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)

                }
            }




    }
}