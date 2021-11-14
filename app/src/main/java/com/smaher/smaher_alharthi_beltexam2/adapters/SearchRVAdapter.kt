package com.smaher.smaher_alharthi_beltexam2.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.smaher.smaher_alharthi_beltexam2.R
import com.smaher.smaher_alharthi_beltexam2.database.UnivercityTable
import com.smaher.smaher_alharthi_beltexam2.fragments.ApiFragment
import com.smaher.smaher_alharthi_beltexam2.model.MyViewModel
import com.smaher.smaher_alharthi_beltexam2.model.Universities
import kotlinx.android.synthetic.main.search_row.view.*

class SearchRVAdapter(private val fragment: ApiFragment, private var list: Universities)
    : RecyclerView.Adapter<SearchRVAdapter.ItemViewHolder>() {


    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.search_row,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        var data = list[position]

        holder.itemView.apply {
            btUniName.text = data.name
        }

        holder.itemView.btUniName.setOnClickListener {
            Toast.makeText(
                fragment.requireContext(),
                "${data.name} is added",
                Toast.LENGTH_LONG
            ).show()
            fragment.myViewModel.addunivercity(UnivercityTable(0,data.name!!,data.country!!,""))
        }
    }
    override fun getItemCount() = list.size

//    fun update(list: ArrayList<UnivercityTable>){
//        this.list = list
//        notifyDataSetChanged()
//    }

}