package com.mix.mvvm.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

/**
 * @Date 執筆時間 2022/01/15 10:40
 * @Author Caden
 * @Description
 * @Email code_legend@163.com
 * @Vesion 1.0
 */
abstract class BaseFragment<VB : ViewBinding>(private val inflater: (LayoutInflater) -> VB) : Fragment() {
    protected open var binding: VB? = null
    protected open val mBinding get() = binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = inflater(layoutInflater)
        return mBinding.root
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        initialize()
//    }
//
//    open fun initialize() {}

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}