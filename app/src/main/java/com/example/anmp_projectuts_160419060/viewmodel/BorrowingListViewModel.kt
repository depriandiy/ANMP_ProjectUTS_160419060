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

class BorrowingListViewModel(application: Application): AndroidViewModel(application) {
    val borrowLiveData = MutableLiveData<ArrayList<Borrowing>>()
    val borrowLoadErrorLiveData = MutableLiveData<Boolean>()
    val loadingLiveData = MutableLiveData<Boolean>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun refresh() {
        borrowLoadErrorLiveData.value = false
        loadingLiveData.value = true
        queue = Volley.newRequestQueue(getApplication())
        val url = "http://192.168.0.15/anmp/borrowing.php"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                val sType = object : TypeToken<ArrayList<Borrowing>>(){}.type
                val result = Gson().fromJson<ArrayList<Borrowing>>(response,sType)
                borrowLiveData.value = result
                loadingLiveData.value = false
                Log.d("showvolley",response)
            },
            {
                loadingLiveData.value = false
                borrowLoadErrorLiveData.value = true
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