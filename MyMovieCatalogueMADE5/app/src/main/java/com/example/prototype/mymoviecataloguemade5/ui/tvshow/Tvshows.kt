package com.example.prototype.mymoviecataloguemade5.ui.tvshow

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Tvshows(
    var id: String? = null,
    var photo: String? = null,
    var title: String? = null,
    var description: String? = null,
    var release: String? = null,
    var rating: Double = 0.0
) : Parcelable