package com.jcfy.xkt.module

import android.os.Parcel
import android.os.Parcelable

/**
 * @author linzheng
 */
data class ExaminationResult(
        val achievement: Float,
        val totalQuestionCount: Int,
        val rightQuestionCount: Int,
        val wrongQuestionCount: Int,
        val totalTime: Long,
        val alreadyUseTime: Long
) : Parcelable {
    constructor(source: Parcel) : this(
            source.readFloat(),
            source.readInt(),
            source.readInt(),
            source.readInt(),
            source.readLong(),
            source.readLong()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeFloat(achievement)
        writeInt(totalQuestionCount)
        writeInt(rightQuestionCount)
        writeInt(wrongQuestionCount)
        writeLong(totalTime)
        writeLong(alreadyUseTime)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<ExaminationResult> = object : Parcelable.Creator<ExaminationResult> {
            override fun createFromParcel(source: Parcel): ExaminationResult = ExaminationResult(source)
            override fun newArray(size: Int): Array<ExaminationResult?> = arrayOfNulls(size)
        }
    }
}