package com.mix.mvvm.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
import androidx.viewpager2.adapter.FragmentStateAdapter
import java.util.ArrayList

/**
 * @Date 執筆時間 2022/01/15 22:05
 * @Author Caden
 * @Description
 * @Email code_legend@163.com
 * @Vesion 1.0
 */
class CommonViewPagerAdapter(activity: FragmentActivity, private var mFragments: List<Fragment>)
    : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int =mFragments.size
    override fun createFragment(position: Int): Fragment {
     return mFragments[position]
    }
}

