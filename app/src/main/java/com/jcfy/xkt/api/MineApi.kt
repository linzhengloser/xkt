package com.jcfy.xkt.api

import com.jcfy.xkt.module.mine.HelpWrapper
import com.jcfy.xkt.module.mine.ScheduleWrapper
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.Headers
import retrofit2.http.POST


/**
 * @author linzheng
 */
interface MineApi {
    /**
     * 获取进度
     */
    @POST("mine/schedule.do")
    @Headers("wiexin_session_user: 0beb34f9-98a3-805f-1116-7c9377b26fa1")
    fun getSchedule(
    ): Observable<Response<ScheduleWrapper>>

    /**
     * 获取错题
     */
    @POST("mine/errors.do")
    fun getWrongQuestion(): Observable<Response<ScheduleWrapper>>

    /**
     * 获取收藏
     */
    @POST("mine/collect.do")
    fun getCollection(): Observable<Response<ScheduleWrapper>>

    /**
     * 获取成绩
     */
    @POST("mine/performance.do")
    fun getScore(): Observable<Response<ScheduleWrapper>>

    /**
     * 充值中心
     */
    @POST("mine/voucherCenter.do")
    fun getRechargeCenter(): Observable<Response<Any>>

    /**
     * 消息中心
     */
    @POST("mine/voucherCenter.do")
    fun getMessageCenter(
            @Field("type") type: Int,
            @Field(PAGE_KEY) page: String = PAGE_DEFAULT_VALUE,
            @Field(LIMIT_KEY) limit: String = LIMIT_DEFAULT_VALUE
    ): Observable<Response<Any>>

    /**
     * 使用帮助
     */
    @POST("mine/help.do")
    fun getUsingHelp(): Observable<Response<HelpWrapper>>

    /**
     * 关于我们
     */
    fun getAboutUs(): Observable<Response<HelpWrapper>>

}