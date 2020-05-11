package com.example.shopping_list.ui.base

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*

abstract class BaseFragment<B : ViewDataBinding> : Fragment() {

    protected lateinit var binding: B

    abstract val contentLayoutId: Int
        @LayoutRes get

    /** Override this value to change toolbar title */
    protected open val title: Int? = null
        @StringRes get

    protected open fun setupBinding(binding: B) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, contentLayoutId, container, false)
        //todo remove
//        binding.lifecycleOwner = this
        setupBinding(binding)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupToolbar()
    }

    private fun setupToolbar() {
        title?.run {
            activity?.toolbar?.title = getString(this)
        }
    }

    fun hideKeyboard() {
        context?.run {
            val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            var view = activity?.currentFocus
            view = view ?: View(this)

            imm.hideSoftInputFromWindow(
                activity?.findViewById<View>(android.R.id.content)?.windowToken,
                0
            )
        }
    }
}