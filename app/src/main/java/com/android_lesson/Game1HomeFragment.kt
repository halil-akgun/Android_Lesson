package com.android_lesson

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.android_lesson.databinding.FragmentGame1HomeBinding

class Game1HomeFragment : Fragment() {

    private lateinit var binding: FragmentGame1HomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGame1HomeBinding.inflate(inflater, container, false)

        binding.button18.setOnClickListener {

            // data sending from one fragment to another
//            val action = Game1HomeFragmentDirections.toGameFragment("Veerle", 29, true)
            val action = Game1HomeFragmentDirections
                .toGameFragment(Person("Veerle", 29), "Veerle", 29, true)

            // Navigating to the GameFragment with the action defined above
            Navigation.findNavController(it).navigate(action)
//            Navigation.findNavController(it).navigate(R.id.toGameFragment)
        }

        return binding.root
    }
}