package com.jcfy.xkt.api

import com.jcfy.xkt.module.ColumnWrapper
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
            @Field(PAGE_KEY) page: String = PAGE_DEFAULT_VALUE,
            @Field(LIMIT_KEY) limit: String = LIMIT_DEFAULT_VALUE
    ): Observable<Response<MainWrapper>>

    /**
     * 资讯列表
     */
    @POST("home/getNewsList.do")
    @FormUrlEncoded
    fun getInformationList(
            @Field("dictId") dictId: String,
            @Field(PAGE_KEY) page: String = PAGE_DEFAULT_VALUE,
            @Field(LIMIT_KEY) limit: String = LIMIT_DEFAULT_VALUE
    ): Observable<Response<InformationWrapper>>


    /**
     * 获取资讯详情
     */
    @POST("home/getNewsDetail.do")
    @FormUrlEncoded
    fun getInformationDetail(
            @Field("newsId") informationId: String
    ): Observable<Response<InformationDetailWrapper>>


    /**
     * 获取专栏列表
     */
    @POST("column/getColumnList.do")
    @FormUrlEncoded
    fun getColumnsList(
            @Field(PAGE_KEY) page: String = PAGE_DEFAULT_VALUE,
            @Field(LIMIT_KEY) limit: String = LIMIT_DEFAULT_VALUE
    ): Observable<Response<ColumnWrapper>>


}