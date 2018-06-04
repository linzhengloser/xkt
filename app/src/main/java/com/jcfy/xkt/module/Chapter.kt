package com.jcfy.xkt.module

import com.lz.baselibrary.model.expandlist.Child
import com.lz.baselibrary.model.expandlist.Parent

/**
 * @author linzheng
 */


data class ChapterWrapper(
        val writingsList: List<Writings>
)

data class Writings(
        val writingsName: String,
        val chapterList: List<Chapter>
) : Parent()

data class Chapter(
        val chapterId: Int,
        val chapterName: String,
        val createTime: Long,
        val isFree: Int,
        val maxId: String,
        val random: Double,
        val schedule: String,
        val status: Int,
        val writingsId: Int
) : Child()