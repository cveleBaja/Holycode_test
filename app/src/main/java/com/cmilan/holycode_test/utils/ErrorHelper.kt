package com.cmilan.holycode_test.utils

import com.cmilan.holycode_test.data.network.BaseHttpError
import com.cmilan.holycode_test.ui.base.BaseActivity
import org.json.JSONException
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException

object ErrorHelper {
    fun showBackendError(baseActivity: BaseActivity, e: Throwable): Boolean {
        if (e is HttpException) {
            val response = e.response()
            if (response != null) {
                val responseBody = response.errorBody()
                if (responseBody != null) {
                    return try {
                        val jObjError = JSONObject(responseBody.string())
                        var description: String? = null
                        var title: String? = null
                        var exception: String? = null
                        var message: String? = null

                        when {
                            jObjError.has("description") -> {
                                description = jObjError.getString("description")
                            }
                            jObjError.has("title") -> {
                                title = jObjError.getString("title")
                            }
                            jObjError.has("exception") -> {
                                exception = jObjError.getString("exception")
                            }
                            jObjError.has("Message") -> {
                                message = jObjError.getString("Message")
                            }
                            jObjError.has("message") -> {
                                message = jObjError.getString("message")
                            }
                        }

                        var errorMessage: String? = "Error"
                        when {
                            description != null -> {
                                errorMessage = description
                            }
                            message != null -> {
                                errorMessage = message
                            }
                            exception != null -> {
                                errorMessage = exception
                            }
                            title != null -> {
                                errorMessage = title
                            }
                        }

                        baseActivity.showGeneralError(BaseHttpError(errorMessage ?: ""))
                        true
                    } catch (ignored: JSONException) {
                        false
                    } catch (ignored: IOException) {
                        false
                    }
                }
            }
        }
        return false
    }
}