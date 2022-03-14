package uz.hamroev.blogchik.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    fun getRetrofit(BASE_Url: String): Retrofit{
        val build = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_Url)
            .build()

        return build
    }
}