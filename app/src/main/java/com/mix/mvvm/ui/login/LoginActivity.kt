package com.mix.mvvm.ui.login

import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Toast

import com.mix.mvvm.base.BaseVmActivity
import com.mix.mvvm.common.Config
import com.mix.mvvm.databinding.ActivityLoginBinding
import com.mix.mvvm.databinding.ActivityLoginBinding.inflate
import com.mix.mvvm.ext.setOnClickNoRepeat
import com.mix.mvvm.router.ROUTER_PATH_LOGIN
import com.mix.mvvm.router.ROUTER_PATH_MAIN
import com.mix.mvvm.ui.MainActivity
import com.yechaoa.yutilskt.SpUtil
import com.yechaoa.yutilskt.ToastUtil
import com.yechaoa.yutilskt.YUtils

/**
 * @Date 執筆時間 2022/01/06 21:53
 * @Author Caden
 * @Description
 * @Email code_legend@163.com
 * @Vesion 1.0
 */

class LoginActivity : BaseVmActivity<ActivityLoginBinding, LoginViewModel>(::inflate) {
    override fun viewModelClass(): Class<LoginViewModel> {
        return LoginViewModel::class.java
    }

    override fun initView() {
        super.initView()
        setText()
    }

    override fun setListener() {
        mBinding.tvRegister.setOnClickNoRepeat {

        }
        mBinding.btnLogin.setOnClickNoRepeat {
            if (!mBinding.cbServiceAgreement.isChecked) {
                Toast.makeText(this,"サービス契約とプライバシーポリシーに同意した後、ログインできます",Toast.LENGTH_SHORT).show()
            }
            login()
        }
    }
    private fun login() {
        YUtils.closeSoftKeyboard()
        mBinding.etUsername.error = null
        mBinding.etPassword.error = null

        val username = mBinding.etUsername.text.toString().trim()
        val password = mBinding.etPassword.text.toString().trim()

        var cancel = false
        var focusView: View? = null

        if (password.isEmpty()) {
            mBinding.etPassword.error = "パスワードを入力してください"
            focusView = mBinding.etPassword
            cancel = true
        }

        if (username.isEmpty()) {
            mBinding.etUsername.error = "アカウントを入力してください"
            focusView = mBinding.etPassword
            cancel = true
        }

        if (cancel) focusView?.requestFocus()
        else {
            YUtils.showLoading(this, "加载中")
            mViewModel.login(username, password)
        }


    }

    override fun observe() {
        super.observe()
        mViewModel.loginState.observe(this, {
            YUtils.hideLoading()
            SpUtil.setBoolean(Config.IS_LOGIN, it)
            if (it){
                startActivity(Intent(this, MainActivity::class.java))
            } else {
                ToastUtil.show("ログイン失敗")
            }
        })
    }

    private fun setText() {

    }
}