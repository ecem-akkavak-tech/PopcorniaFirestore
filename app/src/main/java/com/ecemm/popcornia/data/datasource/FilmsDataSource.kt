package com.ecemm.popcornia.data.datasource
import androidx.lifecycle.MutableLiveData
import com.ecemm.popcornia.data.entity.Films
import com.google.firebase.firestore.CollectionReference

/**
 *  NOT: dataSource ve repository içindeki fonksiyonlarımız returnlü olmalıdır
 *  CollectionReference sayesinde firestoreda bulunan veritabanı üzerinde eş zamanlı çalışıcaz
 *  collectionKisiler  ->  firestoreda bulunan Kisiler tablosunu temsil ediyor
 *  Coroutine'ler ile çalışmadığımız için MutableLiveData ile ilgili işlemler buradan başlar
 *  addSnapshotListener -> gerçek zamanlı veri okumamızı sağlar
 **/

class FilmsDataSource(var collectionFilms : CollectionReference) {
     val filmsList = MutableLiveData<List<Films>>()

    //read
     fun filmYukle() : MutableLiveData<List<Films>> {
        collectionFilms.addSnapshotListener{value,error ->
            if(value!=null){
                //eğer değer varsa
                val liste=ArrayList<Films>()

                for(d in value.documents){
                    //documents burada firestoredaki Kisiler listesi
                    //biz sadece Films entitysine karşılık gelen columnları alıcaz ,yani documentID harici
                    val film = d.toObject(Films::class.java)

                    if(film!=null){
                        film.id = d.id //böylece documentID içinde bulanan unique değeri başta boş olan id içine yollamış olduk
                        liste.add(film) // film nesneleri listemize (entity)  aktarılır
                    }
                }
                filmsList.value = liste //ve böylece sayfamıza eş zamanlı yansımış olacak
            }
        }
        return filmsList
    }


}