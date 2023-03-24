package com.example.a7hw1.presentation.notes

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.OnReceiveContentListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a7hw1.databinding.ItemNotesBinding
import com.example.a7hw1.domain.model.Note

class NotesAdapter(
    private val onItemClickListener: (Note) -> Unit,
    private val onItemLongClickListener: (Note) -> Unit
) :
    RecyclerView.Adapter<NotesAdapter.ViewHolder>() {
    private var list = mutableListOf<Note>()
    private var deletedNote: Note? = null



    inner class ViewHolder(private val binding: ItemNotesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(note: Note) {
            with(binding) {
                itemNotesTitle.text = note.title
                itemNotesDesc.text = note.description

                root.setOnClickListener {
                    onItemClickListener(note)
                }
                root.setOnLongClickListener {
                    deletedNote = note
                    onItemLongClickListener(note)
                    true
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            (ItemNotesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ))
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun deleted() {
        if (deletedNote!=null){
            list.remove(deletedNote)
            deletedNote = null
            notifyDataSetChanged()
        }
    }

    fun update(notes: MutableList<Note>) {
        list = notes
        notifyDataSetChanged()
    }

}