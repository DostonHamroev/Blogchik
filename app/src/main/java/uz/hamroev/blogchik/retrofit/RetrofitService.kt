package uz.hamroev.blogchik.retrofit

import retrofit2.Call
import retrofit2.http.GET
import uz.hamroev.blogchik.models.blogs.Blogs
import uz.hamroev.blogchik.models.photos.Photo
import uz.hamroev.blogchik.models.projects.Projects
import uz.hamroev.blogchik.models.social.Social

interface RetrofitService {

    @GET("social")
    fun getSocials(): Call<Social>

    @GET("photos")
    fun getPhotos(): Call<Photo>

    @GET("projects")
    fun getProjects(): Call<Projects>


    @GET("blogs")
    fun getBlogs(): Call<Blogs>


}