package com.mix.mvvm.ui.search

import android.content.Intent
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.appcompat.widget.SearchView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.mix.mvvm.R
import com.mix.mvvm.adapter.ArticleAdapter
import com.mix.mvvm.base.BaseVmActivity
import com.mix.mvvm.data.bean.History
import com.mix.mvvm.data.bean.Hotkey
import com.mix.mvvm.data.room.AppDatabase
import com.mix.mvvm.data.room.HistoryDao
import com.mix.mvvm.databinding.ActivitySearchBinding
import com.mix.mvvm.tools.randomColor
import com.mix.mvvm.ui.detail.DetailActivity
import com.yechaoa.yutilskt.LogUtil
import com.yechaoa.yutilskt.TimeUtil
import com.yechaoa.yutilskt.ToastUtil
import com.yechaoa.yutilskt.YUtils
import com.zhy.view.flowlayout.FlowLayout
import com.zhy.view.flowlayout.TagAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect
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

    private lateinit var mEditText: EditText

    override fun viewModelClass(): Class<SearchViewModel> = SearchViewModel::class.java

    override fun initView() {
        initRecyclerView()
        setSupportActionBar(mBinding.toolbar)
    }

    override fun initData() {
        super.initData()
        mHistoryDao = AppDatabase.getInstance(this).historyDao()
        mViewModel.getHotkey()
        getSearchHistory()
    }

    override fun observe() {
        super.observe()
        mViewModel.hotkeyList.observe(this, {
            setHotkey(it)
        })

        mViewModel.articleList.observe(this, {
            mBinding.llHotkey.visibility = View.GONE
            mBinding.recyclerView.visibility = View.VISIBLE

            mCurrentSize = it.size
            if (0 == mCurrentPage) {
                if (it.isEmpty()) {
                    mArticleAdapter.setList(null)
                    mArticleAdapter.setEmptyView(R.layout.layout_empty_view)
                } else {
                    mArticleAdapter.setList(it)
                }
            } else {
                mArticleAdapter.addData(it)
                mArticleAdapter.loadMoreModule.loadMoreComplete()
            }


        })

        mViewModel.collectState.observe(this, {
            if (it) {
                ToastUtil.show("收藏成功")
                mArticleAdapter.data[mPosition].collect = true
                mArticleAdapter.notifyItemChanged(mPosition)
            }
        })

        mViewModel.unCollectState.observe(this, {
            if (it) {
                ToastUtil.show("取消成功")
                mArticleAdapter.data[mPosition].collect = false
                mArticleAdapter.notifyItemChanged(mPosition)
            }
        })
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
        lifecycleScope.launch(Dispatchers.IO) {
           mHistoryDao.getAll().collect {
               withContext(Dispatchers.Main) {
                   if (it.isNotEmpty()) {
                       mBinding.llHistory.visibility = View.VISIBLE
                       setHistory(it)
                   } else {
                       mBinding.llHistory.visibility = View.GONE
                   }
               }
           }
        }
    }

    private fun setHotkey(list: MutableList<Hotkey>) {
        mBinding.flowLayoutHot.adapter = object : TagAdapter<Hotkey>(list) {
            override fun getView(parent: FlowLayout?, position: Int, t: Hotkey): View {
                val tvTag = LayoutInflater.from(this@SearchActivity).inflate(
                    R.layout.item_navi, mBinding.flowLayoutHot, false
                ) as TextView
                tvTag.text = t.name
                tvTag.setTextColor(randomColor())
                return tvTag
            }

        }

        //クリック
        mBinding.flowLayoutHot.setOnTagClickListener { _, position,_  ->

            YUtils.closeSoftKeyboard()
            mKey = list[position].name

            mEditText.setText(mKey)
            saveSearchHistory(mKey)
            mCurrentPage = 0 //重置分页，避免二次加载分页混乱
            mViewModel.getArticleList(mCurrentPage, mKey)
            return@setOnTagClickListener true

        }
    }


    private fun setHistory(list: List<History>) {
        mBinding.flowLayoutHistory.adapter = object : TagAdapter<History>(list) {
            override fun getView(parent: FlowLayout, position: Int, s: History): View {
                val tvTag = LayoutInflater.from(this@SearchActivity).inflate(
                   R.layout.item_navi, mBinding.flowLayoutHistory, false
                ) as TextView
                tvTag.text = s.name
                tvTag.setTextColor(randomColor())
                return tvTag
            }
        }
        //设置点击事件
        mBinding.flowLayoutHistory.setOnTagClickListener { _, position, _ ->
            YUtils.closeSoftKeyboard()
            mKey = list[position].name!!
            //填充搜索框
            mEditText.setText(mKey)
            saveSearchHistory(mKey)
            mCurrentPage = 0 //重置分页，避免二次加载分页混乱
            mViewModel.getArticleList(mCurrentPage, mKey)
            return@setOnTagClickListener true
        }
    }

    private fun saveSearchHistory(text: String) {
        MainScope().launch(Dispatchers.IO) {
            val id = mHistoryDao.queryIdByName(text)
            if (null == id) {
                mHistoryDao.insert(History(null, text, TimeUtil.dateAndTime))
            } else {
                mHistoryDao.update(History(id, text, TimeUtil.dateAndTime))
            }
        }
    }

    /**
     * SearchView追加
     */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)

        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView

        searchView.apply {
            setIconifiedByDefault(true)
            isSubmitButtonEnabled = true

            imeOptions = EditorInfo.IME_ACTION_SEARCH

            isIconified = false
            //フォーカス
            isFocusable = true
            requestFocusFromTouch()
            //ヒット
            queryHint = "検索内容"

        }

        mEditText = searchView.findViewById(androidx.appcompat.R.id.search_src_text) as EditText
        mEditText.setHintTextColor(ContextCompat.getColor(this, R.color.white30))
        mEditText.setTextColor(ContextCompat.getColor(this, R.color.white30))

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String): Boolean {
                LogUtil.i("aaa", "搜索内容===$query")
                mKey = query
                saveSearchHistory(mKey)
                mCurrentPage = 0 //重置分页，避免二次加载分页混乱
                //搜索请求
                mViewModel.getArticleList(mCurrentPage, mKey)
                //清除焦点，收软键盘
                searchView.clearFocus()
                return false
            }

            override fun onQueryTextChange(query: String): Boolean {
                mBinding.llHotkey.visibility = View.VISIBLE
                mBinding.recyclerView.visibility = View.GONE
                return false
            }

        })
        return super.onCreateOptionsMenu(menu)
    }

    override fun setListener() {
        super.setListener()
        mBinding.toolbar.setNavigationOnClickListener {
            super.onBackPressed()
        }

        mBinding.ivDelete.setOnClickListener {
            cleanHistory()
            mBinding.llHistory.visibility = View.GONE
        }
    }

    private fun cleanHistory() {
        MainScope().launch(Dispatchers.IO) {
            mHistoryDao.deleteAll()
        }
    }

}