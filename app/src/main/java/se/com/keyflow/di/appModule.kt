package se.com.keyflow.di

import androidx.preference.PreferenceManager
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.squareup.moshi.Moshi
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import se.com.keyflow.MainActivityViewModel
import se.com.keyflow.data.EventsRepository
import se.com.keyflow.data.api.EventsApi
import se.com.keyflow.ui.mainFragment.MainEventsListViewModel
import se.com.keyflow.ui.splash.SplashViewModel
import se.mobileinteraction.keyflow.BuildConfig
import se.mobileinteraction.keyflow.R


@UnstableDefault
val appModule = module {
    factory<CallAdapter.Factory> { RxJava2CallAdapterFactory.create() }
    factory {
        Json(
            JsonConfiguration(
                encodeDefaults = false,
                ignoreUnknownKeys = true,
                isLenient = true
            )
        ).asConverterFactory("application/json".toMediaType())
    }

    single {

        val clientBuilder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) clientBuilder.addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })



        Retrofit.Builder()
            .addCallAdapterFactory(get())
            .client(clientBuilder.build())
            .baseUrl(androidApplication().getString(R.string.base_api))
            .addConverterFactory(get())
            .build()
    }

    single {

        Json {
            (JsonConfiguration.Default.apply {
                ignoreUnknownKeys = true
                isLenient = false
            })
        }
    }

    single {
        val retrofit: Retrofit = get()
        retrofit.create(EventsApi::class.java)
    }

    single {
        PreferenceManager.getDefaultSharedPreferences(androidContext())
    }

    single {
        EventsRepository(get(), get())
    }

    single { Moshi.Builder().build() }
    viewModel { MainActivityViewModel(androidContext(), get(), get()) }
    viewModel { SplashViewModel(get()) }
    viewModel { MainEventsListViewModel(get(), get()) }

}
