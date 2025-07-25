package com.example.userdirectory

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id: Int,
    val name: String,
    val email: String,
    val phone: String,
    val address: Address? = null,
    val website: String? = null,
    val company: Company? = null
) : Parcelable

@Parcelize
data class Address(
    val street: String,
    val suite: String,
    val city: String,
    val zipcode: String,
    val geo: Geo? = null
) : Parcelable {
    override fun toString(): String {
        return "$street, $suite, $city $zipcode"
    }
}

@Parcelize
data class Geo(
    val lat: String,
    val lng: String
) : Parcelable

@Parcelize
data class Company(
    val name: String,
    val catchPhrase: String? = null,
    val bs: String? = null
) : Parcelable
