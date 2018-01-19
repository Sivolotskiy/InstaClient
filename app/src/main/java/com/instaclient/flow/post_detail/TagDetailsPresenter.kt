package com.instaclient.flow.post_detail

import com.instaclient.api.GeneralErrorHandler
import com.instaclient.manager.ApiManager
import com.instaclient.mvp.BaseMvpPresenterImpl
import rx.functions.Action1


class TagDetailsPresenter : BaseMvpPresenterImpl<TagResultContract.View>(),
        TagResultContract.Presenter {
    override fun loadPosts(nameTag: String, accessToken: String) {
        ApiManager.loadTagBySearch(nameTag, accessToken)
                .doOnError { mView?.showMessage(it.toString()) }
                .subscribe(Action1 { mView?.showPost(it.data) },
                        GeneralErrorHandler(mView, true, { throwable, errorBody, isNetwork -> mView?.showMessage("Something was wrong") }))

    }
}