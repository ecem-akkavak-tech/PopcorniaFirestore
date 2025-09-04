package com.ecemm.popcornia.data.repo
import com.ecemm.popcornia.data.datasource.FilmsDataSource
import com.ecemm.popcornia.data.entity.Films


class FilmsRepository (var filmsDataSource:FilmsDataSource) {
    // Todo: DataSource ile bağlama işlemi
    //dependency injection lazım : var filmsDataSource = FilmsDataSource()

    suspend fun yukle():List<Films>{
        return filmsDataSource.filmYukle()
    }

}


