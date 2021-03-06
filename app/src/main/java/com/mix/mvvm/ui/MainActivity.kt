package com.mix.mvvm.ui

import android.content.Intent
import android.util.Log
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment

import com.mix.mvvm.R
import com.mix.mvvm.adapter.CommonViewPagerAdapter
import com.mix.mvvm.base.BaseActivity
import com.mix.mvvm.common.Config
import com.mix.mvvm.databinding.ActivityMainBinding
import com.mix.mvvm.databinding.AppBarMainBinding
import com.mix.mvvm.databinding.ContentMainBinding
import com.mix.mvvm.ui.login.LoginActivity
import com.mix.mvvm.ui.main.home.HomeFragment
import com.mix.mvvm.demo.TestFragment
import com.mix.mvvm.ui.about.AboutActivity
import com.mix.mvvm.ui.main.navi.NaviFragment
import com.mix.mvvm.ui.main.tree.TreeFragment
import com.mix.mvvm.ui.search.SearchActivity
import com.yechaoa.yutilskt.ShareUtil
import com.yechaoa.yutilskt.SpUtil
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
        val viewPagerAdapter = CommonViewPagerAdapter(this).apply {
            addFragment(HomeFragment())
            addFragment(TreeFragment())
            addFragment(NaviFragment())
            addFragment(TestFragment())
        }

        mAppBarMainBinding.contentMain.viewPager.offscreenPageLimit = 1
        mAppBarMainBinding.contentMain.viewPager.adapter = viewPagerAdapter
    }

    override fun onResume() {
        super.onResume()
        initListener()
        Log.d("cookie",SpUtil.getStringSet(Config.COOKIE).toString())
    }

    private fun initListener() {
        /**
         * サイドバー
         */
        mBinding.navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_collect -> startActivity(Intent(this, SearchActivity::class.java))
                R.id.nav_share -> ShareUtil.shareText(getString(R.string.wanandroid), getString(R.string.github))
                R.id.nav_about -> startActivity(Intent(this, AboutActivity::class.java))
                R.id.nav_logout -> {
                    AlertDialog.Builder(this).apply {
                        setTitle("提示")
                        setMessage("ログアウトしますか?")
                        setPositiveButton("確定") {_,_ ->
                            SpUtil.setBoolean(Config.IS_LOGIN, false)
                            SpUtil.removeByKey(Config.COOKIE)
                            startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                            finish()
                        }
                        setNegativeButton("キャンセル", null)
                        create()
                        show()
                    }
                }

            }
            mBinding.drawerLayout.closeDrawer(GravityCompat.START)

            true
        }

        mAppBarMainBinding.toolbar.setOnMenuItemClickListener{
            when (it.itemId) {
                R.id.action_search -> startActivity(Intent(this, SearchActivity::class.java))
//                R.id.action_settings ->
            }
            true
        }

        mAppBarMainBinding.contentMain.bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                    mAppBarMainBinding.contentMain.viewPager.currentItem = 0
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_tree -> {
                    mAppBarMainBinding.contentMain.viewPager.currentItem = 1
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_navi -> {
                    mAppBarMainBinding.contentMain.viewPager.currentItem = 2
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_project -> {
                    mAppBarMainBinding.contentMain.viewPager.currentItem = 3
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }
    }




}