package com.example.a7hw1.presentation.notes

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.a7hw1.R
import com.example.a7hw1.databinding.FragmentCreateNoteBinding
import com.example.a7hw1.databinding.FragmentNotesBinding
import com.example.a7hw1.presentation.base.BaseFragment

class CreateNoteFragment : BaseFragment<NotesViewModel, FragmentCreateNoteBinding>() {
    override val vm: NotesViewModel by viewModels()
    override val binding: FragmentCreateNoteBinding = FragmentCreateNoteBinding.inflate(layoutInflater)

    override fun setupRequests() {
        vm.noteState.collectState(onLoading = {

        }, onError = {

        }, onSuccess = {

        })
    }
}