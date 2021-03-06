package com.mix.mvvm.ui.login

import com.mix.mvvm.base.BaseRepository

/**
 * @Date 執筆時間 2022/01/06 22:07
 * @Author Caden
 * @Description
 * @Email code_legend@163.com
 * @Vesion 1.0
 */
class LoginRepository : BaseRepository() {

    suspend fun login(userName: String?, passWord: String?) = apiService().login(userName, passWord)

}