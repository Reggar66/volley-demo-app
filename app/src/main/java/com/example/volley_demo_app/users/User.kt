package com.example.volley_demo_app.users

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
    val address: Address,
    val phone: String,
    val website: String,
    val company: Company
) {
    @Serializable
    data class Address(
        val street: String,
        val suite: String,
        val city: String,
        val zipcode: String,
        val geo: Geo
    )

    @Serializable
    data class Geo(
        val lat: Float,
        val lng: Float
    )

    @Serializable
    data class Company(
        val name: String,
        val catchPhrase: String,
        val bs: String // what ever bs is ¯\_(ツ)_/¯
    )

    fun toStringFormatted(): String {
        return "Id: ${id}\n" +
                "Name: ${name}\n" +
                "User name: ${username}\n" +
                "Email: ${email}\n" +
                "Address:\n" +
                "   Street: ${address.street}\n" +
                "   Suite: ${address.suite}\n" +
                "   City: ${address.city}\n" +
                "   Zip Code: ${address.zipcode}\n" +
                "   Geo: ${address.geo}\n" +
                "Phone: ${phone}\n" +
                "Website: ${website}\n" +
                "Company:\n" +
                "   Name: ${company.name}\n" +
                "   Catch phrase: ${company.catchPhrase}\n" +
                "   BS: ${company.bs}"
    }
}