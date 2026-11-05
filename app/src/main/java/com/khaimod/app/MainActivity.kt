package com.khaimod.app

import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.google.android.gms.ads.LoadAdError

class MainActivity : AppCompatActivity() {
    private val dark = 0xFF111827.toInt()
    private val white = 0xFFFFFFFF.toInt()
    private var interstitialAd: InterstitialAd? = null
    private var bannerAd: AdView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MobileAds.initialize(this) {}
        loadInterstitial()
        showHome()
    }

    private fun loadInterstitial() {
        InterstitialAd.load(
            this,
            "ca-app-pub-6620088049494024/8378538028",
            AdRequest.Builder().build(),
            object : InterstitialAdLoadCallback() {
                override fun onAdLoaded(ad: InterstitialAd) { interstitialAd = ad }
                override fun onAdFailedToLoad(error: LoadAdError) { interstitialAd = null }
            }
        )
    }

    private fun showStart() {
        val ad = interstitialAd
        if (ad != null) {
            ad.show(this)
            interstitialAd = null
            loadInterstitial()
        } else {
            Toast.makeText(this, "Modul sedang disediakan", Toast.LENGTH_SHORT).show()
        }
    }

    private fun layout() = LinearLayout(this).apply {
        orientation = LinearLayout.VERTICAL
        gravity = Gravity.CENTER_HORIZONTAL
        setPadding(28, 42, 28, 16)
        setBackgroundColor(dark)
    }

    private fun label(root: LinearLayout, value: String, size: Float) {
        root.addView(TextView(this).apply {
            text = value
            textSize = size
            gravity = Gravity.CENTER
            setTextColor(white)
            setPadding(0, 12, 0, 16)
        }, LinearLayout.LayoutParams(-1, -2))
    }

    private fun action(root: LinearLayout, value: String, task: () -> Unit) {
        root.addView(Button(this).apply {
            text = value
            isAllCaps = false
            setOnClickListener { task() }
        }, LinearLayout.LayoutParams(-1, -2).apply { bottomMargin = 12 })
    }

    private fun addBanner(root: LinearLayout) {
        bannerAd = AdView(this).apply {
            adUnitId = "ca-app-pub-6620088049494024/5943946370"
            setAdSize(AdSize.BANNER)
            loadAd(AdRequest.Builder().build())
        }
        root.addView(bannerAd, LinearLayout.LayoutParams(-1, -2))
    }

    private fun showHome() {
        val root = layout()
        label(root, "KHAIMOD", 34f)
        label(root, "Selamat datang", 17f)
        action(root, "MULA") { showStart() }
        action(root, "TETAPAN") {
            val page = layout()
            label(page, "TETAPAN", 26f)
            action(page, "KEMBALI") { showHome() }
            setContentView(page)
        }
        action(root, "TENTANG") {
            val page = layout()
            label(page, "TENTANG KHAIMOD", 24f)
            label(page, "Aplikasi Android versi 1.1", 16f)
            action(page, "KEMBALI") { showHome() }
            setContentView(page)
        }
        label(root, "KHAIMOD • v1.1", 12f)
        addBanner(root)
        setContentView(root)
    }

    override fun onDestroy() {
        bannerAd?.destroy()
        super.onDestroy()
    }
}