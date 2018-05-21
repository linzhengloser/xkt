package com.jcfy.xkt.module

import android.os.Parcel
import android.os.Parcelable

/**
 * @author linzheng
 */
data class ExerciseExamination(val icon: Int, val title: String) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(icon)
        parcel.writeString(title)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ExerciseExamination> {
        override fun createFromParcel(parcel: Parcel): ExerciseExamination {
            return ExerciseExamination(parcel)
        }

        override fun newArray(size: Int): Array<ExerciseExamination?> {
            return arrayOfNulls(size)
        }
    }
}