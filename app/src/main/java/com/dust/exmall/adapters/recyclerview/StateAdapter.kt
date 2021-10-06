package com.dust.exmall.adapters.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.dust.exmall.R
import com.dust.exmall.customviews.CTextView
import com.dust.exmall.interfaces.local.OnStateSelected

class StateAdapter(var stateList:List<String> , var onStateSelected: OnStateSelected , var fragmentManager: FragmentManager) :RecyclerView.Adapter<StateAdapter.MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_state , parent , false))
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.stateText.text = stateList[position]
        holder.itemView.setOnClickListener {
            onStateSelected.onSelect(stateList[position])
            fragmentManager.popBackStack( "SearchStateFragment", FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
    }

    override fun getItemCount(): Int = stateList.size

    inner class MainViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        var stateText = itemView.findViewById<CTextView>(R.id.stateText)
    }

}