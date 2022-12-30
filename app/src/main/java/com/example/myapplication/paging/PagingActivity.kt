package com.example.myapplication.paging

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityPagingBinding
import com.example.myapplication.ext.inflate
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class PagingActivity : AppCompatActivity() {

    private val binding:ActivityPagingBinding by inflate()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.rvPaging.apply {

        }
        val viewModel by viewModels<ArticleViewModel>(
            factoryProducer = { Injection.provideViewModelFactory(owner = this) }
        )

        val items = viewModel.items
        val articleAdapter = ArticleAdapter()

        binding.bindAdapter(articleAdapter = articleAdapter)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                items.collectLatest {
                    articleAdapter.submitData(it)
                }
            }
        }
    }
}

private fun ActivityPagingBinding.bindAdapter(
    articleAdapter: ArticleAdapter
) {
    rvPaging.adapter = articleAdapter
    rvPaging.layoutManager = LinearLayoutManager(rvPaging.context)
    val decoration = DividerItemDecoration(rvPaging.context, DividerItemDecoration.VERTICAL)
    rvPaging.addItemDecoration(decoration)
}