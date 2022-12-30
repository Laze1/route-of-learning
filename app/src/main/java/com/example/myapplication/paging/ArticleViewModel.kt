package com.example.myapplication.paging

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow

private const val ITEMS_PER_PAGE = 10

class ArticleViewModel(
    private val repository: ArticleRepository,
) : ViewModel() {

    val items: Flow<PagingData<Article>> = Pager(
        config = PagingConfig(
            pageSize = ITEMS_PER_PAGE,
            enablePlaceholders = false
        ), pagingSourceFactory = {
            repository.articlePagingSource()
        })
        .flow
        .cachedIn(viewModelScope)
}