package com.example.prototype
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.ui.AppBarConfiguration
import com.example.prototype.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var intent: Intent
    private lateinit var menu: Menu
    private lateinit var menuItem: MenuItem
    private lateinit var imageView: ImageView
    private val PERMISSION_REQUEST_READ_EXTERNAL_STORAGE = 100
    private val REQUEST_CODE_CHOOSE_IMAGE = 101

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        var bottomNavi = findViewById<BottomNavigationView>(R.id.bottomicon)
        var menuItem = bottomNavi.menu

        var jeosuji = findViewById<ImageButton>(R.id.jeosu)
        jeosuji.setOnClickListener{
                intent = Intent(this, JeosujiActivity::class.java)
                startActivity(intent)
               overridePendingTransition(R.anim.slide_out_left,R.anim.slide_in_right)
        }

        bottomNavi.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.noticeButton -> {
                    intent = Intent(this, NoticeActivity::class.java)
                    startActivity(intent)
                    overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left)
                    true

                }
                R.id.myPageButton -> {
                    intent = Intent(this, MyPageActivity::class.java)
                    startActivity(intent)
                    overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left)
                    true
                }
                R.id.gisButton -> {
                    intent = Intent(this,GisActivity::class.java)
                    startActivity(intent)
                    overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left)
                    true
                }
                R.id.cameraButton -> {
                    //intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE_SECURE)
                    //startActivity(intent)
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(this,
                            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                            PERMISSION_REQUEST_READ_EXTERNAL_STORAGE)
                    } else {
                        chooseImage()
                    }
                    overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left)
                    false
                }
                else -> false
            }
        }
    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_READ_EXTERNAL_STORAGE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                chooseImage()
            } else {
                Toast.makeText(this, "갤러리에서 이미지를 선택하려면 권한이 필요합니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun chooseImage() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        launcher.launch(intent)
    }
    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK && result.data?.data != null) {
            val uri = result.data?.data
            // 선택한 이미지 처리
        }
    }
}

