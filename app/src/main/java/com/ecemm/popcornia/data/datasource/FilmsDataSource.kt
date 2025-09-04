package com.ecemm.popcornia.data.datasource

import com.ecemm.popcornia.data.entity.Films
import com.ecemm.popcornia.room.FilmsDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FilmsDataSource(var filmsDao: FilmsDao) {
    suspend fun filmYukle():List<Films> = withContext(Dispatchers.IO){
        return@withContext filmsDao.filmleriGetir()
    }


}