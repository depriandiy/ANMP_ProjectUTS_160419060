package com.example.anmp_projectuts_160419060.model

import com.google.gson.annotations.SerializedName

data class Book(
    val idbook: String?,
    var title: String?,
    var author: String?,
    var release_year: Int?,
    var page: Int?,
    var description: String?,
    var available_stock: Int?,
    var photoURL: String?
)

data class Student(
    val nrp: Int?,
    var name: String?,
    var password: String?,
    var phone: Int?,
    var photoURL: String?
)

data class Borrowing(
    val idborrow: Int,
    val idbook: String?,
    val nrp: Int?,
    var title: String?,
    var author: String?,
    var page: Int?,
    var borrowing_month: Int?,
    var borrowing_year: Int?,
    var photoURL: String?,
    var status: String?
)

data class Bookmark(
    val nrp: Int?,
    val idbook: String?,
    var title: String?,
    var author: String?,
    var release_year: Int?,
    var page: Int?,
    var description: String?,
    var photoURL: String?
)

data class Review(
    val idreview: String?,
    val nrp: Int?,
    val idbook: String?,
    var rating: Int?,
    var description: String?
)