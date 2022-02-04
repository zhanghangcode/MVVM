package com.mix.mvvm.ui.search

import android.content.Intent
import androidx.lifecycle.lifecycleScope
import com.mix.mvvm.adapter.ArticleAdapter
import com.mix.mvvm.base.BaseVmActivity
import com.mix.mvvm.data.room.HistoryDao
import com.mix.mvvm.databinding.ActivitySearchBinding
import com.mix.mvvm.ui.detail.DetailActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * @Date 執筆時間 2022/01/24 23:56
 * @Author Caden
 * @Description
 * @Email code_legend@163.com
 * @Vesion 1.0
 */
class SearchActivity : BaseVmActivity<ActivitySearchBinding, SearchViewModel>(ActivitySearchBinding::inflate) {

    private lateinit var mArticleAdapter: ArticleAdapter
    private lateinit var mKey: String
    private var mPosition: Int = 0
    private lateinit var mHistoryDao: HistoryDao

    override fun viewModelClass(): Class<SearchViewModel> = SearchViewModel::class.java

    override fun initView() {
        initRecyclerView()
        setSupportActionBar(mBinding.toolbar)
    }

    override fun initData() {
        super.initData()
//        mHistoryDao = HistoryDatabase.getInstance(this).historyDao()

    }

    private fun initRecyclerView() {
        mArticleAdapter = ArticleAdapter().apply {
            animationEnable = true
            setOnItemClickListener { _, _, position ->
                val intent = Intent(this@SearchActivity, DetailActivity::class.java).apply {
                    putExtra(DetailActivity.WEB_URL, mArticleAdapter.data[position].link)
                    putExtra(DetailActivity.WEB_TITLE, mArticleAdapter.data[position].title)
                }
                startActivity(intent)
            }
            setOnItemChildClickListener {_, _, position ->
                mPosition = position
                if (data[position].collect) {
                    mViewModel.unCollect(data[position].id)
                } else {
                    mViewModel.collect(data[position].id)
                }
            }
            loadMoreModule.setOnLoadMoreListener {
                mBinding.recyclerView.postDelayed(
                    {
                    if (mCurrentSize < mTotalCount) {
                        mArticleAdapter.loadMoreModule.loadMoreEnd(true)
                    } else {
                        mCurrentPage++
                        mViewModel.getArticleList(mCurrentPage, mKey)
                    }
                    },500
                )
            }
        }
        mBinding.recyclerView.adapter = mArticleAdapter
    }

    private fun getSearchHistory() {
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {

            }
        }
    }
}