package com.ecemm.popcornia.di
import com.ecemm.popcornia.data.datasource.FilmsDataSource
import com.ecemm.popcornia.data.repo.FilmsRepository
import com.google.firebase.Firebase
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.firestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//singleton, tek bir nesneyi pekçok yerde kullanabilmemize olanak sağlar
@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideFilmsRepository(filmsDataSource:FilmsDataSource) : FilmsRepository {
        return FilmsRepository(filmsDataSource)
    }


    @Provides
    @Singleton
    fun provideFilmsDataSource(collectionFilms:CollectionReference) : FilmsDataSource{
        return FilmsDataSource(collectionFilms)
    }


    @Provides
    @Singleton
    fun provideCollectionReference() : CollectionReference{
       //burada firestoredaki "Films" tablosu sağlanmalı
        return Firebase.firestore.collection("Films") //böylece Films tablosu olmasa bile ilk kayıtta oluşmuş olacak
    }
}