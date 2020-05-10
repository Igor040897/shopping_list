package com.example.shopping_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.shopping_list.data.ResultObject

fun <S> MediatorLiveData<ResultObject<S>>.postSuccessResult(source: LiveData<S>) {
    addSource(source) {
        postValue(ResultObject.SuccessResult(it))
    }
}