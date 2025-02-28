package com.app.myinapp.data.network

import com.app.myinapp.data.model.ImageDTO
import com.app.myinapp.data.model.VideoDTO
import com.app.myinapp.data.network.NetworkConstants.Headers
import com.app.myinapp.data.utils.ResponseResult
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.headers

class NetworkClient(private val client: HttpClient) {

    suspend fun getImageList(): ResponseResult<ImageDTO> {
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
                val imageList = response.body<ImageDTO>()
                println(imageList)

                return ResponseResult.Success(imageList)

            } else {
                return ResponseResult.Error(null, "error")
            }

        } catch (e: Exception) {
            return ResponseResult.Error(null, e.message.toString())
        }
    }


    suspend fun getVideoList(): ResponseResult<VideoDTO> {
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
                val videoList = response.body<VideoDTO>()
                return ResponseResult.Success(videoList)

            } else {
                return ResponseResult.Error(null, "error")
            }

        } catch (e: Exception) {
            return ResponseResult.Error(null, e.message.toString())
        }
    }

    suspend fun getFlowImageList(page: Int): ImageDTO {


        val response = client.get(NetworkConstants.curated) {
            url {
                parameters.append("page", "${page}")
                parameters.append("per_page", "20")
            }
            headers {
                appendAll(Headers())
            }
        }
        return response.body<ImageDTO>()

    }

    suspend fun getFlowVideoList(page: Int): VideoDTO {


        val response = client.get(NetworkConstants.video) {
            url {
                parameters.append("page", "${page}")
                parameters.append("per_page", "20")
            }
            headers {
                appendAll(Headers())
            }
        }
        return response.body<VideoDTO>()

    }
}