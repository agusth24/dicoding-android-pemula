package com.example.prototype.mymoviecataloguemade2.ui.movies

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movies(
    var photo: String,
    var title: String,
    var description: String,
    var release: String,
    var rating: Int
) : Parcelable