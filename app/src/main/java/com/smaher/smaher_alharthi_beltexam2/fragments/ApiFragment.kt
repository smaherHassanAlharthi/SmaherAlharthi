package com.smaher.smaher_alharthi_beltexam2.fragments

import android.app.ProgressDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.smaher.smaher_alharthi_beltexam2.R
import com.smaher.smaher_alharthi_beltexam2.adapters.SearchRVAdapter
import com.smaher.smaher_alharthi_beltexam2.api.APIClient
import com.smaher.smaher_alharthi_beltexam2.api.APIInterface
import com.smaher.smaher_alharthi_beltexam2.model.MyViewModel
import com.smaher.smaher_alharthi_beltexam2.model.Universities
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiFragment : Fragment() {

    lateinit var rvSearch: RecyclerView
    lateinit var etUnivercityName: EditText
    lateinit var btAPIBack: Button
    lateinit var btSearch: Button
    lateinit var searchAdapter: SearchRVAdapter
    val myViewModel by lazy { ViewModelProvider(this).get(MyViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_api, container, false)


        btAPIBack=view.findViewById(R.id.btAPIBack)
        btSearch=view.findViewById(R.id.btSearch)
        etUnivercityName = view.findViewById(R.id.etUnivercityName)
        rvSearch =view.findViewById(R.id.rvSearch)

        btAPIBack.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_apiFragment_to_mainFragment)
        }

        btSearch.setOnClickListener {
           var  search = etUnivercityName.text.toString()
            if (search.isNotEmpty()) {
                getApi(search)
            } else {
                Toast.makeText(this.requireContext(), "Enter a name", Toast.LENGTH_SHORT).show()
            }
            etUnivercityName.text.clear()
            hideKeyboard()
        }
        return view
    }

    private fun getApi(keyword: String) {
        //show progress Dialog
        val progressDialog = ProgressDialog(this.context)
        progressDialog.setMessage("Please wait")
        progressDialog.show()

        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)

        val call: Call<Universities?>? = apiInterface!!.getUnivercityInfo("/search?name=$keyword")

        call?.enqueue(object : Callback<Universities?> {
            override fun onResponse(
                call: Call<Universities?>?,
                response: Response<Universities?>
            ) {
                progressDialog.dismiss()

                if(response.body()!=null)
                    setRV(response.body()!!)
            }
            override fun onFailure(call: Call<Universities?>, t: Throwable?) {
                Toast.makeText(requireContext(),"Unable to load data!", Toast.LENGTH_SHORT).show()
                progressDialog.dismiss()
                call.cancel()
            }
        })
    }

    fun setRV(uni: Universities) {
        searchAdapter =SearchRVAdapter(this ,uni)
        this.rvSearch.adapter = searchAdapter
        this.rvSearch.layoutManager = LinearLayoutManager(this.context)

    }


    fun hideKeyboard() {
        // Hide Keyboard
        val hideKeyboard = ContextCompat.getSystemService(requireContext(), InputMethodManager::class.java)
        hideKeyboard?.hideSoftInputFromWindow(requireActivity().currentFocus?.windowToken, 0)
    }
}