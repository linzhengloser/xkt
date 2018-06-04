package com.jcfy.xkt.api

import com.jcfy.xkt.module.question.QuestionWrapper
import com.jcfy.xkt.utils.UserUtils
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * @author linzheng
 */
interface ExaminationApi {

    /**
     * 获取模拟考试
     */
    @POST("question/getPracticeTestList.do")
    @FormUrlEncoded
    fun getMockQuestionList(
            @Field("rank") type: Int,
            @Field("tel") tel: String = UserUtils.user?.mobile!!
    ): Observable<Response<QuestionWrapper>>

    /**
     * 获取错题强化
     */
    @POST("question/getErrorStrengthenList.do")
    @FormUrlEncoded
    fun getWrongQuestionStrengthenList(
            @Field("rank") type: Int,
            @Field("tel") tel: String = UserUtils.user?.mobile!!
    ): Observable<Response<QuestionWrapper>>

    /**
     * 获取专项强化
     */
    @POST("question/getSpecialTrengthenList.do")
    @FormUrlEncoded
    fun getSpecialTrengthenQuestionList(
            @Field("rank") type: Int,
            @Field("type") questionType: Int,
            @Field("tel") tel: String = UserUtils.user?.mobile!!
    ): Observable<Response<QuestionWrapper>>

}