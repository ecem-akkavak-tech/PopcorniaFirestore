package com.ecemm.popcornia.data.repo
import androidx.lifecycle.MutableLiveData
import com.ecemm.popcornia.data.datasource.FilmsDataSource
import com.ecemm.popcornia.data.entity.Films


class FilmsRepository (var filmsDataSource:FilmsDataSource) {
    // Todo: DataSource ile bağlama işlemi
    //dependency injection lazım : var filmsDataSource = FilmsDataSource()

     fun yukle() : MutableLiveData<List<Films>> {
        return filmsDataSource.filmYukle()
     }

}


