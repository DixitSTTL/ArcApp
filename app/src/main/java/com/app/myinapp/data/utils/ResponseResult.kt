package com.app.myinapp.data.utils

sealed class ResponseResult<D>(data:D?,message:String?) {

    data class Success<D>(val data:D?) : ResponseResult<D>(data,null)
    data class Error<D>(val data:D?,val message:String) : ResponseResult<D>(data,message)
}