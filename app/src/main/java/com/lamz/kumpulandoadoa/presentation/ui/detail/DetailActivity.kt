package com.lamz.kumpulandoadoa.presentation.ui.detail

import android.os.Bundle
import android.view.Gravity
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

import com.google.android.material.snackbar.Snackbar
import com.lamz.core.domain.model.KumpulanDoaDoa
import com.lamz.kumpulandoadoa.R
import com.lamz.kumpulandoadoa.databinding.ActivityDetailBinding
import com.lamz.kumpulandoadoa.presentation.model.detail.DetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private val detailViewModel : DetailViewModel by viewModel()
    private var _binding: ActivityDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detailKumpulanDoaDoa = intent.getParcelableExtra<KumpulanDoaDoa>(EXTRA_DATA)
        showDataDoa(detailKumpulanDoaDoa)

        binding.topBar.btnBack.setOnClickListener {
            finish()
        }
    }

    private fun showDataDoa(detail: KumpulanDoaDoa?) {
        detail?.let {
            with(binding) {
                topBar.tvJdDoa.text = detail.doa
                detailCard.tvAyat.text = detail.ayat
                detailCard.tvArti.text = detail.artinya
                detailCard.tvLatin.text = detail.latin

                var state = detail.favorite
                setFavoriteDoa(state)
                detailCard.fab.setOnClickListener {
                        state = !state
                        showSnackbar(state)
                        detailViewModel.setFavoriteDoa(detail, state)
                        setFavoriteDoa(state)
                }
            }
        }
    }

    private fun setFavoriteDoa(state: Boolean){
        if(state){
            binding.detailCard.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_bookmarked))
        }else{
            binding.detailCard.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_bookmark))

        }
    }
    private fun showSnackbar(action: Boolean) {
        setFavoriteDoa(action)
        val message = if (action) {
            "Ditambahkan ke favorite"
        } else {
            "Dihapus dari favorite"
        }

        val snackbar = Snackbar.make(
            binding.root,
            message,
            Snackbar.LENGTH_SHORT
        )

        val params = snackbar.view.layoutParams as FrameLayout.LayoutParams
        params.width = FrameLayout.LayoutParams.WRAP_CONTENT
        params.gravity = Gravity.BOTTOM or Gravity.CENTER
        snackbar.view.layoutParams = params

        val textView = snackbar.view.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
        textView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_bookmarks_24, 0, 0, 0)

        val actionText = snackbar.view.findViewById<TextView>(com.google.android.material.R.id.snackbar_action)
        actionText.setTextColor(ContextCompat.getColor(this, R.color.red))

        snackbar.setBackgroundTint(ContextCompat.getColor(this, R.color.blue))
        snackbar.view.background = ContextCompat.getDrawable(this, R.drawable.snackbacr_rounded)
        snackbar.show()
    }



    companion object {
        const val EXTRA_DATA = "extra_data"
    }

}