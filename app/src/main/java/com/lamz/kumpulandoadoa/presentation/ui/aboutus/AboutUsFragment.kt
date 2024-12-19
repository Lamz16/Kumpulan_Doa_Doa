package com.lamz.kumpulandoadoa.presentation.ui.aboutus

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.lamz.kumpulandoadoa.databinding.FragmentAboutusBinding

class AboutUsFragment : Fragment() {

    private var _binding: FragmentAboutusBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAboutusBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val webView = binding.webView

        // Aktifkan JavaScript
        webView.settings.javaScriptEnabled = true

        // Sesuaikan konten dengan layar
        webView.settings.loadWithOverviewMode = true
        webView.settings.useWideViewPort = true

        // Tambahkan pengaturan zoom jika diperlukan
        webView.settings.builtInZoomControls = true
        webView.settings.displayZoomControls = false

        // Muat URL
        webView.loadUrl("https://github.com/Lamz16")
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}