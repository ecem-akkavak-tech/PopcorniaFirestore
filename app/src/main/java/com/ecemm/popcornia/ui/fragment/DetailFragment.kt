package com.ecemm.popcornia.ui.fragment
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.ecemm.popcornia.R
import com.ecemm.popcornia.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private lateinit var binding : FragmentDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)

        // TODO: dataBinding kurulum
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail , container, false)

        //TODO: Veriyi alan taraftayız,bu yüzden **args**  && xml ve fragment tarafındaki nesneler eşleşir **/
        val bundle:DetailFragmentArgs by navArgs()
        val film = bundle.film
        binding.filmObject = film /****/

        // todo:resmi glide ile almak için
        val context = requireContext()
        var url="http://kasimadalan.pe.hu/filmler_yeni/resimler/${film.resim}"
        Glide.with(context).load(url)
            .override(700,950)
            .into(binding.imageViewFilm)


        /********  TOOLBAR (TITLE & TEXT COLOR) ****/
        binding.toolbarDetailpage.setTitleTextColor(Color.WHITE)
        return binding.root
    }


}