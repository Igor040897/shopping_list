package com.example.shopping_list.ui.addItem.addPhoto

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseBottomSheetDialogFragment<B : ViewDataBinding> : BottomSheetDialogFragment () {
    protected lateinit var binding: B

    abstract val contentLayoutId: Int
        @LayoutRes get

    protected open fun setupBinding(binding: B) {}

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, contentLayoutId, container, false)
        setupBinding(binding)
        return binding.root
    }
}