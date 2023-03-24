package com.example.a7hw1.presentation.create_note

import com.example.a7hw1.domain.model.Note
import com.example.a7hw1.domain.usecase.CreateNoteUseCase
import com.example.a7hw1.domain.usecase.EditNoteUseCase
import com.example.a7hw1.presentation.base.BaseViewModel
import com.example.a7hw1.presentation.notes.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CreateNoteViewModel @Inject constructor(
    private val createNoteUseCase: CreateNoteUseCase,
    private val editNoteUseCase: EditNoteUseCase
) : BaseViewModel() {
    private val _createNoteState = MutableStateFlow<UiState<Unit>>(UiState.Empty())
    val createNoteState = _createNoteState.asStateFlow()
    private val _editNoteState = MutableStateFlow<UiState<Unit>>(UiState.Empty())
    val editNoteState = _editNoteState.asStateFlow()

    fun createNote(title: String, desc: String) {
        if (title.isNotEmpty() && title.isNotBlank()) {
            createNoteUseCase(
                Note(
                    title = title,
                    description = desc,
                    createAt = System.currentTimeMillis()
                )
            ).collectFlow(_createNoteState)
        } else {
            _createNoteState.value = UiState.Error("Title is empty!")
        }
    }

    fun editNote(note: Note) {
        if (note.title.isNotEmpty() && note.title.isNotBlank()) {
            editNoteUseCase(
                Note(
                    title = note.title,
                    description = note.description,
                    createAt = System.currentTimeMillis()
                )
            ).collectFlow(_editNoteState)
        } else {
            _editNoteState.value = UiState.Error("Title is empty!")
        }
    }

}