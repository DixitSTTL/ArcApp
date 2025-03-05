package com.app.myinapp.data.network

import com.app.myinapp.data.model.ImageListDTO
import com.app.myinapp.data.model.VideoListDTO
import com.app.myinapp.data.network.NetworkConstants.Headers
import com.app.myinapp.data.utils.ResponseResult
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.headers

class NetworkClient(private val client: HttpClient) {

    suspend fun getImageList(): ResponseResult<ImageListDTO> {
        try {

            val response = client.get(NetworkConstants.curated) {
                url {
                    parameters.append("page", "2")
                    parameters.append("per_page", "20")
                }
                headers {
                    appendAll(Headers())
                }
            }
            if (response.status.value == 200) {
                val imageList = response.body<ImageListDTO>()
                println(imageList)

                return ResponseResult.Success(imageList)

            } else {
                return ResponseResult.Error(null, "error")
            }

        } catch (e: Exception) {
            return ResponseResult.Error(null, e.message.toString())
        }
    }


    suspend fun getVideoList(): ResponseResult<VideoListDTO> {
        try {

            val response = client.get(NetworkConstants.video) {
                url {
                    parameters.append("per_page", "20")
                }
                headers {
                    appendAll(Headers())
                }
            }
            if (response.status.value == 200) {
                val videoList = response.body<VideoListDTO>()
                return ResponseResult.Success(videoList)

            } else {
                return ResponseResult.Error(null, "error")
            }

        } catch (e: Exception) {
            return ResponseResult.Error(null, e.message.toString())
        }
    }

    suspend fun getFlowImageList(page: Int): ImageListDTO {


        val response = client.get(NetworkConstants.curated) {
            url {
                parameters.append("page", "${page}")
                parameters.append("per_page", "30")
            }
            headers {
                appendAll(Headers())
            }
        }
        return response.body<ImageListDTO>()

    }

    suspend fun getFlowVideoList(page: Int): VideoListDTO {


        val response = client.get(NetworkConstants.video) {
            url {
                parameters.append("page", "${page}")
                parameters.append("per_page", "30")
            }
            headers {
                appendAll(Headers())
            }
        }
        return response.body<VideoListDTO>()

    }

    suspend fun getSearchFlowImageList(page: Int, query: String): ImageListDTO {
        val response = client.get(NetworkConstants.searchImage) {
            url {
                parameters.append("page", "${page}")
                parameters.append("per_page", "30")
                parameters.append("query", query)
            }
            headers {
                appendAll(Headers())
            }
        }
        return response.body<ImageListDTO>()
    }

    suspend fun getSearchFlowVideoList(page: Int, query: String): VideoListDTO {
        val response = client.get(NetworkConstants.searchVideo) {
            url {
                parameters.append("page", "${page}")
                parameters.append("per_page", "30")
                parameters.append("query", query)
            }
            headers {
                appendAll(Headers())
            }
        }
        return response.body<VideoListDTO>()
    }
}