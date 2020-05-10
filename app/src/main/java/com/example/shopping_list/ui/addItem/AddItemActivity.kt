package com.example.shopping_list.ui.addItem

import android.os.Bundle
import com.example.shopping_list.R
import com.example.shopping_list.databinding.ActivityAddItemBinding
import com.example.shopping_list.ui.base.BaseActivity
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class AddItemActivity: BaseActivity<ActivityAddItemBinding>(), HasAndroidInjector, AddItemContract.View {
    override val contentLayoutId: Int
        get() = R.layout.activity_add_item

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    @Inject
    lateinit var presenter: AddItemPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun setupBinding(binding: ActivityAddItemBinding) {
        binding.actionListener = this
    }

    override fun onAddItemClick() {
        binding.nameTextView.text.toString().run {
            presenter.saveItemProduct(this)
            //todo clear all from presenter and unbind View
            finish()
        }
    }
}