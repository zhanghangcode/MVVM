package com.mix.mvvm.demo

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.mix.mvvm.R

/**
 * @Date 執筆時間 2022/01/16 10:01
 * @Author Caden
 * @Description
 * @Email code_legend@163.com
 * @Vesion 1.0
 */
class TestActivity : AppCompatActivity() {

    var count = 0
    private val viewModel: TestViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.test)
//        viewModel = ViewModelProvider(this).get(TestViewModel::class.java)



        val textView = findViewById<TextView>(R.id.text)
        val button = findViewById<Button>(R.id.btn)

//        textView.text = viewModel.count.toString()
        viewModel.count.observe(this,{
            textView.text = it.toString()
        })

        button.setOnClickListener(View.OnClickListener {
            viewModel.updateCount()
//            textView.text = viewModel.count.toString()
        })
    }
}