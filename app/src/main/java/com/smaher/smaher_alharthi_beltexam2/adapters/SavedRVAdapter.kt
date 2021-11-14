package com.smaher.smaher_alharthi_beltexam2.adapters

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.smaher.smaher_alharthi_beltexam2.R
import com.smaher.smaher_alharthi_beltexam2.database.UnivercityTable
import com.smaher.smaher_alharthi_beltexam2.fragments.DatabaseFragment
import com.smaher.smaher_alharthi_beltexam2.model.MyViewModel
import com.smaher.smaher_alharthi_beltexam2.model.Universities
import kotlinx.android.synthetic.main.edit_dialog.*
import kotlinx.android.synthetic.main.note_row.view.*

class SavedRVAdapter (private val fragment: DatabaseFragment, private var list: ArrayList<UnivercityTable>):  RecyclerView.Adapter<SavedRVAdapter.ItemViewHolder>(){
    private val myViewModel by lazy { ViewModelProvider(fragment).get(MyViewModel::class.java) }

    class ItemViewHolder (itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.note_row,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        var data = list[position]


        holder.itemView.apply {
            tvName.text = data.name
            tvCountry.text = data.country

        }
        holder.itemView.btUpdate.setOnClickListener {
            updateDialog(position)
        }

        holder.itemView.btDelete.setOnClickListener {
            fragment.myViewModel.deleteunivercity(UnivercityTable(data.id,data.name,data.country,data.note))
        }
        holder.itemView.setOnClickListener {
            Toast.makeText(
                fragment.requireContext(),
                "${data.note}",
                Toast.LENGTH_LONG
            ).show()
        }



    }

    fun updateDialog(position: Int){
        var myInfoDialog = Dialog(fragment.requireContext())
        myInfoDialog.setContentView(R.layout.edit_dialog)
        myInfoDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        myInfoDialog.show()

        myInfoDialog.etNoteUpdate.setText(list[position].note)


        myInfoDialog.btNoteUpdate.setOnClickListener {
            if (myInfoDialog.etNoteUpdate.text.isNotBlank()) {
                list[position].note = myInfoDialog.etNoteUpdate.text.toString()
                fragment.myViewModel.updatenivercity(list[position])
                Toast.makeText(
                    fragment.requireContext(),
                    "Update success!!",
                    Toast.LENGTH_SHORT
                ).show()
                myInfoDialog.dismiss()
            }
            else {
                Toast.makeText(
                    fragment.requireContext(),
                    "Do not leave it empty",
                    Toast.LENGTH_SHORT
                ).show()
                myInfoDialog.dismiss()
            }
        }
        myInfoDialog.btCansle.setOnClickListener{
            myInfoDialog.dismiss()
        }


    }

    override fun getItemCount() = list.size

    fun update(list: ArrayList<UnivercityTable>){
        this.list = list
        notifyDataSetChanged()
    }
}