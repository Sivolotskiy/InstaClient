package com.instaclient.flow.tag_search

import android.util.Log
import com.instaclient.manager.ApiManager
import com.instaclient.mvp.BaseMvpPresenterImpl
import rx.Subscription

class TagSearchPresenter : BaseMvpPresenterImpl<TagSearchContact.View>(), TagSearchContact.Presenter {
    var TAG = TagSearchPresenter::class.java.name
    lateinit var subs: Subscription

    override fun loadTags(nameTag: String, accessToken: String) {
        try {
            subs.unsubscribe()
        } catch (e: UninitializedPropertyAccessException) {
            //TODO add after mock method i in Log
//            Log.i(TAG, "Subscription not created" + e.toString())
        }

        subs = ApiManager.loadTagsByName(nameTag, accessToken)
                .doOnError { mView?.showError(it.toString()) }
                .subscribe({ mView?.showTags(it.data) },
                        { mView?.showMessage("Something was wrong") })
    }
}