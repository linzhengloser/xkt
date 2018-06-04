package com.jcfy.xkt.module.mine

import android.os.Parcel
import android.os.Parcelable
import com.jcfy.xkt.module.Chapter
import com.lz.baselibrary.model.expandlist.Parent

/**
 * @author linzheng
 */

data class ScheduleWrapper(
        val writingMiddleList: List<Schedule>,
        val writingBaseList: List<Schedule>,
        val baseSchedule: String,
        val middleSchedule: String
) : Parcelable {
    constructor(source: Parcel) : this(
            ArrayList<Schedule>().apply { source.readList(this, Schedule::class.java.classLoader) },
            ArrayList<Schedule>().apply { source.readList(this, Schedule::class.java.classLoader) },
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeList(writingMiddleList)
        writeList(writingBaseList)
        writeString(baseSchedule)
        writeString(middleSchedule)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<ScheduleWrapper> = object : Parcelable.Creator<ScheduleWrapper> {
            override fun createFromParcel(source: Parcel): ScheduleWrapper = ScheduleWrapper(source)
            override fun newArray(size: Int): Array<ScheduleWrapper?> = arrayOfNulls(size)
        }
    }
}

data class Schedule(
        val chapterList: List<Chapter>,
        val createTime: Long,
        val kind: Int,
        val schedule: String,
        val status: Int,
        val type: Int,
        val writingsId: Int,
        val writingsName: String
): Parent()