package com.instaclient.flow.post_detail

import com.instaclient.model.Post
import com.instaclient.mvp.BaseMvpPresenter
import com.instaclient.mvp.BaseMvpView

object TagResultContract {

    interface View : BaseMvpView {
        fun showPost(post: MutableList<Post>)
    }

    interface Presenter : BaseMvpPresenter<View> {
        fun loadPosts(nameTag: String, accessToken: String)
    }
}