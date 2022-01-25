package com.mix.mvvm.router

/**
 * @Date 執筆時間 2022/01/24 23:22
 * @Author Caden
 * @Description
 * @Email code_legend@163.com
 * @Vesion 1.0
 */

const val ROUTER_GROUP_COMMON = "/common"

const val ROUTER_GROUP_SEARCH = "/search"

const val ROUTER_GROUP_USER = "/user"

/** ホーム画面 [MainActivity] */
const val ROUTER_PATH_MAIN = "$ROUTER_GROUP_COMMON/main"

const val ROUTER_PATH_SETTING = "$ROUTER_GROUP_COMMON/setting"

/** ログイン画面 [LoginActivity] */
const val ROUTER_PATH_LOGIN = "$ROUTER_GROUP_USER/login"