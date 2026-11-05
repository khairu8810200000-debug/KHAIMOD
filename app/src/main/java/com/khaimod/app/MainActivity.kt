package com.khaimod.app
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private val dark = 0xFF111827.toInt()
    private val white = 0xFFFFFFFF.toInt()
    override fun onCreate(savedInstanceState: Bundle?) { super.onCreate(savedInstanceState); showHome() }
    private fun layout() = LinearLayout(this).apply {
        orientation=LinearLayout.VERTICAL; gravity=Gravity.CENTER_HORIZONTAL
        setPadding(28,42,28,24); setBackgroundColor(dark)
    }
    private fun label(root: LinearLayout, value: String, size: Float) {
        root.addView(TextView(this).apply {
            text=value; textSize=size; gravity=Gravity.CENTER; setTextColor(white); setPadding(0,12,0,16)
        }, LinearLayout.LayoutParams(-1,-2))
    }
    private fun action(root: LinearLayout, value: String, task: () -> Unit) {
        root.addView(Button(this).apply { text=value; isAllCaps=false; setOnClickListener { task() } },
            LinearLayout.LayoutParams(-1,-2).apply { bottomMargin=12 })
    }
    private fun showHome() {
        val root=layout(); label(root,"KHAIMOD",34f); label(root,"Selamat datang",17f)
        action(root,"MULA") { Toast.makeText(this,"KHAIMOD sedia digunakan",Toast.LENGTH_SHORT).show() }
        action(root,"TETAPAN") { val v=layout(); label(v,"TETAPAN",26f); action(v,"KEMBALI"){showHome()}; setContentView(v) }
        action(root,"TENTANG") { val v=layout(); label(v,"TENTANG KHAIMOD",24f); label(v,"Aplikasi Android versi 1.0",16f); action(v,"KEMBALI"){showHome()}; setContentView(v) }
        label(root,"KHAIMOD • v1.0",12f); setContentView(root)
    }
}