package com.ecemm.popcornia.data.entity

import java.io.Serializable
//veri transfer işlemi için entity classı "Serializable" olmalı

data class Films(
   var id:String="",
   var ad:String="",
   var resim:String="",
   var fiyat:String=""
) :Serializable {}
