package com.example.shopping_list.ui.base

import io.reactivex.disposables.CompositeDisposable

class BaseContract {

    interface Presenter<in T> {
//        protected val disposables: CompositeDisposable = CompositeDisposable()

        fun subscribe()
        fun unsubscribe()
        fun attach(view: T)
        //todo remove view after detach
    }

    interface View {

    }

//    inner class DefaultObserver<E : Any?, T : ResultObject<E>> : Observer<T> {
//        private var onError: (errorResult: ResultObject.ErrorResult) -> Unit = {
//            handleProcessing(false)
//        }
//        private var onSuccess: (successResult: ResultObject.SuccessResult<E>) -> Unit = {
//            handleProcessing(false)
//        }
//        private var onProcessing: (processingResult: ResultObject.Processing) -> Unit = {
//            handleProcessing(true)
//        }
//
//        private var shouldHandleProcessing: Boolean = true
//
//        @Suppress("UNCHECKED_CAST")
//        override fun onChanged(t: T) {
//            when (t) {
//                is ResultObject.SuccessResult<*> -> onSuccess(t as ResultObject.SuccessResult<E>)
//                is ResultObject.ErrorResult -> onError(t)
//            }
//        }
//
//        fun handleError(
//            withDefault: Boolean = true,
//            handler: (errorResult: ResultObject.ErrorResult) -> Unit
//        ): DefaultObserver<E, T> {
//            onError = if (withDefault) {
//                val default = onError
//                {
//                    default(it)
//                    handler(it)
//                }
//            } else {
//                handler
//            }
//            return this
//        }
//
//        fun handleSuccess(
//            withDefault: Boolean = true,
//            handler: (successResult: ResultObject.SuccessResult<E>) -> Unit
//        ): DefaultObserver<E, T> {
//            onSuccess = if (withDefault) {
//                val default = onSuccess
//                {
//                    default(it)
//                    handler(it)
//                }
//            } else {
//                handler
//            }
//            return this
//        }
//
//        fun handleProcessing(
//            withDefault: Boolean = true,
//            handler: (processingResult: ResultObject.Processing) -> Unit
//        ): DefaultObserver<E, T> {
//            onProcessing = if (withDefault) {
//                val default = onProcessing
//                {
//                    default(it)
//                    handler(it)
//                }
//            } else {
//                handler
//            }
//            return this
//        }
//
//        fun dontHandleProcessing(): DefaultObserver<E, T> {
//            shouldHandleProcessing = false
//            return this
//        }
//    }
}