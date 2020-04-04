package tk.dsjin.protectednote

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import tk.dsjin.protectednote.presenter.notes.adapter.NotesAdapter
import tk.dsjin.protectednote.presenter.notes.datatest.Note

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rv = findViewById<RecyclerView>(R.id.test_rec)
        val adapter = NotesAdapter()
        adapter.noteList = Note.getNotes()
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = adapter
        adapter.notifyDataSetChanged()
    }
}
