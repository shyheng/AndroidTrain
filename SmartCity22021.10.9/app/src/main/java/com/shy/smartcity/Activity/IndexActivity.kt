package com.shy.smartcity.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.shy.smartcity.Fragment.*
import com.shy.smartcity.R

class IndexActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_index)

        findViewById<ViewPager2>(R.id.viewPager2).adapter = object : FragmentStateAdapter(this){
            override fun getItemCount() = 5

            override fun createFragment(position: Int): Fragment {
                return when(position){
                    0-> HomeFragment()
                    1-> ServiceFragment()
                    2-> CommunityFragment()
                    3 -> NewsFragment()
                    else -> PersonalFragment()
                }
            }

        }

        HomeFragment.viewPager2 = findViewById<ViewPager2>(R.id.viewPager2)

        TabLayoutMediator(findViewById(R.id.tab),findViewById(R.id.viewPager2)){ tab, position->
            when(position){
                0->tab.setIcon(R.drawable.home).setText("首页")
                1->tab.setIcon(R.drawable.service).setText("服务")
                2->tab.setIcon(R.drawable.community).setText("社区")
                3->tab.setIcon(R.drawable.news).setText("新闻")
                else->tab.setIcon(R.drawable.personal).setText("个人中心")
            }
        }.attach()
    }

}