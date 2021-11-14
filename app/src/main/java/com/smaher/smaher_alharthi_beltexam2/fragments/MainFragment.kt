package com.smaher.smaher_alharthi_beltexam2.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import com.smaher.smaher_alharthi_beltexam2.R

class MainFragment : Fragment() {

    lateinit var btBrowseApi: Button
    lateinit var btLocalDB: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)


        btBrowseApi = view.findViewById(R.id.btBrowseApi)
        btLocalDB = view.findViewById(R.id.btLocalDB)

        btBrowseApi.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_apiFragment)
        }
        btLocalDB.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_databaseFragment)
        }

        return view
    }

}