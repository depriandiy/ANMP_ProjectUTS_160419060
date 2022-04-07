package com.example.anmp_projectuts_160419060.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.anmp_projectuts_160419060.model.Borrowing
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class BorrowingDetailViewModel(application: Application): AndroidViewModel(application) {
    val borrowLiveData = MutableLiveData<Borrowing>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun fetch(bookId: String?) {
        queue = Volley.newRequestQueue(getApplication())
        val url = "http://192.168.0.15/anmp/borrow_detail.php?idbook=$bookId"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<Borrowing>(){}.type
                val result = Gson().fromJson<Borrowing>(it,sType)
                borrowLiveData.value = result
                Log.d("showvolley",it)
            },
            {
                Log.d("errorvolley",it.toString())
            }
        ).apply {
            tag = "TAG"
        }
        queue?.add(stringRequest)
    }
    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}