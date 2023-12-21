package com.example.task18.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.task18.databinding.ItemRecyclerviewBinding
import com.example.task18.domain.PersonsList

class PersonsRecyclerViewAdapter :
    PagingDataAdapter<PersonsList.Person, PersonsRecyclerViewAdapter.PersonViewHolder>(
        PersonDiffUtils()
    ) {

    inner class PersonViewHolder(private val binding: ItemRecyclerviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(person: PersonsList.Person) = with(binding) {

            Glide.with(itemView.context)
                .load(person.avatar)
                .into(ivImage)

            tvName.text = person.firstname

            tvLastName.text = person.lastname

            tvEmail.text = person.email
        }
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PersonViewHolder(
        ItemRecyclerviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )
}
