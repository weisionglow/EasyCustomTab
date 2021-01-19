package com.lwseasy.easycustomtab_sample

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.lwseasy.easycustomtab_sample.databinding.FragmentCustomBinding

class CustomFragment : Fragment() {

    private var binding: FragmentCustomBinding? = null

    companion object {
        private const val EXTRA_DATA = "EXTRA_TITLE"

        fun newInstance(title: String): CustomFragment {
            return CustomFragment().apply {
                arguments = Bundle().apply {
                    putString(EXTRA_DATA, title)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        binding = DataBindingUtil.inflate<FragmentCustomBinding>(
            inflater,
            R.layout.fragment_custom,
            container,
            false
        )

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.title = arguments?.getString(EXTRA_DATA) ?: ""
    }
}