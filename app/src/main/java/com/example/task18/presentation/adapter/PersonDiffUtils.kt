package com.example.task18.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.task18.domain.PersonsList

class PersonDiffUtils : DiffUtil.ItemCallback<PersonsList.Person>() {
    override fun areItemsTheSame(
        oldItem: PersonsList.Person,
        newItem: PersonsList.Person
    ) = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: PersonsList.Person,
        newItem: PersonsList.Person
    ) = oldItem == newItem

}