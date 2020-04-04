package tk.dsjin.protectednote.presenter.notes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tk.dsjin.protectednote.R
import tk.dsjin.protectednote.presenter.notes.viewholder.NoteViewHolder
import tk.dsjin.protectednote.usecase.model.NoteModel

class NotesAdapter : RecyclerView.Adapter<NoteViewHolder>(){
    var noteList : List<NoteModel> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder = NoteViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false))

    override fun getItemCount(): Int = noteList.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bindTo(noteList[position])
    }
}