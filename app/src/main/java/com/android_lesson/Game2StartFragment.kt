package com.android_lesson

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.android_lesson.databinding.FragmentGame2StartBinding

class Game2StartFragment : Fragment() {

    private lateinit var binding: FragmentGame2StartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGame2StartBinding.inflate(inflater, container, false)

        val bundle: Game2StartFragmentArgs by navArgs()

        val receivedPerson = bundle.Person
        val receivedName = bundle.name
        val receivedAge = bundle.age
        val receivedIsAdult = bundle.isAdult

        Log.e("Game2StartFragment", "Person: ${receivedPerson.name} ${receivedPerson.age}")
        Log.e("Game2StartFragment", "Name: $receivedName")
        Log.e("Game2StartFragment", "Age: $receivedAge")
        Log.e("Game2StartFragment", "Is Adult: $receivedIsAdult")

        binding.button19.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.toResultFragment)
        }

        return binding.root
    }
}