package com.example.a7hw1.presentation.notes

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.a7hw1.R
import com.example.a7hw1.databinding.FragmentCreateNoteBinding
import com.example.a7hw1.databinding.FragmentNotesBinding
import com.example.a7hw1.domain.model.Note
import com.example.a7hw1.presentation.base.BaseFragment
import com.example.a7hw1.presentation.extension.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NotesFragment : BaseFragment<NotesViewModel, FragmentNotesBinding>(FragmentNotesBinding::inflate) {
    override val vm: NotesViewModel by viewModels()
    private val list = mutableListOf<Note>()
    private val adapter by lazy { NotesAdapter(this::onItemClick, this::onLongItemClick) }

    private fun onItemClick(note: Note) {
        val bundle = bundleOf().apply {
            putSerializable(ARG_ADD_EDIT, note)
        }
        findNavController().navigate(R.id.action_notesFragment_to_createNoteFragment, bundle)
    }

    private fun onLongItemClick(note: Note) {
        vm.deleteNote(note)
    }


    override fun initialze() {
        binding.recyclerView.adapter = adapter
    }

    override fun listeners() {
        binding.createFab.setOnClickListener {
            findNavController().navigate(R.id.action_notesFragment_to_createNoteFragment)
        }
    }

    override fun setupRequests() {
        vm.noteState.collectState(onLoading = {
            binding.notesProgress.isVisible = true
        }, onError = {
            binding.notesProgress.isVisible = false
            showToast(it)
        }, onSuccess = {
            binding.notesProgress.isVisible = false
            adapter.update(it as MutableList<Note>)
        })

        vm.deleteNoteState.collectState(
            onLoading = {
                binding.notesProgress.isVisible = true
            },
            onError = {
                binding.notesProgress.isVisible = false
                showToast(it)
            },
            onSuccess = {
                binding.notesProgress.isVisible = false
                showToast("Note has been successfully deleted!")
                adapter.deleted()
            }
        )
        vm.createNoteState.collectState(onLoading = {
            binding.notesProgress.isVisible = true
        }, onError = {
            binding.notesProgress.isVisible = false
        }, onSuccess = {
            binding.notesProgress.isVisible = false

        })

    }


    companion object {
        const val ARG_ADD_EDIT = "edit_note"
    }
}