package info.firozansari.stackoverflowapp.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import info.firozansari.stackoverflowapp.R
import info.firozansari.stackoverflowapp.api.model.Question
import kotlinx.android.synthetic.main.row_layout.view.*


class QuestionAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<Question, QuestionAdapter.QuestionViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_layout, parent, false)
        return QuestionViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        val trailer = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(trailer)
        }
        holder.bind(trailer)
    }

    class QuestionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(question: Question) {
            itemView.title_textView.text = question.title
            itemView.votes_textView.text = "votes ${question.score}"
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