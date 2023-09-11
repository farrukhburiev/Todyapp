package project.todyapp

import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import project.todyapp.database.AppDataBase
import project.todyapp.database.entity.Task
import project.todyapp.databinding.FragmentTaskBinding
import java.time.LocalTime

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TaskFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TaskFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: Task? = null
    var dueTime:LocalTime? = null
    var time:String? = null
    var date:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getSerializable(ARG_PARAM2) as Task
        }
    }
    val appDataBase: AppDataBase by lazy {
        AppDataBase.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentTaskBinding.inflate(inflater,container,false)


//        var tasks = appDataBase.getTaskDao().getTasks()
        binding.date!!.text = param1
//        if (param2 != null){
//            for (i in tasks){
//                if (i.id == param2!!.id){
////                    binding.textOrg.text =
////                    binding.date.text = param2!!.date
////
//
//                }
//            }
//        }

        binding.textOrg?.addTextChangedListener {
            if (binding.textOrg?.text?.length!! > 0){
                binding.send!!.visibility = View.VISIBLE
            }
            else{
                binding.send!!.visibility = View.INVISIBLE
//                Toast.makeText(requireContext(), "title or task cannot be empty", Toast.LENGTH_SHORT).show()
            }
        }

        binding.send!!.setOnClickListener{
            binding.dateLayout!!.visibility = View.VISIBLE
            binding.backConstr!!.visibility = View.VISIBLE
        }

        binding.calendarView!!.setOnDateChangeListener { view, year, month, dayOfMonth ->
            Toast.makeText(requireContext(), dayOfMonth.toString() + "/" + month.toString() + "/" + year.toString(), Toast.LENGTH_LONG).show();
            date = dayOfMonth.toString() + "/" + month.toString() + "/" + year.toString()
        }
        binding.reschedule!!.setOnClickListener {
            openTimePicker()
        }

        binding.save!!.setOnClickListener {
            appDataBase.getTaskDao().addTask(Task(task = binding.textOrg.text.toString(), time = time!!, date = date!!, title = binding.titleOrg.text.toString()))
            parentFragmentManager.beginTransaction().replace(R.id.task_fragment,CreateTaskFragment()).commit()
            Log.d("AAAA", "onCreateView: "+ time)

        }

        binding.back.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.task_fragment,CreateTaskFragment()).commit()
        }





        return binding.root
    }

    private fun openTimePicker() {
        if (dueTime == null)
            dueTime = LocalTime.now()
            val listener = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                dueTime = LocalTime.of(hourOfDay, minute)
                time = String.format("%02d:%02d", dueTime!!.hour, dueTime!!.minute)

            }

        val dialog = TimePickerDialog(requireContext(),listener,dueTime!!.hour, dueTime!!.minute,true)
        dialog.setTitle("Enter youtr task time")

        dialog.show()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TaskFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String) =
            TaskFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }

        fun new(task:
        ) =
            CreateTaskFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM2, task)

                }
            }
    }
}