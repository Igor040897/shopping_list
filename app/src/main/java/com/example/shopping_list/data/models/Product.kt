package com.example.shopping_list.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Product(
    var name : String,
    var imageUri : String? = null,
    var isPurchase: Boolean = false
){
    //todo made date purchase
    @PrimaryKey(autoGenerate = true) var id: Long? = null
}