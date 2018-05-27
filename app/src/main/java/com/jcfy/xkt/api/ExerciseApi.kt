package com.jcfy.xkt.api

import com.jcfy.xkt.module.ChapterWrapper
import com.jcfy.xkt.module.question.QuestionWrapper
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * @author linzheng
 */
interface ExerciseApi {

    /**
     * 获取随机联系
     */
    @POST("question/getRondomList.do")
    @FormUrlEncoded
    fun getRandomQuestionList(
            @Field("type") type: String = "1",
            @Field("tel") tel: String = "13477484198"
    ): Observable<Response<QuestionWrapper>>


    /**
     * 获取章节列表
     */
    @POST("question/getChapterIndex.do")
    @FormUrlEncoded
    fun getChapterList(
            @Field("type") type: String = "1",
            @Field("tel") tel: String = "13477484198"
    ): Observable<Response<ChapterWrapper>>

    /**
     * 根据章节 ID 获取练习列表
     */
    @POST("question/getChapterList.do")
    @FormUrlEncoded
    fun getQuestionListByChapterId(
            @Field("chapterId") chapterId: Int,
            @Field("tel") tel: String = "13477484198"
    ): Observable<Response<QuestionWrapper>>


    /**
     * 获取未做题目列表
     */
    @POST("question/notDoneList.do")
    @FormUrlEncoded
    fun getNotDoneQuestionList(
            @Field("type") type: String = "1",
            @Field("tel") tel: String = "1347748198"
    ): Observable<Response<QuestionWrapper>>

    /**
     * 保存做题记录
     */
    @POST("question/saveRecord.do")
    @FormUrlEncoded
    fun saveDoQuestionRecord(
            @Field("doList") successList: String,
            @Field("errorList") wrongList: String
    ): Response<Any>

    /**
     * 获取错题列表
     */
    @POST("question/getErrorList.do")
    @FormUrlEncoded
    fun getWrongQuestionList(
            @Field("type") type: String,
            @Field("tel") tel: String = "1347748198"
    ): Observable<Response<QuestionWrapper>>


    /**
     * 获取收藏题库列表
     */
    @POST("question/getCollectionList.do")
    @FormUrlEncoded
    fun getCollectionQuestionList(
            @Field("type") type: String,
            @Field("tel") tel: String = "1347748198"
    ): Observable<Response<QuestionWrapper>>

    @POST("question/getSpecialList.do")
    @FormUrlEncoded
    fun getSpecialQuestionList(
            @Field("type") type: String,
            @Field("rank") rank: String = type
    ): Observable<Response<QuestionWrapper>>


    /**
     * 收藏 or 取消收藏题目
     */
    @POST("question/toCollection.do")
    fun collectionQuestion(
            @Field("questionId") questionId: Int
    ): Observable<Response<String>>

}
