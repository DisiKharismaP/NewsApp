package disiiy.khaper.newsapp.service

import disiiy.khaper.newsapp.model.ResponseNews
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("top-headlines")
    fun getNewsData(
        @Query("country") country : String?,
        @Query("apikey") apiKey : String?
    ):Call<ResponseNews>
}