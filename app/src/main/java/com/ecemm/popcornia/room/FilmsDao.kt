package com.ecemm.popcornia.room

import androidx.room.Dao
import androidx.room.Query
import com.ecemm.popcornia.data.entity.Films

//todo: (Database Access Object) veritabanı üzerinde yazdıracağımız metodlarımız burada (erişim katmanı olarak görev yapacak)
//todo: verileri getirme - ekle - sil - güncelle işlemleri burada yapılır

@Dao
interface FilmsDao {

    //todo: VERİ GETİRME (Read)
    @Query("SELECT * FROM filmler")
    suspend fun filmleriGetir():List<Films>
    /* filmler tablosundaki her satırı Films sınıfına uygun olacak şekilde nesnelere dönüştürüp
      otomatik olarak istenen sayfamıza getirecek */
}