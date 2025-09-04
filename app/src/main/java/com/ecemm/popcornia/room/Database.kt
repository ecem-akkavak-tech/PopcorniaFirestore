package com.ecemm.popcornia.room
import androidx.room.Database
import androidx.room.RoomDatabase
import com.ecemm.popcornia.data.entity.Films

@Database(entities = [Films::class], version = 1) //entities içindeki ilgili tablo yazılır, 1 tablo ; 1 interface
abstract class Database :RoomDatabase(){
    //todo: Her tablonun kendine ait interfacei bulunur ve bu interface'e (filmsDao) erişim sağlarız
    abstract fun getFilmsDao() : FilmsDao //todo: bu abstract gövdesiz metod, FilmsDao türündeki interfacei return edecek
}