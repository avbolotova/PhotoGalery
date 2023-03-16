package com.example.photogalery


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.photogalery.adapters.ViewPagerAdapter
import com.example.photogalery.databinding.ActivityMainBinding
import com.example.photogalery.fragments.Home
import com.google.android.material.tabs.TabLayoutMediator
import java.util.*


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tabLayoutId.tabRippleColor = null
        val adapter = ViewPagerAdapter(this)
        binding.viewpagerId.offscreenPageLimit = 2
        val tableNames = arrayOf("Unsplash photo")
        adapter.addFragment(Home(),tableNames[0])
        binding.viewpagerId.adapter = adapter
        TabLayoutMediator(binding.tabLayoutId,binding.viewpagerId) { tab ,position ->
            tab.text = tableNames[position]
            binding.viewpagerId.setCurrentItem(tab.position,true)
        }.attach()

    }
}