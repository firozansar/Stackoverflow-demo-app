package info.firozansari.stackoverflowapp.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import info.firozansari.stackoverflowapp.R
import info.firozansari.stackoverflowapp.ViewModelFactory
import javax.inject.Inject

class MainFragment : Fragment() {

    @Inject
    internal lateinit var viewModelFactory: ViewModelFactory<MainViewModel>
    private lateinit var viewModel: MainViewModel

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }


}