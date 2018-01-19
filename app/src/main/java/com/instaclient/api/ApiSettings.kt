package com.instaclient.api


object ApiSettings {
    const val PATH_TAG = "v1/tags"

    const val PATH_TAG_NAME = "tag_name"
    const val RECENT_PHOTOS = "$PATH_TAG/{$PATH_TAG_NAME}/media/recent"
    const val SEARCH_TAG = "$PATH_TAG/search"

    const val TAG = "q"
    const val ACCESS_TOKEN = "access_token"
}