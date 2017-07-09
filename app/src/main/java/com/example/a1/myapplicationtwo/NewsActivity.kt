package com.example.a1.myapplicationtwo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.google.gson.JsonParser
import io.reactivex.Observable
import io.reactivex.Observable.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_new.*
import okhttp3.OkHttpClient
import okhttp3.Request
import java.util.*
import java.util.concurrent.TimeUnit

class NewsActivity : AppCompatActivity() {

    class New(
            val title: String, //заголовок
            val text: String, //текст
            val image: String) // картинка


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)


        /*val client = OkHttpClient.Builder()
                .readTimeout(1000L, TimeUnit.MILLISECONDS)
                .build()

        val task = Runnable{
            val reqest = Request.Builder()
                    .url("https://api.myjson.com/bins/a829r")
                    //.url("https://api.myjson.com/bins/a829r/get")
                    //.url("https//bash.im/index/1269/get?count=2&offset=60")
                    //.url("https://w3schools.com/test/demo_form.php/post: 80")
                    .build()

            val result = client.newCall(reqest)
                    .execute()
                    .body().string()


            val resultJson = JsonParser().parse(result).asJsonObject
            val code = resultJson["code"].asInt

            val news = mutableListOf<New>()

            val responseArray = resultJson["response"].asJsonArray
            for  (el in responseArray){
                val newJson = el.asJsonObject

                val title = newJson["title"].asString
                val text = newJson["text"].asString
                val image = newJson ["image"].asString

                news.add(
                        New(title, text, image))
            }
            runOnUiThread {
                val adapter = NewsAdapter()
                adapter.setData(news)
                recycleView.adapter = adapter
                recycleView.layoutManager = LinearLayoutManager (this)
            }
            Log.e("NewsActivity", result)
        }
        Thread(task).start()*/

        Observable.create<List<New>>
        { emitter ->

            val client = OkHttpClient.Builder()
                    .readTimeout(1000L, TimeUnit.MILLISECONDS)
                    .build()

            val request = Request.Builder()
                    .url("https://api.myjson.com/bins/a829r")
                    .build()

            val result = client.newCall(request)
                    .execute()
                    .body().string()

            val resultJson = JsonParser().parse(result).asJsonObject
            val code = resultJson["code"].asInt

            val responseArray = resultJson["response"].asJsonArray
            val news =
                    responseArray.map { el ->
                        val newJson = el.asJsonObject

                        val title = newJson["title"].asString
                        val text = newJson["text"].asString
                        val image = newJson["image"].asString

                        New(title, text, image)
                    }
                        emitter.onNext(news)


                    }

//создали в котлине список данных  который мы будем отображать(список новостей)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { news ->
                    val adapter = NewsAdapter()
                    adapter.setData(news)
                    recyclerView.adapter = adapter
                    recyclerView.layoutManager = LinearLayoutManager(this)
                            }

        }
    }


