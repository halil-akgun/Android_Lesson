package com.android_lesson

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment

class FragmentTest : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Note: In a Fragment, `this` or `this@FragmentTest` cannot be used directly as a context
        // because a Fragment is not a Context itself. Instead, you should use `requireContext()`,
        // `requireActivity()`, or `activity` to get a valid Context for operations like showing a Toast.
//        Toast.makeText(requireContext(), "Fragment", Toast.LENGTH_LONG).show()
//        Toast.makeText(requireActivity(), "Fragment", Toast.LENGTH_LONG).show()
        Toast.makeText(activity, "Fragment", Toast.LENGTH_LONG).show()

        return super.onCreateView(inflater, container, savedInstanceState)
    }
}