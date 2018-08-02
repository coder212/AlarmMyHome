package uk.co.arba.wakeupapp

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiSetDataInterface {
    @Headers("Content-type: application/json")
    @POST("setalarm/api/v1.0")
    fun sending(@Body sendModel: SendModel): Call<ReceiveModel>

    companion object Factory {
        val BASE_URL = "http://192.168.1.111:200/"
        fun create(): ApiSetDataInterface {
            val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            return retrofit.create(ApiSetDataInterface::class.java)
        }
    }

}