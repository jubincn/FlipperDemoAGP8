package tech.jubin.flipper.demo.activities

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import tech.jubin.flipper.demo.network.Contributor
import tech.jubin.flipper.demo.network.NetworkCallback
import tech.jubin.flipper.demo.network.NetworkUtils
import tech.jubin.flipper.demo.R

class NetworkDemoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_network)

        val tvCount = findViewById<TextView>(R.id.count)
        findViewById<Button>(R.id.send_https_request).setOnClickListener {
            Thread {
                NetworkUtils.sendDemoRequest(object : NetworkCallback {
                    override fun onContributorsReturn(contributors: List<Contributor>?) {
                        Handler(Looper.getMainLooper()).post {
                            tvCount.text = contributors?.size.toString()
                        }
                    }
                })
            }.start()
            Toast.makeText(this, "Https Request Sent!", Toast.LENGTH_SHORT).show()
        }
    }
}