package com.example.a7hw1.domain.usecase

import com.example.a7hw1.domain.model.Note
import com.example.a7hw1.domain.repository.NoteRepository

class GetAllNotesUseCase(
    private val noteRepository: NoteRepository
) {
    fun getAllNotes(note: Note) = noteRepository.getAllNotes()
}