package com.example.shopping_list.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PurchaseItem(
    @PrimaryKey(autoGenerate = true) var id: Long? = null,
    var name : String
)