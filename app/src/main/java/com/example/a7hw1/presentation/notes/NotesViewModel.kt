package com.example.a7hw1.presentation.notes

import com.example.a7hw1.domain.model.Note
import com.example.a7hw1.domain.usecase.DeleteNoteUseCase
import com.example.a7hw1.domain.usecase.GetAllNotesUseCase
import com.example.a7hw1.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val getAllNotesUseCase: GetAllNotesUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase
) : BaseViewModel() {

    private val _notesState = MutableStateFlow<UiState<List<Note>>>(UiState.Empty())
    val noteState = _notesState.asStateFlow()
    private val _deleteNoteState = MutableStateFlow<UiState<Unit>>(UiState.Empty())
    val deleteNoteState = _deleteNoteState.asStateFlow()


    private val _createNoteState = MutableStateFlow<UiState<Unit>>(UiState.Empty())
    val createNoteState = _createNoteState.asStateFlow()

    init {
        getAllNotes()
    }

    fun getAllNotes() {
        getAllNotesUseCase().collectFlow(_notesState)
    }

    fun deleteNote(note: Note) {
        deleteNoteUseCase(note).collectFlow(_deleteNoteState)
    }
}