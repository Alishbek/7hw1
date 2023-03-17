package com.example.a7hw1.presentation.notes

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.a7hw1.R
import com.example.a7hw1.databinding.FragmentNotesBinding
import com.example.a7hw1.presentation.base.BaseFragment
import kotlinx.coroutines.launch

class NotesFragment : BaseFragment<NotesViewModel, FragmentNotesBinding>() {
    override val vm: NotesViewModel by viewModels()
    override val binding: FragmentNotesBinding = FragmentNotesBinding.inflate(layoutInflater)

    override fun setupRequests() {
        vm.noteState.collectState(onLoading = {

        }, onError = {

        }, onSuccess = {

        })
    }
}