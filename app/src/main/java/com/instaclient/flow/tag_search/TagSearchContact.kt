package com.instaclient.flow.tag_search

import com.instaclient.model.Tag
import com.instaclient.mvp.BaseMvpPresenter
import com.instaclient.mvp.BaseMvpView
class TagSearchContact {

    interface View : BaseMvpView {
        fun showTags(tags: MutableList<Tag>)
    }

    interface Presenter : BaseMvpPresenter<View> {
        fun loadTags(nameTag: String, accessToken: String)
    }
}
