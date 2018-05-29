package com.jcfy.xkt.module.question

import android.os.Parcel
import android.os.Parcelable

/**
 * @author linzheng
 */
data class QuestionRecord(
        val questionId: Int,
        val selectionIndexArray: ArrayList<Int> = arrayListOf(),
        var isShowAnswer: Boolean = true,
        var isOpenEyeProtectionMode: Boolean = false
) : Parcelable {
    constructor(source: Parcel) : this(
            source.readInt(),
            ArrayList<Int>().apply { source.readList(this, Int::class.java.classLoader) },
            1 == source.readInt(),
            1 == source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(questionId)
        writeList(selectionIndexArray)
        writeInt((if (isShowAnswer) 1 else 0))
        writeInt((if (isOpenEyeProtectionMode) 1 else 0))
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<QuestionRecord> = object : Parcelable.Creator<QuestionRecord> {
            override fun createFromParcel(source: Parcel): QuestionRecord = QuestionRecord(source)
            override fun newArray(size: Int): Array<QuestionRecord?> = arrayOfNulls(size)
        }
    }
}