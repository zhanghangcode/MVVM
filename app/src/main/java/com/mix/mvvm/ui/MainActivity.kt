package com.mix.mvvm.ui

import android.content.Intent
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.mix.mvvm.R
import com.mix.mvvm.adapter.CommonViewPagerAdapter
import com.mix.mvvm.base.BaseActivity
import com.mix.mvvm.base.BaseVmActivity
import com.mix.mvvm.databinding.ActivityMainBinding
import com.mix.mvvm.databinding.AppBarMainBinding
import com.mix.mvvm.databinding.ContentMainBinding
import com.mix.mvvm.ui.login.LoginActivity
import com.mix.mvvm.ui.main.home.HomeFragment
import com.mix.mvvm.ui.main.navi.NaviFragment
import com.mix.mvvm.ui.main.pro.ProjectFragment
import com.mix.mvvm.ui.main.tree.TreeFragment
import java.util.*

/**
 * @Date 執筆時間 2022/01/15 10:38
 * @Author Caden
 * @Description
 * @Email code_legend@163.com
 * @Vesion 1.0
 */
class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate){

    private lateinit var mAppBarMainBinding: AppBarMainBinding
    private lateinit var mContentMainBinding: ContentMainBinding

    override fun initialize() {
        super.initialize()
        //include
        mAppBarMainBinding = mBinding.appBarMain
        mContentMainBinding = mBinding.appBarMain.contentMain

        mAppBarMainBinding.toolbar.title = resources.getString(R.string.title_home)

        initBarDrawer()

        initFragments()
    }

    /**
     * DrawerとToolbarを繋がる
     */
    private fun initBarDrawer() {
        var toggle = ActionBarDrawerToggle(
            this,
            mBinding.drawerLayout,
            mAppBarMainBinding.toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        mBinding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
    }

    /**
     * 初期化Fragment
     */
    private fun initFragments() {
        val fragmentList: List<Fragment> = listOf(HomeFragment(), TreeFragment(), NaviFragment(), ProjectFragment())
        val viewPagerAdapter = CommonViewPagerAdapter(this, fragmentList)
        mAppBarMainBinding.contentMain.viewPager.offscreenPageLimit = 1
        mAppBarMainBinding.contentMain.viewPager.adapter = viewPagerAdapter
    }

    override fun onResume() {
        super.onResume()
        initListener()
    }

    private fun initListener() {
        mBinding.navView.setNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.nav_collect -> startActivity(Intent(this, LoginActivity::class.java))
                R.id.nav_share -> startActivity(Intent(this, LoginActivity::class.java))
                R.id.nav_about -> startActivity(Intent(this, LoginActivity::class.java))
                R.id.nav_logout -> startActivity(Intent(this, LoginActivity::class.java))
            }
            //閉じる
            mBinding.drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
    }




}