package com.mix.mvvm.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.viewbinding.ViewBinding

/**
 * @Date 執筆時間 2022/01/06 19:57
 * @Author Caden
 * @Description
 * @Email code_legend@163.com
 * @Vesion 1.0
 */
abstract class BaseVmActivity<VB : ViewBinding, VM : BaseViewModel>
    (inflate : (LayoutInflater) -> VB) : BaseActivity<VB>(inflate) {


    protected open lateinit var mViewModel: VM

    //ページング
    protected open val mTotalCount = 20
    protected open var mCurrentSize = 0
    protected open var mCurrentPage = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        observe()
        initView()
        initData()
        setListener()
    }

    private fun initViewModel() {
        mViewModel = ViewModelProvider(this)[viewModelClass()]
    }

    protected abstract fun viewModelClass(): Class<VM>

    open fun initView() {}

    open fun initData() {}

    open fun setListener() {}

    open fun observe() {
        // 需要登录，跳转登录页
        mViewModel.needLogin.observe(this, {
            if (it) {
//                SpUtil.setBoolean(MyConfig.IS_LOGIN, false)
//                startActivity(Intent(this, LoginActivity::class.java))
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        mCurrentSize = 0
        mCurrentPage = 0
    }
}