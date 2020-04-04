package tk.dsjin.protectednote.presenter.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import tk.dsjin.protectednote.usecase.NoteUseCase
import tk.dsjin.protectednote.usecase.model.NoteModel

class ListNotesViewModel(private val useCase : NoteUseCase) : ViewModel() {
    private var _notes : LiveData<List<NoteModel>>? = MutableLiveData<List<NoteModel>>().apply {
        value = emptyList()
    }
    val notes : LiveData<List<NoteModel>>?
        get() = _notes
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : MutableLiveData<Boolean>
        get() = _isLoading

    fun start() {
        getNotes()
    }

    private fun getNotes() {
        _isLoading.value = true
        val noteResult = useCase.getAllNotes()
        when(noteResult.result){
            NoteUseCase.ResultStatus.Success -> {
                _notes = noteResult.data
                _isLoading.value = false
            }
            else -> _isLoading.value = false
        }
    }
}
