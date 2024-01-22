package com.lamz.bookmark

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.lamz.bookmark.databinding.FragmentBookmarkBinding
import com.lamz.core.ui.ListKumpulanDoaAdapter
import com.lamz.kumpulandoadoa.presentation.ui.detail.DetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class BookmarkFragment : Fragment() {

    private val bookmarkViewModel : BookmarkViewModel by viewModel()
    private var _binding: FragmentBookmarkBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentBookmarkBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadKoinModules(bookmarkModule)

        if (activity != null){
            val listKumpulanDoaAdapter = ListKumpulanDoaAdapter { favorite ->
                if(favorite.favorite){
                    bookmarkViewModel.setFavoriteDoa(favorite,false)
                }else{
                    bookmarkViewModel.setFavoriteDoa(favorite,true)
                }
            }

            listKumpulanDoaAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            bookmarkViewModel.favoriteDoa.observe(viewLifecycleOwner) { doa ->
                listKumpulanDoaAdapter.setData(doa)
                binding.viewEmpty.root.visibility =
                    if (doa.isNotEmpty()) View.GONE else View.VISIBLE
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
}