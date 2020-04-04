package tk.dsjin.protectednote.presenter.notes.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import tk.dsjin.protectednote.databinding.ItemNoteBinding
import tk.dsjin.protectednote.usecase.model.NoteModel

class NoteViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    private val binding = ItemNoteBinding.bind(itemView)

    fun bindTo(note : NoteModel){
        binding.note = note
    }
}