package com.android_lesson

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android_lesson.databinding.FragmentZ3Binding

class Z3Fragment : Fragment() {

    // DataBinding
    private lateinit var dataBinding: FragmentZ3Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataBinding = FragmentZ3Binding.inflate(inflater, container, false)
        return dataBinding.root
    }

}