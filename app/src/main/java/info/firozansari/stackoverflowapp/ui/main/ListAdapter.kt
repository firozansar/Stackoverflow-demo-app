package info.firozansari.stackoverflowapp.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import info.firozansari.stackoverflowapp.databinding.RowLayoutBinding
import info.firozansari.stackoverflowapp.api.model.Question

class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    var dataList = emptyList<Question>()

    class MyViewHolder(private val binding: RowLayoutBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(question: Question){
            binding.question = question
            binding.executePendingBindings()
        }
        companion object{
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RowLayoutBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(
                    binding
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(
            parent
        )
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = dataList[position]
        holder.bind(currentItem)
    }

    fun setData(questions: List<Question>){
        this.dataList = questions
    }

}