package com.mix.mvvm.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding

/**
 * @Date 執筆時間 2022/01/15 11:23
 * @Author Caden
 * @Description
 * @Email code_legend@163.com
 * @Vesion 1.0
 */
abstract class BaseVmFragment<VB : ViewBinding, VM : BaseViewModel>(inflate: (LayoutInflater) -> VB)
    : BaseFragment<VB>(inflate) {

        protected lateinit var mViewModel: VM
        private var lazyLoaded = false

    //分页参数
    protected open val mTotalCount = 20//每次加载数量
    protected open var mCurrentSize = 0//当前加载数量
    protected open var mCurrentPage = 0//当前加载页数

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        observe()
        initView()
        initData()
        setListener()
    }
    override fun onResume() {
        super.onResume()
        // 遅延ロード
        if (!lazyLoaded) {
            lazyLoadData()
            lazyLoaded = true
        }
    }

    /**
     * 初期化ViewModel
     */
    private fun initViewModel() {
        mViewModel = ViewModelProvider(this).get(viewModelClass())
    }

    abstract fun viewModelClass(): Class<VM>

    open fun observe() {
    }

    abstract fun initView()
    open fun initData() {}
    open fun setListener() {}

    /**
     * データを遅延ロード
     */
    open fun lazyLoadData() {}
}