package com.mix.mvvm.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.CountDownTimer
import com.gyf.immersionbar.ImmersionBar
import com.mix.mvvm.R
import com.mix.mvvm.base.BaseActivity
import com.mix.mvvm.common.Config
import com.mix.mvvm.databinding.ActivitySplashBinding
import com.mix.mvvm.ext.setOnClickNoRepeat
import com.mix.mvvm.ui.MainActivity
import com.mix.mvvm.ui.login.LoginActivity
import com.yechaoa.yutilskt.SpUtil

/**
 * @Date 執筆時間 2022/01/29 18:33
 * @Author Caden
 * @Description
 * @Email code_legend@163.com
 * @Vesion 1.0
 */
class SplashActivity : BaseActivity<ActivitySplashBinding>(ActivitySplashBinding::inflate) {

    private lateinit var countDownTimer: CountDownTimer

    override fun initialize() {
        super.initialize()
        //アプリbarが透明にする
        ImmersionBar.with(this).fitsSystemWindows(true).barColor(R.color.transparent).init()
        setListener()
    }

    override fun onResume() {
        super.onResume()
        startCountDown()
    }

    private fun setListener() {
        mBinding.tvSkip.setOnClickNoRepeat {
            if (this::countDownTimer.isInitialized) countDownTimer.cancel()
            checkLogin()
        }
    }

    private fun startCountDown() {
         countDownTimer = object : CountDownTimer(1000, 1000) {
             override fun onTick(millisUntilFinished: Long) {
             }

             override fun onFinish() {
                 countDownTimer.cancel()
                 checkLogin()
             }
         }
        countDownTimer.start()
    }

    private fun checkLogin() {
        return if (!SpUtil.getBoolean(Config.IS_LOGIN)) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        } else {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }


}