package com.jcfy.xkt.module.question

import android.os.Parcel
import android.os.Parcelable

/**
 * @author linzheng
 */

data class QuestionWrapper(
        val questionCount: Int,
        val rechargeStatus: Int,
        val questionList: List<Question>,
        val maxId: Int,
        val nextMaxId: Int,
        val nextChapterId: Int
) : Parcelable {
    constructor(source: Parcel) : this(
            source.readInt(),
            source.readInt(),
            ArrayList<Question>().apply { source.readList(this, Question::class.java.classLoader) },
            source.readInt(),
            source.readInt(),
            source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(questionCount)
        writeInt(rechargeStatus)
        writeList(questionList)
        writeInt(maxId)
        writeInt(nextMaxId)
        writeInt(nextChapterId)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<QuestionWrapper> = object : Parcelable.Creator<QuestionWrapper> {
            override fun createFromParcel(source: Parcel): QuestionWrapper = QuestionWrapper(source)
            override fun newArray(size: Int): Array<QuestionWrapper?> = arrayOfNulls(size)
        }
    }
}

data class Question(
        val questionId: Int,
        val optionsList: List<Options>,
        val answerlist: List<String>,
        val isCollection: Int,
        val explanation: String,
        val type: Int,
        val content: String) : Parcelable {
    constructor(source: Parcel) : this(
            source.readInt(),
            source.createTypedArrayList(Options.CREATOR),
            source.createStringArrayList(),
            source.readInt(),
            source.readString(),
            source.readInt(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(questionId)
        writeTypedList(optionsList)
        writeStringList(answerlist)
        writeInt(isCollection)
        writeString(explanation)
        writeInt(type)
        writeString(content)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Question> = object : Parcelable.Creator<Question> {
            override fun createFromParcel(source: Parcel): Question = Question(source)
            override fun newArray(size: Int): Array<Question?> = arrayOfNulls(size)
        }
    }
}

data class Options(
        val tab: Int,
        val content: String
) : Parcelable {
    constructor(source: Parcel) : this(
            source.readInt(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(tab)
        writeString(content)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Options> = object : Parcelable.Creator<Options> {
            override fun createFromParcel(source: Parcel): Options = Options(source)
            override fun newArray(size: Int): Array<Options?> = arrayOfNulls(size)
        }
    }
}