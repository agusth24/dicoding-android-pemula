package com.example.prototype.mymoviecataloguemade5

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.prototype.mymoviecataloguemade5.ui.movies.Movies
import com.example.prototype.mymoviecataloguemade5.ui.tvshow.Tvshows
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class MainViewModel : ViewModel() {
    companion object {
        private val API_KEY = BuildConfig.API_KEY
    }

    val listMovies = MutableLiveData<ArrayList<Movies>>()
    val listTvshows = MutableLiveData<ArrayList<Tvshows>>()

    internal fun setDataMovies(key: String? = null) {

        val client = AsyncHttpClient()
        val listItems = ArrayList<Movies>()
        val url = if(key == null ) {
            "https://api.themoviedb.org/3/discover/movie?api_key=$API_KEY&language=" + Locale.getDefault().toLanguageTag()
        } else {
            "https://api.themoviedb.org/3/search/movie?api_key=$API_KEY&language=" + Locale.getDefault().toLanguageTag() + "&query=" + key
        }
        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<Header>,
                responseBody: ByteArray
            ) {
                try {
                    val result = String(responseBody)
                    val responseObject = JSONObject(result)
                    val list = responseObject.getJSONArray("results")

                    val originalFormat: DateFormat =
                        SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                    val targetFormat: DateFormat =
                        SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault())

                    for (i in 0 until list.length()) {
                        val movie = list.getJSONObject(i)
                        val movieItem = Movies()

                        val date: Date = originalFormat.parse(movie.getString("release_date"))
                        val formattedDate: String = targetFormat.format(date)

                        movieItem.id = movie.getString("id")
                        movieItem.title = movie.getString("original_title")
                        movieItem.description = movie.getString("overview")
                        movieItem.release = formattedDate
                        movieItem.rating = movie.getDouble("vote_average")
                        movieItem.photo = movie.getString("poster_path")
                        listItems.add(movieItem)
                    }
                    listMovies.postValue(listItems)
                } catch (e: Exception) {
                    listMovies.postValue(null)
                    Log.d("Exception", e.message.toString())
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<Header>,
                responseBody: ByteArray,
                error: Throwable
            ) {
                listMovies.postValue(null)
                Log.d("onFailure", error.message.toString())
            }
        })
    }

    internal fun getMovies(): LiveData<ArrayList<Movies>> {
        return listMovies
    }

    internal fun setDataTvshows(key: String? = null) {
        val client = AsyncHttpClient()
        val listItems = ArrayList<Tvshows>()
        val url = if(key == null ) {
            "https://api.themoviedb.org/3/discover/tv?api_key=$API_KEY&language=" + Locale.getDefault().toLanguageTag()
        } else {
            "https://api.themoviedb.org/3/search/tv?api_key=$API_KEY&language=" + Locale.getDefault().toLanguageTag() + "&query=" + key
        }

        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<Header>,
                responseBody: ByteArray
            ) {
                try {
                    val result = String(responseBody)
                    val responseObject = JSONObject(result)
                    val list = responseObject.getJSONArray("results")

                    val originalFormat: DateFormat =
                        SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                    val targetFormat: DateFormat =
                        SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault()) // 20120821

                    for (i in 0 until list.length()) {
                        val tvshow = list.getJSONObject(i)
                        val tvshowItems = Tvshows()

                        val date: Date = originalFormat.parse(tvshow.getString("first_air_date"))
                        val formattedDate: String = targetFormat.format(date)

                        tvshowItems.id = tvshow.getString("id")
                        tvshowItems.title = tvshow.getString("original_name")
                        tvshowItems.description = tvshow.getString("overview")
                        tvshowItems.release = formattedDate
                        tvshowItems.rating = tvshow.getDouble("vote_average")
                        tvshowItems.photo = tvshow.getString("poster_path")
                        listItems.add(tvshowItems)
                    }
                    listTvshows.postValue(listItems)
                } catch (e: Exception) {
                    listTvshows.postValue(null)
                    Log.d("Exception", e.message.toString())
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<Header>,
                responseBody: ByteArray,
                error: Throwable
            ) {
                listTvshows.postValue(null)
                Log.d("onFailure", error.message.toString())
            }
        })
    }

    internal fun getTvshow(): LiveData<ArrayList<Tvshows>> {
        return listTvshows
    }
}