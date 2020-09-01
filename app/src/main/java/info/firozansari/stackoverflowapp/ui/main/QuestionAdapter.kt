package info.firozansari.stackoverflowapp.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import info.firozansari.stackoverflowapp.databinding.RowLayoutBinding
import info.firozansari.stackoverflowapp.api.model.Question

class QuestionAdapter (private val onClickListener: OnClickListener) : ListAdapter<Question, QuestionAdapter.MyViewHolder>(DiffCallback) {

    var questionList = emptyList<Question>()

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
        return questionList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentQuestion = questionList[position]
        holder.itemView.setOnClickListener {
            onClickListener.onClick(currentQuestion)
        }
        holder.bind(currentQuestion)
    }

    fun setData(questions: List<Question>){
        this.questionList = questions
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Question>() {

        override fun areItemsTheSame(oldItem: Question, newItem: Question): Boolean {
            return oldItem.questionID == newItem.questionID
        }

        override fun areContentsTheSame(oldItem: Question, newItem: Question): Boolean {
            return oldItem == newItem
        }
    }

    class OnClickListener(val clickListener: (question: Question) -> Unit) {
        fun onClick(question: Question) = clickListener(question)
    }


}