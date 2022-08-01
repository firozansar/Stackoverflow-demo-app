package info.firozansari.stackoverflowapp.ui.main

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import info.firozansari.stackoverflowapp.R
import info.firozansari.stackoverflowapp.api.ApiStatus
import info.firozansari.stackoverflowapp.ui.MainApplication
import kotlinx.android.synthetic.main.main_fragment.*
import javax.inject.Inject

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()

    private val adapter: QuestionAdapter by lazy {
        QuestionAdapter(QuestionAdapter.OnClickListener {
            requireActivity().startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(it.link)
                )
            )
        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as MainApplication).appComponent.inject(this)
    }


    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupRecyclerView()
        observeApiStatus()
    }

    private fun setupRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.adapter = adapter
    }

    private fun observeApiStatus() {
        viewModel.apiStatus.observe(viewLifecycleOwner, Observer {
            it?.let {
                when (it) {
                    ApiStatus.LOADING -> {
                        status_imageView.visibility = View.VISIBLE
                        status_imageView.setImageResource(R.drawable.loading_animation)
                    }
                    ApiStatus.ERROR -> {
                        status_imageView.visibility = View.VISIBLE
                        status_imageView.setImageResource(R.drawable.ic_connection_error)
                    }
                    ApiStatus.DONE -> {
                        status_imageView.visibility = View.GONE
                        observeQuestions()
                    }

                }
            }
        })
    }

    private fun observeQuestions() {
        viewModel.questions.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })
    }

}