package com.example.a7hw1.presentation.notes

import androidx.lifecycle.ViewModel
import com.example.a7hw1.domain.model.Note
import com.example.a7hw1.domain.usecase.CreateNoteUseCase
import com.example.a7hw1.domain.usecase.EditNoteUseCase
import com.example.a7hw1.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CreateNoteViewModel @Inject constructor(
    private val createNoteUseCase: CreateNoteUseCase,
    private val editNoteUseCase: EditNoteUseCase
): BaseViewModel() {
    private val _createNoteState = MutableStateFlow<UiState<List<Note>>>(UiState.Empty())
    val createNoteState = _createNoteState.asStateFlow()
    private val _editNoteState = MutableStateFlow<UiState<List<Note>>>(UiState.Empty())
    val editNoteState = _editNoteState.asStateFlow()

    fun createNote(note: Note) {
        createNoteUseCase(note).collectFlow(_createNoteState)
    }

    fun editNote(note: Note) {
        editNoteUseCase(note).collectFlow(_editNoteState)
    }
}