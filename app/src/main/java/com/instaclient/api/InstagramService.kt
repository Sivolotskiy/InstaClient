package com.instaclient.api

import com.instaclient.model.SearchResponse
import com.instaclient.model.TagRecentResponse
import retrofit2.http.*
import rx.Observable

interface InstagramService {

    @GET(ApiSettings.RECENT_PHOTOS)
    fun getPhotosByTag(@Path(ApiSettings.PATH_TAG_NAME) tagName: String,
                       @Query(ApiSettings.ACCESS_TOKEN) accessToken: String): Observable<TagRecentResponse>

    @GET(ApiSettings.SEARCH_TAG)
    fun getTagsByName(@Query(ApiSettings.TAG) tagName: String,
                      @Query(ApiSettings.ACCESS_TOKEN) accessToken: String): Observable<SearchResponse>
}