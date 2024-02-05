package com.example.flashcards.Adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.flashcards.Data.Word
import com.example.flashcards.databinding.ItemDictionaryBinding
import java.util.*

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

                val mCal = Calendar.getInstance()
                mCal.timeInMillis = item.dateOfLastLearning
                count.text = "${mCal.get(Calendar.DATE)}.${mCal.get(Calendar.MONTH) + 1}.${
                    mCal.get(
                        Calendar.YEAR
                    )
                }\n${
                    mCal.get(Calendar.HOUR)
                }:${mCal.get(Calendar.MINUTE)}:${mCal.get(Calendar.MILLISECOND)}"
                learn.text = item.countOfLearning.toString()

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