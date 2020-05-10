package com.example.shopping_list.data

import kotlinx.coroutines.Job

sealed class ResultObject<out T : Any?>(protected val resultData: T?) {

    class SuccessResult<T : Any?>(result: T?) : ResultObject<T>(result) {
        fun getResult() = resultData
    }

    class Processing(private val coroutineScope: Job?) : ResultObject<Nothing>(null) {
        fun cancelOperation() {
            coroutineScope?.cancel()
        }
    }

    class ErrorResult(val error: Throwable) : ResultObject<Nothing>(null) {
        fun map(): ErrorResult {
            return ErrorResult(error)
        }
    }
}

suspend fun <T : Any?> ResultObject<T>.applyToSuccess(
    function: suspend (T) -> Unit
): ResultObject<T> = this.apply {
    when (this) {
        is ResultObject.SuccessResult -> {
            getResult()?.let {
                function(it)
            }
        }
    }
}
