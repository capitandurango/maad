package com.capps.maad.domain.responses

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * Class that retreives the response for the current searching.
 */
data class SearchDataResponse(
    @SerializedName("RelatedTopics")
    val topics: List<RelatedTopic>
)

/**
 * Class that manage the data for the character.
 */
data class RelatedTopic(
    @SerializedName("Text")
    val text: String,
    @SerializedName("Icon")
    val icon: Icon
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readParcelable(Icon::class.java.classLoader)
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(text)
        parcel.writeParcelable(icon, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RelatedTopic> {
        override fun createFromParcel(parcel: Parcel): RelatedTopic {
            return RelatedTopic(parcel)
        }

        override fun newArray(size: Int): Array<RelatedTopic?> {
            return arrayOfNulls(size)
        }
    }
}

/**
 * Class that contains the metadata for the character.
 */
data class Icon(
    @SerializedName("URL")
    val url: String = ""
) : Parcelable {
    constructor(parcel: Parcel) : this(parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Icon> {
        override fun createFromParcel(parcel: Parcel): Icon {
            return Icon(parcel)
        }

        override fun newArray(size: Int): Array<Icon?> {
            return arrayOfNulls(size)
        }
    }
}