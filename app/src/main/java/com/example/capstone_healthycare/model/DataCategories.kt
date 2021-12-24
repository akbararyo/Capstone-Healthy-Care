package com.example.capstone_healthycare.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class DataCategories(
    var strCategory: String? = null,
    var strCategoryThumb: String? = null,
    var strCategoryDescription: String? = null
): Parcelable
