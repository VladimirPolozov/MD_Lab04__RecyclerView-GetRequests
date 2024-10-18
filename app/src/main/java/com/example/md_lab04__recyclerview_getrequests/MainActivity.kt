package com.example.md_lab04__recyclerview_getrequests

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread


class MainActivity : AppCompatActivity() {
    private val TAG = "Flickr cats"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val connection = URL("https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=ff49fcd4d4a08aa6aafb6ea3de826464&tags=cat&format=json&nojsoncallback=1").openConnection() as HttpURLConnection
        val getViaHttpButton: Button = findViewById(R.id.buttonHTTP)

        getViaHttpButton.setOnClickListener {
            thread {
                try {
                    val data = connection.inputStream.bufferedReader().use { it.readText() }
                    Log.d(TAG, data) // d - отладка (debug)
                    connection.disconnect()
                } finally {
                    connection.disconnect()
                }
            }
        }
    }
}