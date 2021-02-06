package com.target.targetcasestudy.ui

import android.view.LayoutInflater
import androidx.recyclerview.widget.ListAdapter
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.target.targetcasestudy.data.DealItem
import com.target.targetcasestudy.databinding.DealListItemBinding

class DealItemAdapter(private val clickListener: DealItemListener) :
      ListAdapter<DealItem, DealItemViewHolder>(
        RecentDiffCallback()
){

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DealItemViewHolder {
    val inflater = LayoutInflater.from(parent.context)
    val binding = DealListItemBinding.inflate(inflater, parent, false)
    return DealItemViewHolder(binding)
  }

  override fun onBindViewHolder(viewHolder: DealItemViewHolder, position: Int) {
    val item = getItem(position)
    viewHolder.bind((item), clickListener)
  }
}

//Listener to handle item click actions
class DealItemListener(val clickListener: (deal: DealItem) -> Unit) {
  fun onClick(deal: DealItem) = clickListener(deal)
}

class DealItemViewHolder(val binding: DealListItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: DealItem, clickListener: DealItemListener){
      binding.deal = item
      binding.clickListener = clickListener
      binding.executePendingBindings()
    }
}

class RecentDiffCallback : DiffUtil.ItemCallback<DealItem>() {
  override fun areItemsTheSame(oldItem: DealItem, newItem: DealItem): Boolean {
    return oldItem.id == newItem.id
  }

  override fun areContentsTheSame(oldItem: DealItem, newItem: DealItem): Boolean {
    return oldItem == newItem
  }
}