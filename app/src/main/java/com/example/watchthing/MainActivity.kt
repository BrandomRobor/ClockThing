package com.example.watchthing

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.wear.widget.WearableLinearLayoutManager
import com.example.watchthing.databinding.ActivityMainBinding
import com.example.watchthing.databinding.ItemListBinding

class MainActivity : Activity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val adapter = RecyclerAdapter()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recycler.apply {
            setHasFixedSize(true)
            this.adapter = adapter
            isEdgeItemsCenteringEnabled = false
            layoutManager = WearableLinearLayoutManager(this@MainActivity)
        }

        adapter.submitList(listOf("One", "Two", "Three", "Four", "Five", "Six", "Seven", "Ish"))
    }
}

class RecyclerAdapter: ListAdapter<String, RecyclerAdapter.RecyclerViewHolder>(util) {
    companion object {
        val util = object: DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String) = oldItem == newItem
            override fun areContentsTheSame(oldItem: String, newItem: String) = oldItem == newItem
        }
    }

    inner class RecyclerViewHolder(val binding: ItemListBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            binding.number.text = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        RecyclerViewHolder(ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}