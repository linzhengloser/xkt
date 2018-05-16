package com.jcfy.xkt.api

import com.jcfy.xkt.module.UserWrapper
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * @author linzheng
 */
interface UserApi {

    /**
     * 登录
     */
    @POST("user/login.do")
    @FormUrlEncoded
    fun login(
            @Field("mobile") phoneNumber: String,
            @Field("pwd") pwd: String,
            @Field("equipmentId") equipmentId: String
    ): Observable<BaseData<UserWrapper>>


    /**
     * 第三方登录
     */
    @POST("user/thirdpartyLogin.do")
    @FormUrlEncoded
    fun login(
            @Field("mobile") phoneNumber: String,
            @Field("code") validateCode: String,
            @Field("unionid") unionId: String,
            @Field("loginType") phoneNumberIsRegister: String,
            @Field("pwd") pwd: String,
            @Field("type") loginType: String,
            @Field("thirdName") nickName: String,
            @Field("thirdSex") sex: String,
            @Field("thirdIocn") avatar: String
    ): Observable<BaseData<UserWrapper>>

    /**
     * 发送验证码
     */
    @POST("sys/getSmsCode.do")
    @FormUrlEncoded
    fun sendValidateCode(
            @Field("mobile") phoneNUmber: String,
            @Field("type") type: String
    ): Observable<BaseData<Any>>


    /**
     * 注册
     */
    @POST("user/register.do")
    @FormUrlEncoded
    fun register(
            @Field("mobile") phoneNumber: String,
            @Field("pwd") pwd: String,
            @Field("code") validateCode: String
    ): Observable<BaseData<Any>>


    /**
     * 修改密码
     */
    @POST("user/updatePwd.do")
    @FormUrlEncoded
    fun modifyPwd(
            @Field(USER_ID_KEY) userId: String,
            @Field("pwd") pwd: String,
            @Field("newPwd") newPwd: String
    ): Observable<BaseData<Any>>


    /**
     * 忘记密码
     */
    @POST("user/updatePwdByMobile.do")
    @FormUrlEncoded
    fun forgetPwd(
            @Field("mobile") phoneNumber: String,
            @Field("code") validateCode: String,
            @Field("newPwd") newPwd: String
    ): Observable<BaseData<Any>>

    /**
     * 绑定手机号
     */
    @POST("user/binding.do")
    @FormUrlEncoded
    fun bindPhoneNumber(
            @Field("mobile") phoneNumber: String,
            @Field("code") validateCode: String,
            @Field("token") token: String
    ): Observable<BaseData<UserWrapper>>

}