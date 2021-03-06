package com.mix.mvvm.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mix.mvvm.base.BaseViewModel
import java.lang.NullPointerException

/**
 * @Date 執筆時間 2022/01/06 22:06
 * @Author Caden
 * @Description
 * @Email code_legend@163.com
 * @Vesion 1.0
 */
class LoginViewModel : BaseViewModel() {
    private val loginRepository by lazy { LoginRepository() }

    private val _loginState = MutableLiveData<Boolean>()
    val loginState: LiveData<Boolean> = _loginState
    fun login(username: String?, password: String?) {
        val job = launch(
            block = {
                val loginData = loginRepository.login(username, password)
                _loginState.value = 0 == loginData.code()
            },
            error = {
                if (it is NullPointerException) {
                    _loginState.value = false
                }
            },
            cancel = {},
            false
        )
    }

}