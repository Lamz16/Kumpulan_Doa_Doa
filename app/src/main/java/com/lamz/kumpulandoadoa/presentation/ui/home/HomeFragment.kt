package com.lamz.kumpulandoadoa.presentation.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.lamz.core.data.ResourceState
import com.lamz.core.domain.model.KumpulanDoaDoa
import com.lamz.core.ui.ListKumpulanDoaAdapter
import com.lamz.kumpulandoadoa.R
import com.lamz.kumpulandoadoa.databinding.FragmentHomeBinding
import com.lamz.kumpulandoadoa.presentation.model.home.HomeViewModel
import com.lamz.kumpulandoadoa.presentation.ui.detail.DetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val homeViewModel : HomeViewModel by viewModel()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null){

            val listKumpulanDoaAdapter = ListKumpulanDoaAdapter { favorite ->
                if(favorite.favorite){
                    homeViewModel.setFavoriteDoa(favorite,false)
                    showSnackbar(true,favorite)
                }else{
                    showSnackbar(false,favorite)
                    homeViewModel.setFavoriteDoa(favorite,true)
                }
            }

            listKumpulanDoaAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)

                val options = ActivityOptionsCompat.makeCustomAnimation(
                    requireContext(),
                    R.anim.zoom_in,
                    0
                )

                startActivity(intent, options.toBundle())
            }

            homeViewModel.doa.observe(viewLifecycleOwner) { doa ->
                if (doa != null) {
                    when (doa) {
                        is ResourceState.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }

                        is ResourceState.Success -> {
                            binding.progressBar.visibility = View.GONE
                            listKumpulanDoaAdapter.setData(doa.data)
                            Log.d("Suksess", "onViewCreated: Suksess")
                        }

                        is ResourceState.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.viewError.root.visibility = View.VISIBLE
                            binding.viewError.tvError.text =
                                doa.message ?: getString(R.string.kesalahan)
                            Log.d("Gagal", "onViewCreated: ${doa.message}")
                        }
                    }
                }
            }

            with(binding.rvDoa) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = listKumpulanDoaAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showSnackbar(action : Boolean, kumpulanDoaDoa : KumpulanDoaDoa){
        val message = if (action){
            "Dihapus dari favorite"
        }else{
            "Ditambahkan ke favorite"
        }
        val actionMessage = if (action){
            "Batal hapus"
        }else{
            "Batal tambah"
        }
        val snackBar = Snackbar.make(
            binding.root,
            message,
            Snackbar.LENGTH_SHORT
        )

        snackBar.setAction(actionMessage){ if (action){
            homeViewModel.setFavoriteDoa(kumpulanDoaDoa,true)
        } else{
            homeViewModel.setFavoriteDoa(kumpulanDoaDoa,false)
        } }
        val params = snackBar.view.layoutParams as FrameLayout.LayoutParams
        params.width = FrameLayout.LayoutParams.WRAP_CONTENT
        params.gravity = Gravity.BOTTOM or Gravity.CENTER
        snackBar.view.layoutParams = params
        snackBar.setBackgroundTint(ContextCompat.getColor(requireContext(),R.color.blue))
        val textView = snackBar.view.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
        textView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_bookmarks_24, 0, 0, 0)
        val actionText = snackBar.view.findViewById<TextView>(com.google.android.material.R.id.snackbar_action)
        actionText.setTextColor(ContextCompat.getColor(requireContext(), R.color.red))
        snackBar.view.background = ContextCompat.getDrawable(requireContext(), R.drawable.snackbacr_rounded)
        snackBar.show()
    }
}