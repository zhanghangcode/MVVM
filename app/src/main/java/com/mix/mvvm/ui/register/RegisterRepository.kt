package com.mix.mvvm.ui.register

import com.mix.mvvm.base.BaseRepository

/**
 * @Date 執筆時間 2022/02/05 14:22
 * @Author Caden
 * @Description
 * @Email code_legend@163.com
 * @Vesion 1.0
 */
class RegisterRepository : BaseRepository() {

    suspend fun register(username: String, password: String, repassword: String) =
        apiService().register(username, password, repassword)

}