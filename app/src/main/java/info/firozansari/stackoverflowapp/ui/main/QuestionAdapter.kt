package info.firozansari.stackoverflowapp.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import info.firozansari.stackoverflowapp.api.model.Question
import info.firozansari.stackoverflowapp.databinding.RowLayoutBinding


class QuestionAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<Question, QuestionAdapter.QuestionViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder =
        QuestionViewHolder(RowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class QuestionViewHolder(
        private val binding: RowLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(question: Question) = binding.apply {
            titleText.text = question.title
            votesText.text = "votes ${question.score}"
        }
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