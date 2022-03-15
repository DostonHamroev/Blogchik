package uz.hamroev.blogchik.download

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {
    @GET()
    fun getImage(@Url imageUrl: String): Call<ResponseBody>
}