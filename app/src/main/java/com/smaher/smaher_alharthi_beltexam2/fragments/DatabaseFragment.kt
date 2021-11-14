package com.smaher.smaher_alharthi_beltexam2.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.smaher.smaher_alharthi_beltexam2.R
import com.smaher.smaher_alharthi_beltexam2.adapters.SavedRVAdapter
import com.smaher.smaher_alharthi_beltexam2.adapters.SearchRVAdapter
import com.smaher.smaher_alharthi_beltexam2.database.UnivercityTable
import com.smaher.smaher_alharthi_beltexam2.model.MyViewModel
import com.smaher.smaher_alharthi_beltexam2.model.Universities

class DatabaseFragment : Fragment() {

    lateinit var btDatabaseBack: Button
    lateinit var rvDatabase: RecyclerView
    lateinit var DatabaseAdapter: SavedRVAdapter
    val myViewModel by lazy { ViewModelProvider(this).get(MyViewModel::class.java) }
    var list = ArrayList<UnivercityTable>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_database, container, false)

        rvDatabase =view.findViewById(R.id.rvDb)
        btDatabaseBack=view.findViewById(R.id.btDatabaseBack)

        btDatabaseBack.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_databaseFragment_to_mainFragment)
        }

        myViewModel.getunivercity().observe(viewLifecycleOwner, {
                List ->
            DatabaseAdapter.update(List as ArrayList<UnivercityTable>)
        })

        DatabaseAdapter =SavedRVAdapter(this ,list)
        this.rvDatabase.adapter = DatabaseAdapter
        this.rvDatabase.layoutManager = LinearLayoutManager(this.context)

        return view
    }

}