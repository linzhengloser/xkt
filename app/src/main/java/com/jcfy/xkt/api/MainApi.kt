package com.jcfy.xkt.api

import com.jcfy.xkt.module.InformationDetailWrapper
import com.jcfy.xkt.module.InformationWrapper
import com.jcfy.xkt.module.MainWrapper
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * @author linzheng
 */

interface MainApi {

    /**
     * 获取首页数据
     */
    @POST("home/getHome.do")
    @FormUrlEncoded
    fun getMainData(
            @Field("bannerType") bannerType: String = "1",
            @Field(PAGE_KEY) page: String = PAGE_DEFALUT_VALUE,
            @Field(LIMIT_KEY) limit: String = LIMIT_DEFAULT_VALUE
    ): Observable<BaseData<MainWrapper>>

    /**
     * 资讯列表
     */
    @POST("home/getNewsList.do")
    @FormUrlEncoded
    fun getInformationList(
            @Field("dictId") dictId: String,
            @Field(PAGE_KEY) page: String = PAGE_DEFALUT_VALUE,
            @Field(LIMIT_KEY) limit: String = LIMIT_DEFAULT_VALUE
    ): Observable<BaseData<InformationWrapper>>


    /**
     * 获取资讯详情
     */
    @POST("home/getNewsDetail.do")
    @FormUrlEncoded
    fun getInformationDetail(
            @Field("newsId") informationId: String
    ): Observable<BaseData<InformationDetailWrapper>>


    /**
     * 获取专栏列表
     */
    @POST("home/getColumnList.do")
    fun getColumnsList(
            @Field(PAGE_KEY) page: String = PAGE_DEFALUT_VALUE,
            @Field(LIMIT_KEY) limit: String = LIMIT_DEFAULT_VALUE
    ): Observable<BaseData<Any>>


}