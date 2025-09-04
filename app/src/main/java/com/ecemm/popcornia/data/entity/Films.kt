package com.ecemm.popcornia.data.entity
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull
import java.io.Serializable
//todo: db işlemleri: assets oluştur içine veritabanını kopyala>Films>Database>FilmsDao>FilmsDataSource>AppModule
//veri transfer işlemi için entity classı "Serializable" olmalı

@Entity(tableName="filmler")//veritabanı ile aynı tablo & kolon adları koy
data class Films(
    @ColumnInfo(name="id") @PrimaryKey(autoGenerate = true) @NotNull var id:Int,
    @ColumnInfo(name="ad") @NotNull var ad:String,
    @ColumnInfo(name="resim") @NotNull var resim:String,
    @ColumnInfo(name="fiyat") @NotNull var fiyat:Int
) :Serializable {}
