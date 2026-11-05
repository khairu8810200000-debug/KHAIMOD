package com.khaimod.app

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(28, 48, 28, 24)
            setBackgroundColor(0xFF111827.toInt())
        }
        val title = TextView(this).apply {
            text = "KHAIMOD"
            textSize = 32f
            setTextColor(0xFFFFFFFF.toInt())
            setPadding(0, 12, 0, 8)
        }
        val subtitle = TextView(this).apply {
            text = "Selamat datang"
            textSize = 16f
            setTextColor(0xFFD1D5DB.toInt())
            setPadding(0, 0, 0, 28)
        }
        root.addView(title)
        root.addView(subtitle)
        listOf("MULA", "TETAPAN", "TENTANG").forEach { label ->
            val button = Button(this).apply {
                text = label
                isAllCaps = false
                setOnClickListener {
                    Toast.makeText(this@MainActivity, "$label — fungsi sedang disediakan", Toast.LENGTH_SHORT).show()
                }
            }
            root.addView(button, LinearLayout.LayoutParams(-1, -2).apply { bottomMargin = 12 })
        }
        val footer = TextView(this).apply {
            text = "KHAIMOD • Versi 1.0"
            textSize = 12f
            setTextColor(0xFF9CA3AF.toInt())
            setPadding(0, 24, 0, 0)
        }
        root.addView(footer)
        setContentView(root)
    }
}
