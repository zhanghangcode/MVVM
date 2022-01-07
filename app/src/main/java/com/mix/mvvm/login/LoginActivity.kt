package com.mix.mvvm.login

import androidx.appcompat.app.AppCompatActivity
import com.mix.mvvm.base.BaseVmActivity
import com.mix.mvvm.databinding.ActivityLoginBinding

/**
 * @Date 執筆時間 2022/01/06 21:53
 * @Author Caden
 * @Description
 * @Email code_legend@163.com
 * @Vesion 1.0
 */
class LoginActivity : BaseVmActivity<ActivityLoginBinding, LoginViewModel>(ActivityLoginBinding :: inflate) {
    override fun viewModelClass(): Class<LoginViewModel> = LoginViewModel::class.java

}