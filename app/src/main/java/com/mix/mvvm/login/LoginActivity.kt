package com.mix.mvvm.login

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mix.mvvm.base.BaseVmActivity
import com.mix.mvvm.databinding.ActivityLoginBinding
import com.mix.mvvm.ext.setOnclickNoRepeat

/**
 * @Date 執筆時間 2022/01/06 21:53
 * @Author Caden
 * @Description
 * @Email code_legend@163.com
 * @Vesion 1.0
 */
class LoginActivity : BaseVmActivity<ActivityLoginBinding, LoginViewModel>(ActivityLoginBinding :: inflate) {
    override fun viewModelClass(): Class<LoginViewModel> = LoginViewModel::class.java

    override fun initView() {
        super.initView()
        setText()
    }

    override fun setListener() {
        mBinding.tvRegister.setOnclickNoRepeat {

        }
    }

    override fun observe() {
        super.observe()
        mViewModel.loginState.observe(this, {
            if (it){
                Toast.makeText(this,"ログイン成功",Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this,"ログイン失敗",Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setText() {

    }
}