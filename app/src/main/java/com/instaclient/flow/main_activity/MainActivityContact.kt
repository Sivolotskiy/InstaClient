package com.instaclient.flow.main_activity

import com.instaclient.mvp.BaseMvpPresenter
import com.instaclient.mvp.BaseMvpView

class MainActivityContact {

    interface View : BaseMvpView

    interface Presenter : BaseMvpPresenter<View>
}