package com.example.prototype

import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.registerForActivityResult
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.LayoutInflaterCompat
import androidx.core.view.isGone
import androidx.core.view.isNotEmpty
import com.google.android.material.bottomnavigation.BottomNavigationView


class NoticeAddActivity : AppCompatActivity() {
    private lateinit var getContentLauncher: ActivityResultLauncher<Array<String>>
    private lateinit var upButton: Button
    private val imageViewList: MutableList<ImageView> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.noticeadd)


        var uploadlayout = findViewById<LinearLayout>(R.id.uploadLayout)
        upButton = findViewById(R.id.upload)

        getContentLauncher =
            registerForActivityResult(ActivityResultContracts.OpenMultipleDocuments()) { uris ->
                for (imageUri in uris) {
                    val imageView = ImageView(this)
                    val imageInputStream = contentResolver.openInputStream(imageUri)
                    imageView.setImageURI(imageUri)
                    imageView.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams(250,270)
                    )
                    imageViewList.add(imageView)
                    upButton.isGone = true
                }
                imageViewList.forEach {
                    imageView ->
                    uploadlayout.addView(imageView)
                }
            }

            uploadlayout.setOnClickListener{
            if (imageViewList.isNotEmpty()) {
                var imageView = imageViewList.last()
                imageViewList.remove(imageView)
                uploadlayout.removeView(imageView)
            }
                if (imageViewList.isEmpty()) {
                    upButton.isGone = false
                }
            }
            val upload = findViewById<Button>(R.id.upload)
            upload.setOnClickListener {
                val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
                    type = "image/*"
                    addCategory(Intent.CATEGORY_OPENABLE)
                    putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
                }
                getContentLauncher.launch(arrayOf("image/*"))
            }
            var back = findViewById<ImageButton>(R.id.BackMain)
            back?.setOnClickListener {
                intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            }
            var bottomNavi = findViewById<BottomNavigationView>(R.id.bottomicon)
            bottomNavi.menu.findItem(R.id.noticeButton).isChecked = true

            bottomNavi.setOnItemSelectedListener { menuItem ->
                val intent = when (menuItem.itemId) {
                    R.id.homeButton -> Intent(this, MainActivity::class.java)
                    R.id.myPageButton -> Intent(this, MyPageActivity::class.java)
                    R.id.cameraButton -> Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    R.id.gisButton -> Intent(this, GisActivity::class.java)
                    else -> null
                }
                intent?.let {
                    startActivity(it)
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                    true
                } ?: false
            }
        }
    }
