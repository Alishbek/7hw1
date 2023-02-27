package com.example.a7hw1.data.mappers

import com.example.a7hw1.data.model.NoteEntity
import com.example.a7hw1.domain.model.Note

fun Note.toNoteEntity() = NoteEntity(
    id, title, description, createAt
)
fun NoteEntity.toNote() = Note(
    id, title, description, createAt
)

