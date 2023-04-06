package com.example.prototype

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.view.animation.Animation
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class NoticeActivity : AppCompatActivity() {
    private lateinit var intent: Intent
    private lateinit var likeButton:Button
    private lateinit var movingText:TextView
    private lateinit var anime:ObjectAnimator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.notice_main)

        var textcode = 0
        movingText = findViewById(R.id.testText)
        anime = ObjectAnimator.ofFloat(movingText, "translationX", -200f, 300f)
        anime.setDuration(3000)
        anime.setRepeatCount(ValueAnimator.INFINITE)
        anime.setRepeatMode(ValueAnimator.REVERSE)
        anime.start()


        var likeCount = 0
        likeButton = findViewById(R.id.Heart)
        likeButton.setOnClickListener{
            likeCount++
            likeButton.setText("$likeCount")
        }
        var add = findViewById<Button>(R.id.addNotice)
        add.setOnClickListener{
            intent = Intent(this,NoticeAddActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left)
        }
        var back = findViewById<ImageButton>(R.id.BackMain)
        back.setOnClickListener{
            intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left)
        }
        var bottomNavi = findViewById<BottomNavigationView>(R.id.bottomicon)
        bottomNavi.menu.findItem(R.id.noticeButton).setChecked(true)

        bottomNavi.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.homeButton -> {
                    intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left)
                    true
                }
                R.id.myPageButton -> {
                    intent = Intent(this,MyPageActivity::class.java)
                    startActivity(intent)
                    overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left)
                    true
                }
                R.id.cameraButton -> {
                    intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE_SECURE)
                    startActivity(intent)
                    overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left)
                    false
                }
                R.id.gisButton -> {
                    intent = Intent(this,GisActivity::class.java)
                    startActivity(intent)
                    overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left)
                    true
                }
                else -> false
            }
        }
    }
}