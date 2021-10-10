package com.shy.smartcity.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.shy.smartcity.*
import com.shy.smartcity.Fragment.*

class IndexActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_index)


        findViewById<ViewPager2>(R.id.viewPager2).adapter = object : FragmentStateAdapter(this){
            override fun getItemCount() = 5

            override fun createFragment(position: Int): Fragment {
                return when(position){
                    0-> IndexFragment()
                    1-> ServicesFragment()
                    2-> PartyFragment()
                    3 -> NewFragment()
                    else -> PersonalFragment()
                }
            }

        }

        IndexFragment.viewPager2 = findViewById<ViewPager2>(R.id.viewPager2)
        ServicesFragment.viewPager2 = findViewById<ViewPager2>(R.id.viewPager2)

        TabLayoutMediator(findViewById(R.id.tab),findViewById(R.id.viewPager2)){ tab, position->
            when(position){
                0->tab.setIcon(R.drawable.index)
                1->tab.setIcon(R.drawable.services)
                2->tab.setIcon(R.drawable.party)
                3->tab.setIcon(R.drawable.news)
                else->tab.setIcon(R.drawable.personal)
            }
        }.attach()
    }
}