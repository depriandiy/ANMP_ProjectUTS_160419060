package com.example.anmp_projectuts_160419060.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.anmp_projectuts_160419060.model.Student
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class StudentViewModel(application: Application): AndroidViewModel(application) {
    val studentsLiveData = MutableLiveData<Student>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null
    fun fetch() {

        queue = Volley.newRequestQueue(getApplication())
        val url = "http://192.168.0.15/anmp/student.php"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                val sType = object : TypeToken<Student>(){}.type
                val result = Gson().fromJson<Student>(response,sType)
                studentsLiveData.value = result
                Log.d("showvolley",response)
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