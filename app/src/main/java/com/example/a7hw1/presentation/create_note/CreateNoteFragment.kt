package com.example.a7hw1.presentation.create_note

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.a7hw1.R
import com.example.a7hw1.databinding.FragmentCreateNoteBinding
import com.example.a7hw1.domain.model.Note
import com.example.a7hw1.presentation.base.BaseFragment
import com.example.a7hw1.presentation.extension.showToast
import com.example.a7hw1.presentation.notes.NotesFragment
import com.example.a7hw1.presentation.notes.NotesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateNoteFragment :
    BaseFragment<CreateNoteViewModel, FragmentCreateNoteBinding>(FragmentCreateNoteBinding::inflate) {
    override val vm: CreateNoteViewModel by viewModels()
    private var note: Note? = null

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun initialze() {
        if (arguments != null) {
            this.note = requireArguments().getSerializable(NotesFragment.ARG_ADD_EDIT, Note::class.java)
            setData()
        }
    }

    private fun setData() {
        with(binding){
            titleEt.setText(note!!.title)
            descEt.setText(note!!.description)
        }
    }


    override fun listeners() {
        with(binding) {
            saveNoteBtn.setOnClickListener {
                if (note != null) {
                    vm.editNote(
                        note!!.copy(
                            title = titleEt.text.toString(),
                            description = descEt.text.toString()
                        )
                    )
                } else {
                    vm.createNote(
                        titleEt.text.toString(),
                        descEt.text.toString()
                    )
                    findNavController().navigate(R.id.action_createNoteFragment_to_notesFragment)

                }
            }
        }
    }

    override fun setupRequests() {
        vm.createNoteState.collectState(
            onLoading = {},
            onError = {
                showToast(it)
            },
            onSuccess = {
                showToast("Note has been successfully created!")
                findNavController().navigateUp()
            }
        )
        vm.editNoteState.collectState(
            onLoading = {},
            onError = {
                showToast(it)
            },
            onSuccess = {
                showToast("Note has been successfully edited!")
                findNavController().navigateUp()
            }
        )
    }
}