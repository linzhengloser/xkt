package com.jcfy.xkt.module

import android.os.Parcel
import android.os.Parcelable

/**
 * @author linzheng
 */

const val RANDOM_EXERCISE = "随机练习"

const val CHAPTER_EXERCISE = "章节练习"

const val SPECIAL_EXERCISE = "专项练习"

const val NO_DONE_EXERCISE = "未做题库"

const val WRONG_EXERCISE = "错题题库"

const val COLLECTION_EXERCISE = "收藏题库"

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