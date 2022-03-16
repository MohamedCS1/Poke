package com.example.di

import android.app.Application
import com.example.network.PokemonApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(Application::class)
class RetrofitModule {

    @Provides
    @Singleton
    fun providePokemonApiService():PokemonApiService
    {
        return Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build().create(PokemonApiService::class.java)
    }

}