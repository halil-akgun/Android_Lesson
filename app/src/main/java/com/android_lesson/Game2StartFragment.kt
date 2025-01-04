package com.android_lesson

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.android_lesson.databinding.FragmentGame2StartBinding

class Game2StartFragment : Fragment() {

    private lateinit var binding: FragmentGame2StartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGame2StartBinding.inflate(inflater, container, false)

        binding.button19.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.toResultFragment)
        }

        return binding.root
    }
}