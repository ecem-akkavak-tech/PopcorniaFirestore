package com.ecemm.popcornia.ui.adapter
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ecemm.popcornia.R
import com.ecemm.popcornia.data.entity.Films
import com.ecemm.popcornia.databinding.CardDesignBinding
import com.ecemm.popcornia.ui.fragment.HomepageFragmentDirections
import com.google.android.material.snackbar.Snackbar

/**********     CARD TASARIMININ TEMSİL EDİLMESİ    ************

 * 1.parametre: context -> activity veya fragment içerisinde olduğumuzu belli ettiğimiz parçadır
 * 2.parametre veri kümesi : Liste tanımla (data type'ı Films classı olacak)

 * card_design.xml'i temsil eden classlara ihtiyaç var -> ** holder class **
 * holder class parametre olarak binding almalı -> card_design.xml için ** CardDesignBinding ** (xml'in isimlendirme mantığı ile)

 * not: cardView'ı burada her bir fimlerListesi'nin elemanı gibi düşün (her card 1 nesne)
 * böylece card_design (cardView) içerisindeki görsel ögelere erişim sağlanacak

 * Sonra bu adapter'ı istediğimiz sayfada (fragment,activity vs) -HomepageFragment- kullanmak için o sayfaya Recycler View ile eklemeliyiz
 **/


/** NOT
  * holder nesnesi, her bir cardView'ı yani card_design.xml'i temsil eden
    ve içindeki görsel bileşenlere (TextView, ImageView, vs.) erişmeni sağlayan köprü gibidir.

  * inner class olduğu için dış sınıftaki verilere de ulaşabilir.

  *holder view binding’i tutar; adapter ise veri kümesini ve bağlama işlemini yönetir.
 **/


class FilmsAdapter(var mContext:Context , var filmlerListesi:List<Films>) : RecyclerView.Adapter<FilmsAdapter.CardDesignHolder>(){

    inner class CardDesignHolder(var cardBinding : CardDesignBinding) : RecyclerView.ViewHolder(cardBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardDesignHolder {
        //viewBinding kurulumu burada yapılır
        // todo: data binding işlemi**/
        val binding:CardDesignBinding = DataBindingUtil.inflate(
                      LayoutInflater.from(mContext),
                      R.layout.card_design,parent,
                      false
        )
        return CardDesignHolder(binding)
    }

    override fun onBindViewHolder(holder: CardDesignHolder, position: Int) {
        /** card View ile ilgili tüm işlemler (tıklama vs) burada olacak
         * 1-holder nesnesi sayesinde CardDesignHolder classındaki  cardBinding'e ulaşıcaz
         * 2-position ise bir döngünün indexi gibi düşün (her 1 nesneye teker teker ulaşacak)
         * 3-HomePageFragment içinde oluşturulan recyclerView e gönderilen filmList burada doldurulur (karşılanır)
         **/

        val film = filmlerListesi.get(position)
        val cBinding = holder.cardBinding

        cBinding.filmsObject = film  // todo: xml ve fragmenttaki nesneler eşleştirilir

        // todo:resmi glide ile almak için
        var url="http://kasimadalan.pe.hu/filmler_yeni/resimler/${film.resim}"
        Glide.with(mContext).load(url)
             .override(500,750)
             .into(cBinding.imageViewFilmImg)



        /** card View tıklama & veri transferi & sayfa geçişi
         * 4-cardView'a (her bir card'a ) tıklanınca detailFragment sayfasına geçiş yapılsın ve her bir filmin detayı görüntülensin
         * hatırlatma : cardView yapısı HomePageFragment içinde, o yüzden **directions** o sayfa **args** DetailFragment
         * hatırlatma : main_activity_nav içinde film nesnesi argument olarak ekli olmalı
         **/
        cBinding.cardViewFilm.setOnClickListener{
            val gecis = HomepageFragmentDirections.detailPageGecis(film=film)
            Navigation.findNavController(it).navigate(gecis)
        }


        /**Sepet butonuna tıklama işlemi**
         *
         */
        cBinding.buttonSepet.setOnClickListener {
            Snackbar.make(it," \"${film.ad}\" sepete eklendi. ",Snackbar.LENGTH_SHORT).show()
        }

    }

    override fun getItemCount(): Int {
        return filmlerListesi.size
    }
}

