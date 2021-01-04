package io.viktoriia.architecture.base.result

/**
 * Result, holding [Success] [Error] and [warnings] for the warning messages occurred during execution.
 */
sealed class AppResult<T : Any> {
    val warnings: MutableList<String> = mutableListOf()

    open class Success<T : Any>(val data: T) : AppResult<T>()

    open class Error<T : Any>(
        val throwable: Throwable? = null,
        val message: String? = throwable?.localizedMessage
    ) : AppResult<T>()
}