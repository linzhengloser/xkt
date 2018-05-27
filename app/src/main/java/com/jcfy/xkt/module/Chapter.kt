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
        val maxId: Int,
        val isFree: Int,
        val chapterId: Int,
        val chapterName: String
) : Child()