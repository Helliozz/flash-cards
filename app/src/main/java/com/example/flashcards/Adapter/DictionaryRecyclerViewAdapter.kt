package com.example.flashcards.Adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.flashcards.Data.Word
import com.example.flashcards.databinding.ItemDictionaryBinding

class DictionaryRecyclerViewAdapter(
    val deleteWord: (Word) -> Unit

) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var binding: ItemDictionaryBinding

    class ItemViewHolder(
        private val binding: ItemDictionaryBinding,
        private val recycler: DictionaryRecyclerViewAdapter
    ) : RecyclerView.ViewHolder(binding.root) {
        fun setWords(item: Word) {
            binding.apply {
                engWord.text = item.engWord
                rusWord.text = item.rusWord

                delete.setOnClickListener {
                    recycler.deleteWord(item)
                    binding.root.visibility = View.GONE
                    binding.root.isClickable = false
                }
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = ItemDictionaryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding, this)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ItemViewHolder).setWords(differ.currentList[position])
        holder.setIsRecyclable(false)
    }

    private val differCallback = object : DiffUtil.ItemCallback<Word>() {
        override fun areContentsTheSame(oldItem: Word, newItem: Word): Boolean {
            return oldItem.engWord == newItem.engWord && oldItem.rusWord == newItem.rusWord

        }

        @SuppressLint("DiffUtilEquals")
        override fun areItemsTheSame(oldItem: Word, newItem: Word): Boolean {
            return oldItem.id == newItem.id
        }
    }
    val differ = AsyncListDiffer(this, differCallback)
}