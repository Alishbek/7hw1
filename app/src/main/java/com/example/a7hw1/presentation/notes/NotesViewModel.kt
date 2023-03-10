package com.example.a7hw1.presentation.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a7hw1.domain.model.Note
import com.example.a7hw1.domain.usecase.CreateNoteUseCase
import com.example.a7hw1.domain.usecase.GetAllNotesUseCase
import com.example.a7hw1.domain.utils.ResultStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val getAllNotesUseCase: GetAllNotesUseCase,
    private val createNoteUseCase: CreateNoteUseCase
) : ViewModel() {

    private val _notesState = MutableStateFlow<UiState<List<Note>>>(UiState.Empty())
    val noteState = _notesState.asStateFlow()
    fun getAllNotes() {
        viewModelScope.launch {
            getAllNotesUseCase.getAllNotes().collect {
                when (it) {
                    is ResultStatus.Error -> {
                        _notesState.value = UiState.Error(it.error)
                    }
                    is ResultStatus.Loading -> {
                        _notesState.value = UiState.Loading()
                    }
                    is ResultStatus.Success -> {
                        if (it.data != null)
                        _notesState.value = UiState.Success(it.data)
                    }
                }
            }
        }
    }
}