package com.android_lesson

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.android_lesson.databinding.ActivityRBinding
import com.google.android.material.tabs.TabLayoutMediator

class RActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityRBinding
    private val fragmentList = ArrayList<Fragment>()
    private val fragmentTitleList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        viewBinding = ActivityRBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        fragmentList.add(R_Fragment1())
        fragmentList.add(R_Fragment2())
        fragmentList.add(R_Fragment3())

        val adapter = MyPagerAdapter(this)
        viewBinding.viewpager.adapter = adapter

        fragmentTitleList.add("Fragment 1")
        fragmentTitleList.add("Fragment 2")
        fragmentTitleList.add("Fragment 3")

        // Attach the viewpager to the TabLayout
        TabLayoutMediator(viewBinding.tabLayout, viewBinding.viewpager) { tab, position ->
            tab.setText(fragmentTitleList[position])
        }.attach()

        viewBinding.tabLayout.getTabAt(0)!!.setIcon(R.drawable.r_tab1)
        viewBinding.tabLayout.getTabAt(1)!!.setIcon(R.drawable.r_tab2)
        viewBinding.tabLayout.getTabAt(2)!!.setIcon(R.drawable.r_tab3)
    }

    inner class MyPagerAdapter(fragmentActivity: FragmentActivity) :
        FragmentStateAdapter(fragmentActivity) {
        override fun getItemCount(): Int {
            return fragmentList.size
        }

        override fun createFragment(position: Int): Fragment {
            return fragmentList[position]
        }

    }
}
