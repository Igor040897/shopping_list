package com.example.shopping_list.ui.itemsList

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shopping_list.R
import com.example.shopping_list.databinding.FragmentItemsListBinding
import com.example.shopping_list.ui.addItem.AddItemActivity
import com.example.shopping_list.ui.base.BaseFragment
import com.example.shopping_list.ui.main.purchase.PurchaseAdapter
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

const val REQUEST_CODE_ADD_ITEM = 99

class ItemsListFragment : BaseFragment<FragmentItemsListBinding>(), ItemsListContract.View, HasAndroidInjector {

    companion object {
        fun newInstance() = ItemsListFragment()
    }

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = fragmentInjector

    override val contentLayoutId = R.layout.fragment_items_list
    override var title = R.string.label_shopping_list

    @Inject
    lateinit var presenter: ItemsListPresenter

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)

        presenter.attach(this)
    }

    override fun setupBinding(binding: FragmentItemsListBinding) {
        super.setupBinding(binding)

        binding.fab.setOnClickListener { view ->
            val intent = Intent(context, AddItemActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_ADD_ITEM)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.subscribe()
    }

    override fun onStop() {
        super.onStop()
        presenter.unsubscribe()
    }

    override fun postPurchaseAdapter(adapter: PurchaseAdapter) {
        binding.itemsRecyclerView.adapter = adapter
    }
}