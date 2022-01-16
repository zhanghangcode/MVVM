package com.mix.mvvm.demo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * @Date 執筆時間 2022/01/16 10:02
 * @Author Caden
 * @Description
 * @Email code_legend@163.com
 * @Vesion 1.0
 */
class TestViewModel : ViewModel() {

//    var count = 0
   private  var _count = MutableLiveData<Int>().apply {
        value = 0
}
    var count: LiveData<Int> = _count

    fun updateCount(){
        _count.value = _count.value?.plus(1)
    }

}