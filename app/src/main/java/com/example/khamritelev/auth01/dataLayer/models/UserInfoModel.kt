package com.example.khamritelev.auth01.dataLayer.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UserInfoModel() {
    @SerializedName("id")
    @Expose
    private var id: String? = null
    @SerializedName("email")
    @Expose
    var email: String? = null
    @SerializedName("verified_email")
    @Expose
    private var verifiedEmail: Boolean? = null
    @SerializedName("name")
    @Expose
    private var name: String? = null
    @SerializedName("given_name")
    @Expose
    var givenName: String? = null
    @SerializedName("family_name")
    @Expose
    var familyName: String? = null
    @SerializedName("picture")
    @Expose
    private var picture: String? = null
    @SerializedName("locale")
    @Expose
    private var locale: String? = null

//    class UserInfoModel(val id: String, val email: String, val verifiedEmail: Boolean, val name: String,
//                        val givenName: String, val familyName: String, val picture: String, val locale: String) {
//
//    }
}