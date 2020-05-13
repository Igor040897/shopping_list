package com.example.shopping_list.ui.itemsList

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ActionMode
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shopping_list.R
import com.example.shopping_list.databinding.FragmentItemsListBinding
import com.example.shopping_list.ui.addItem.AddItemActivity
import com.example.shopping_list.ui.base.BaseFragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

const val REQUEST_CODE_ADD_ITEM = 99
const val REQUEST_CODE_ALERT_DIALOG_FRAGMENT = 100
const val ALERT_DIALOG_FRAGMENT_TAG = "ALERT_DIALOG_FRAGMENT_TAG"

class ItemsListFragment : BaseFragment<FragmentItemsListBinding>(), ItemsListContract.View,
    HasAndroidInjector {

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
    private var actionMode: ActionMode? = null
    private val actionModeCallback: ActionMode.Callback =
        object : ActionMode.Callback {
            override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
                item?.run {
                    when (item.itemId) {
                        R.id.item_select_all -> {
                            val countSelected = presenter.selectAll()
                            actionMode?.title = "$countSelected выбрано"
                        }
                        R.id.item_shop -> {
                            showDialog()
                            //todo create dialog
                        }
                        else -> {
                        }
                    }
                }
                return false
            }

            override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                mode?.menuInflater?.inflate(R.menu.menu_action_mode, menu)
                binding.fab.hide()
                return true
            }

            override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                return false
            }

            override fun onDestroyActionMode(mode: ActionMode?) {
                actionMode = null
                mode?.finish()
                presenter.clearItemSelections()
                binding.fab.show()
            }
        }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)

        presenter.attach(this)
    }

    override fun setupBinding(binding: FragmentItemsListBinding) {
        super.setupBinding(binding)

        binding.fab.setOnClickListener {
            val intent = Intent(context, AddItemActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_ADD_ITEM)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.subscribe()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.subscribe()
    }

    override fun postPurchaseAdapter(adapterSelectMode: SelectModeProductsAdapter) {
        binding.itemsRecyclerView.adapter = adapterSelectMode
    }

    override fun onLongClicked() {
        if (actionMode == null) {
            actionMode = (activity as? AppCompatActivity)
                ?.startSupportActionMode(actionModeCallback)
        }
    }

    override fun onClicked(size: Int) {
        actionMode?.title = if (size == 0) "" else "$size выбрано"
    }

    fun showDialog() {
        fragmentManager?.apply {
            val newFragment: DialogFragment = AlertDialogFragment.newInstance(R.string.shop_products_message)
            newFragment.setTargetFragment(
                this@ItemsListFragment,
                REQUEST_CODE_ALERT_DIALOG_FRAGMENT
            )
            newFragment.show(this, ALERT_DIALOG_FRAGMENT_TAG)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_ALERT_DIALOG_FRAGMENT) {
            if (resultCode == Activity.RESULT_OK) {
                presenter.shopSelectItems()
                actionMode?.finish()
            }
        }
    }
}