package com.instaclient

import com.instaclient.flow.tag_search.TagSearchContact
import com.instaclient.flow.tag_search.TagSearchPresenter
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class SearchPresenterTests : BasePresenterTest() {

    @Mock
    lateinit var mockView: TagSearchContact.View

    lateinit var userListPresenter: TagSearchPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        userListPresenter = TagSearchPresenter()
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
        userListPresenter.loadTags("name", "")

        // Then
        Mockito.verify(mockView).showMessage(errorText)
    }

    @Test
    fun testLoadData_success() {
        userListPresenter.attachView(mockView)
        userListPresenter.loadTags("travels", "1757710756.e183bfb.6fb0dcaff01d4445bdb6255069da124a")
    }
}