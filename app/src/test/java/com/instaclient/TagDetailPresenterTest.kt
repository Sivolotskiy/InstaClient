package com.instaclient

import com.instaclient.flow.post_detail.TagDetailsPresenter
import com.instaclient.flow.post_detail.TagResultContract
import com.instaclient.model.Post
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class TagDetailPresenterTest : BasePresenterTest() {

    @Mock
    lateinit var mockView: TagResultContract.View

    lateinit var userListPresenter: TagDetailsPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        userListPresenter = TagDetailsPresenter()
    }

    @Test
    fun testPresenter_canAttach() {
        userListPresenter.attachView(mockView)
        Assert.assertTrue(userListPresenter.isAttached())
    }

    @Test
    fun testLoadData_errorCase_showError() {
        val errorText = "Something was wrong"
        userListPresenter.attachView(mockView)
        userListPresenter.loadPosts("name", "")

        // Then
        Mockito.verify(mockView).showMessage(errorText)
    }

    @Test
    fun testLoadData_success() {
        userListPresenter.attachView(mockView)
        userListPresenter.loadPosts("travels", "1757710756.e183bfb.6fb0dcaff01d4445bdb6255069da124a")

    }

}
