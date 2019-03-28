package com.hyundeee.app.findnewusers.data
import java.util.*

data class SearchResponse(
        val total_count: Int,
        val incomplete_results: Boolean,
        val items: ArrayList<User>
)
