package uz.hamroev.blogchik.retrofit

object Common {

    val BASE_URL = "https://blogchik.uz/api/"

    val retrofitService: RetrofitService
    get() = RetrofitClient.getRetrofit(BASE_URL).create(RetrofitService::class.java)


}