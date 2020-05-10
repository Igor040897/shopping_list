package com.example.shopping_list.ui.history

import android.content.Context
import com.example.shopping_list.R
import com.example.shopping_list.databinding.FragmentHistoryShoppingBinding
import com.example.shopping_list.ui.base.BaseFragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class HistoryShoppingFragment  : BaseFragment<FragmentHistoryShoppingBinding>(),
    HasAndroidInjector {

    companion object {
        fun newInstance() = HistoryShoppingFragment()
    }

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = fragmentInjector

    override val contentLayoutId = R.layout.fragment_history_shopping
    override var title = R.string.label_history_shopping

    @Inject
    lateinit var presenter: HistoryPresenter

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }
}