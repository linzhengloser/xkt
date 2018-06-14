package com.jcfy.xkt.api

import com.jcfy.xkt.module.ChapterWrapper
import com.jcfy.xkt.module.question.QuestionCollectionResult
import com.jcfy.xkt.module.question.QuestionWrapper
import com.jcfy.xkt.utils.UserUtils
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
            @Field("type") type: Int,
            @Field("tel") tel: String? = UserUtils.user?.mobile
    ): Observable<Response<QuestionWrapper>>


    /**
     * 获取章节列表
     */
    @POST("question/getChapterIndex.do")
    @FormUrlEncoded
    fun getChapterList(
            @Field("type") type: Int,
            @Field("tel") tel: String? = UserUtils.user?.mobile
    ): Observable<Response<ChapterWrapper>>

    /**
     * 根据章节 ID 获取练习列表
     */
    @POST("question/getChapterList.do")
    @FormUrlEncoded
    fun getQuestionListByChapterId(
            @Field("chapterId") chapterId: Int,
            @Field("tel") tel: String? = UserUtils.user?.mobile
    ): Observable<Response<QuestionWrapper>>


    /**
     * 获取未做题目列表
     */
    @POST("question/notDoneList.do")
    @FormUrlEncoded
    fun getNotDoneQuestionList(
            @Field("type") type: Int,
            @Field("tel") tel: String? = UserUtils.user?.mobile
    ): Observable<Response<QuestionWrapper>>

    /**
     * 保存做题记录
     */
    @POST("question/saveRecord.do")
    @FormUrlEncoded
    fun saveDoQuestionRecord(
            @Field("doList") rightQuestionIds: String,
            @Field("errorList") wrongQuestionIds: String,
            @Field("tel") tel: String? = UserUtils.user?.mobile
    ): Observable<Response<Any>>

    /**
     * 获取错题列表
     */
    @POST("question/getErrorList.do")
    @FormUrlEncoded
    fun getWrongQuestionList(
            @Field("type") type: Int,
            @Field("tel") tel: String = UserUtils.user?.mobile!!
    ): Observable<Response<QuestionWrapper>>


    /**
     * 获取收藏题库列表
     */
    @POST("question/getCollectionList.do")
    @FormUrlEncoded
    fun getCollectionQuestionList(
            @Field("type") type: Int,
            @Field("tel") tel: String? = UserUtils.user?.mobile
    ): Observable<Response<QuestionWrapper>>

    /**
     * 获取专项练习列表
     */
    @POST("question/getSpecialList.do")
    @FormUrlEncoded
    fun getSpecialQuestionList(
            @Field("type") type: Int,
            @Field("rank") rank: Int,
            @Field("tel") tel: String? = UserUtils.user?.mobile
    ): Observable<Response<QuestionWrapper>>


    /**
     * 收藏 or 取消收藏题目
     */
    @POST("question/toCollection.do")
    @FormUrlEncoded
    fun collectionQuestion(
            @Field("questionId") questionId: Int,
            @Field("tel") tel: String = UserUtils.user?.mobile!!
    ): Observable<Response<QuestionCollectionResult>>

}
