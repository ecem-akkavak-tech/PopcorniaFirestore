package com.ecemm.popcornia.ui.viewmodel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ecemm.popcornia.data.entity.Films
import com.ecemm.popcornia.data.repo.FilmsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomepageViewModel @Inject constructor (var filmsRepository : FilmsRepository):ViewModel(){
    // Todo: Repository ile bağlama işlemi **/
    //dependency injection lazım : var filmsRepository = FilmsRepository()

    // Todo: Sayfaya veri aktarma işlemi olduğu için Livedata (içinde Films türünde liste içermeli)kullanıcaz
    var filmList = MutableLiveData<List<Films>>()

     init {
     // todo: Uygulamanın ilk açıldığı anda veri getirmesi için init gerekir
     //ViewModel ilk çalıştığı anda filmList'i tetikle ve getir
        yukle()
     }
     fun yukle(){
           filmList = filmsRepository.yukle() //firestoreda filmList.value yapmıyoruz
     }
}
/**
 * Her activity veya fragment 1 View Model'e sahiptir.
 * View Modeller fragment & activityleri yönetir
 * View'lardaki fonksiyonlar genelde return'lü çalışmaz.
 * Değer okuma ve üzerindeki fonksiyonları çalıştırma işlemleri burada yapılır (live data ile).
 ** İlgili activity'e (ya da fragment) veri göndermeliyiz, bunu da activity (ya da fragment) içinde bağlayarak yapabiliriz.
 **/