package com.cmilan.holycode_test.data.network

import com.cmilan.holycode_test.utils.PrefUtils
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject

class NetworkInterceptor
@Inject constructor(private val mPrefUtils: PrefUtils) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        val request: Request = chain.request()

        val response: Response = chain.proceed(request)

        if (response.code == 401) {
            // reset access token and logout user
        }
        return response
    }

}