package com.mix.mvvm.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2

/**
 * @Date 執筆時間 2022/01/29 22:07
 * @Author Caden
 * @Description
 * @Email code_legend@163.com
 * @Vesion 1.0
 */
//fun ViewPager2.setFragmentAdapter(activity: FragmentActivity, init: ) {
//
//}

fun ViewPager2.setFragmentAdapter(activity: FragmentActivity, init: SimpleFragmentStateAdapter.() -> Unit) {
    val adapter = SimpleFragmentStateAdapter(activity)
    adapter.init()
    this.adapter = adapter
}

open class SimpleFragmentStateAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle)
    : FragmentStateAdapter(fragmentManager, lifecycle) {

    constructor(fragment: Fragment) : this(fragment.childFragmentManager, fragment.lifecycle)

    constructor(activity: FragmentActivity) : this(activity.supportFragmentManager, activity.lifecycle)

    private var itemCount = 0

    private lateinit var createFragment: (Int) -> Fragment

    override fun getItemCount(): Int {
        return itemCount
    }

    fun count(count: Int) {
        itemCount = count
    }

    override fun createFragment(position: Int): Fragment {
        return createFragment.invoke(position)
    }

    fun createFragment(createFragment: (Int) -> Fragment) {
        this.createFragment = createFragment
    }

}


