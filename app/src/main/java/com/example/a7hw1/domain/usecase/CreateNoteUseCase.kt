package com.example.a7hw1.domain.usecase

import com.example.a7hw1.domain.model.Note
import com.example.a7hw1.domain.repository.NoteRepository

class CreateNoteUseCase(
    private val noteRepository: NoteRepository
) {
    fun createNote(note: Note) = noteRepository.createNote(note)
}