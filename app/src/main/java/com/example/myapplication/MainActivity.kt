package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root) // BINDING.ROOT = VIEW
        binding.btnMain.setOnClickListener {
            val intent = Intent(this,SecondActivity::class.java)
                .putExtra("extra","someValue")
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        Toast.makeText(this,"Activity is laoded and can be interacted with",Toast.LENGTH_SHORT).show()
    }


}