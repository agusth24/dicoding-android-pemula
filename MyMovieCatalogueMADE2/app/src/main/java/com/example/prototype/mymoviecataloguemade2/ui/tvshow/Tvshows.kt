package com.example.prototype.mymoviecataloguemade2.ui.tvshow

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Tvshows(
    var photo: String,
    var title: String,
    var description: String,
    var release: String,
    var rating: Int
) : Parcelable