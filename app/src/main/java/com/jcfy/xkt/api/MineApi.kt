package com.jcfy.xkt.api

import com.jcfy.xkt.module.mine.AchievementWrapper
import com.jcfy.xkt.module.mine.HelpWrapper
import com.jcfy.xkt.module.mine.RechargeWrapper
import com.jcfy.xkt.module.mine.ScheduleWrapper
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


/**
 * @author linzheng
 */
interface MineApi {
    /**
     * 获取进度
     */
    @POST("mine/schedule.do")
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
    fun getAchievement(): Observable<Response<AchievementWrapper>>

    /**
     * 充值中心
     */
    @POST("mine/voucherCenter.do")
    fun getRechargeCenter(): Observable<Response<RechargeWrapper>>

    /**
     * 消息中心
     */
    @POST("mine/voucherCenter.do")
    @FormUrlEncoded
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