package com.example.prototype

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import java.net.URL

class LoginActivity : AppCompatActivity() {
    private lateinit var intent: Intent
    private lateinit var loginButton: Button
    private lateinit var userId : EditText
    private lateinit var userPw : EditText
    private lateinit var id : String
    private lateinit var pw : String
    private val USER_ID = "DATABASE_URL"
    private val USER_PW = "PASSWORD"



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_main)

        loginButton = findViewById(R.id.loginGo)
        val USER_ID = "DATABASE_URL"
        val USER_PW = "PASSWORD"


        loginButton.setOnClickListener {
            userId.findViewById<EditText>(R.id.idText)
            userPw.findViewById<EditText>(R.id.pwText)
            id = userId.getText().toString().trim()
            pw = userPw.getText().toString().trim()

        }
    }
}
