package com.example.prototype.mymoviecataloguemade1.movie

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    var photo: Int,
    var title: String,
    var description: String,
    var release: String,
    var rating: String
) : Parcelable