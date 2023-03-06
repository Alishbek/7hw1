package com.example.a7hw1.domain.usecase

import com.example.a7hw1.domain.model.Note
import com.example.a7hw1.domain.repository.NoteRepository
import javax.inject.Inject

class GetAllNotesUseCase @Inject constructor(
    private val noteRepository: NoteRepository
) {
    fun getAllNotes(note: Note) = noteRepository.getAllNotes()
}