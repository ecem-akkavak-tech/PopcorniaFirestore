package com.ecemm.popcornia.ui.fragment
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.ecemm.popcornia.R
import com.ecemm.popcornia.data.entity.Films
import com.ecemm.popcornia.databinding.FragmentHomepageBinding
import com.ecemm.popcornia.ui.adapter.FilmsAdapter
import com.ecemm.popcornia.ui.viewmodel.HomepageViewModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomepageFragment : Fragment() {
    private lateinit var binding : FragmentHomepageBinding
    // TODO:  VIEW MODEL BAĞLAMA İŞLEMİ (fragmentlarda)
    private lateinit var viewModel: HomepageViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentHomepageBinding.inflate(inflater, container, false)

        // todo dataBinding kurulum
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_homepage , container, false)
        binding.homepageObject = this
        binding.toolbarHomepageTitle = "Filmler"

        // todo **NOT** ilgili sayfaya veri aktarma işlemi varsa livedata kullan

        /********  RECYCLER VIEW  ********/
        /** Listeleme işlemini yapabilmemizi sağlayan yapıdır.
         *  Kullanabilmek için belirli aşamalar var:
         *      1- önce card tasarımı oluşturmalıyız.
         *      2- card tasarımını temsil eden bir adapter oluşturmalıyız. (adapter,card tasarımı üzerindeki işlemleri kontrol eder)
         *      3- veri kümesi (arraylist,list vs) oluşturulur ve bu veri kümesi adapter'a aktarılır.
         *      4- Adapter'dan Recycler view'a aktarıyoruz ve Recycler view  görüntülemeyi sağlıyor
         *      5- NOT : HomePageFragment'tan DetailFragment'a veri transferi işlemi -> ADAPTER İÇİNDE (onBindViewHolder() metodunda)
         *   -------------------------------------------------------------------------------------------------------------
         *   FRAGMENT İÇİN -> binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
         *   ACTIVITY İÇİN -> binding.recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
         *
         *   LinearLayoutManager,alt alta hizalar :
         *      //binding.recyclerViewFilm.layoutManager = LinearLayoutManager(requireContext())
         *
         *   StaggeredGridLayoutManager elemanları belirttiğimiz sayı kadar satır içinde hizalar ve yukarı aşağı scroll eder
         *      //binding.recyclerViewFilm.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
         **/

        viewModel.filmList.observe(viewLifecycleOwner){ //todo: fragmentta olduğumuz için : viewLifecycleOwner
            // todo: veri yükleme işlemi burada sağlanır || it ->  viewModel.filmList
            // ADAPTER TANIMLAMA (context ve ilgili liste eklenir ve view model) & RECYCLERVIEW'A AKTARMA İŞLEMİ **/
            //todo: Adapter tanımlama (context & liste ekle) & Adapter'ın  RecyclerView'a aktarılma işlemi */
            val filmsAdapter = FilmsAdapter(requireContext(),it)
            binding.filmAdapter = filmsAdapter //adapterlar eşleştirilir
        }


        /********  TOOLBAR (TITLE & TEXT COLOR) ****/
        binding.toolbarHomepage.title="Films"
        binding.toolbarHomepage.setTitleTextColor(Color.WHITE)

        return binding.root
    }

    // TODO:  VIEW MODEL için gerekli metodlar-> onCreate & onResume (fragmentlarda)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel:HomepageViewModel by viewModels()
        viewModel = tempViewModel
    }

}