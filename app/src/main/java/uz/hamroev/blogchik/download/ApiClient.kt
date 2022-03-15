package uz.hamroev.blogchik.download

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient() {
    val BASE_URL = "http://"

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    companion object

    val service = retrofit.create(ApiService::class.java)
}