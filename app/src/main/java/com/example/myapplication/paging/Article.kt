package com.example.myapplication.paging

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 *
 * Article
 * @author Wu Xi
 * @date 2022/12/30 10:05
 *
 */
data class Article(
    val id: Int,
    val title: String,
    val description: String,
    val created: LocalDateTime,
)

val Article.createdText: String get() = articleDateFormatter.format(created)

private val articleDateFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy")
