package com.fadlurahmanf.datapandemi.di.module

import com.fadlurahmanf.datapandemi.api.ServiceApiRumahSakit
import com.fadlurahmanf.datapandemi.api.ServiceApiVaksinasi
import com.fadlurahmanf.datapandemi.api.ServiceKawalCorona
import com.fadlurahmanf.datapandemi.api.ServicePemerintah
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    val BASE_URL_PEMERINTAH = "https://data.covid19.go.id/public/"
    val BASE_URL_VAKSINASI = "https://vaksincovid19-api.vercel.app/api/"
    val BASE_URL_RUMAH_SAKIT = "https://rs-bed-covid-api.vercel.app/api/"
    val BASE_URL_KAWAL_CORONA = "https://api.kawalcorona.com"

    val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    val client = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()

    @Singleton
    @Provides
    fun providesRetrofitBuilder(): Retrofit.Builder {
        return Retrofit.Builder()
    }

    @Singleton
    @Provides
    fun providesApiPemerintahService(retrofitBuilder:Retrofit.Builder): ServicePemerintah {
        return retrofitBuilder.baseUrl(BASE_URL_PEMERINTAH)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build().create(ServicePemerintah::class.java)
    }

    @Singleton
    @Provides
    fun providesApiVaksinasi(retrofitBuilder: Retrofit.Builder) :ServiceApiVaksinasi{
        return retrofitBuilder.baseUrl(BASE_URL_VAKSINASI)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build().create(ServiceApiVaksinasi::class.java)
    }

    @Singleton
    @Provides
    fun providesApiRumahSakit(retrofitBuilder: Retrofit.Builder):ServiceApiRumahSakit{
        return retrofitBuilder.baseUrl(BASE_URL_RUMAH_SAKIT)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build().create(ServiceApiRumahSakit::class.java)
    }

    @Singleton
    @Provides
    fun providesApiKawalCorona(retrofitBuilder: Retrofit.Builder):ServiceKawalCorona{
        return retrofitBuilder.baseUrl(BASE_URL_KAWAL_CORONA)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build().create(ServiceKawalCorona::class.java)
    }
}