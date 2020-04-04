package tk.dsjin.protectednote.presenter.notes

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import tk.dsjin.protectednote.R

class ListNotesFragment : Fragment() {

    companion object {
        fun newInstance() = ListNotesFragment()
    }

    private lateinit var viewModel: ListNotesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.list_notes_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ListNotesViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
