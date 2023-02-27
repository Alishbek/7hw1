package com.example.a7hw1.data.repository

import com.example.a7hw1.data.local.NoteDao
import com.example.a7hw1.data.mappers.toNote
import com.example.a7hw1.data.mappers.toNoteEntity
import com.example.a7hw1.domain.model.Note
import com.example.a7hw1.domain.repository.NoteRepository

class NoteRepositoryImpl(private val noteDao: NoteDao) : NoteRepository {
    override fun createNote(note: Note) {
        noteDao.createNote(note.toNoteEntity())
    }

    override fun getAllNotes(): List<Note> {
        return noteDao.getAllNotes().map { it.toNote() }
    }

    override fun editNote(note: Note) {
        noteDao.editNote(note.toNoteEntity())
    }

    override fun deleteNote(note: Note) {
        noteDao.deleteNote(note.toNoteEntity())
    }
}