package com.mix.mvvm.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mix.mvvm.base.BaseViewModel

/**
 * @Date 執筆時間 2022/02/05 14:21
 * @Author Caden
 * @Description
 * @Email code_legend@163.com
 * @Vesion 1.0
 */
class RegisterViewModel : BaseViewModel() {

    private val repository by lazy { RegisterRepository() }

    private val _registerState = MutableLiveData<Boolean>()
    val registerState: LiveData<Boolean> = _registerState

    fun register(username: String, password: String, repassword: String) {
        launch(
            block = {
                val loginData = repository.register(username, password, repassword)
                _registerState.value = 0 == loginData.code()
            }
        )
    }

}