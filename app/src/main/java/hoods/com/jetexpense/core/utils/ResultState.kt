package hoods.com.jetexpense.core.utils

sealed class ResultState<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T? = null) : ResultState<T>(data)
    class Failure<T>(message: String?, data: T? = null) : ResultState<T>(data, message)
}
