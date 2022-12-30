package com.example.myapplication.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import java.lang.Integer.max
import java.time.LocalDateTime


private val firstArticleCreatedTime = LocalDateTime.now()
/**
 *
 * ArticlePagingSource
 * @author Wu Xi
 * @date 2022/12/30 10:06
 *
 */
class ArticlePagingSource : PagingSource<Int, Article>() {

    private val STARTING_KEY = 0

    /*
    * load() 函数会返回一个 LoadResult。LoadResult 可以是以下类型之一：
    * LoadResult.Page（如果结果返回成功）。
    * LoadResult.Error（如果发生错误）。
    * LoadResult.Invalid（如果 PagingSource 因无法再保证其结果的完整性而应失效）。
    * */
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        // Start paging with the STARTING_KEY if this is the first load
        val start = params.key ?: STARTING_KEY
        // Load as many items as hinted by params.loadSize
        val range = start.until(start + params.loadSize)

        return LoadResult.Page(
            data = range.map { number ->
                Article(
                    // Generate consecutive increasing numbers as the article id
                    id = number,
                    title = "Article $number",
                    description = "This describes article $number",
                    created = firstArticleCreatedTime.minusDays(number.toLong())
                )
            },

            // Make sure we don't try to load items behind the STARTING_KEY
            prevKey = when (start) {
                STARTING_KEY -> null
                else -> ensureValidKey(key = range.first - params.loadSize)
            },
            nextKey = range.last + 1
        )
    }
    // The refresh key is used for the initial load of the next PagingSource, after invalidation
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        // In our case we grab the item closest to the anchor position
        // then return its id - (state.config.pageSize / 2) as a buffer
        val anchorPosition = state.anchorPosition ?: return null
        val article = state.closestItemToPosition(anchorPosition) ?: return null
        return ensureValidKey(key = article.id - (state.config.pageSize / 2))
    }
    /**
     * Makes sure the paging key is never less than [STARTING_KEY]
     */
    private fun ensureValidKey(key: Int) = max(STARTING_KEY, key)
}