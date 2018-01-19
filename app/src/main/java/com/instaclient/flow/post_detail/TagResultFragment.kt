package com.instaclient.flow.post_detail

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.instaclient.R
import com.instaclient.flow.main_activity.MainActivity
import com.instaclient.model.Post
import com.instaclient.mvp.BaseMvpFragment
import kotlinx.android.synthetic.main.fragment_tag_detail.*
import java.util.ArrayList

class TagResultFragment : BaseMvpFragment<TagResultContract.View,
        TagResultContract.Presenter>(),
        TagResultContract.View {

    override var mPresenter: TagResultContract.Presenter = TagDetailsPresenter()

    private var mAdapter: PhotosAdapter? = null

    companion object {
        private const val NAME = "name"

        fun newIntent(name: String): Bundle =
                Bundle().apply {
                    putString(NAME, name)
                }
    }

    override fun showPost(post: MutableList<Post>) {
        hideProgress()
        mAdapter?.addPhotos(post)
        mAdapter?.notifyDataSetChanged()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater?.inflate(R.layout.fragment_tag_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val nameTag = arguments?.getString(NAME)

        setUpRecyclerView()

        nameTag?.let {
            showProgress()
            mPresenter.loadPosts(it, getString(R.string.access_token))
        }

        with(toolbar_detail){
            title = nameTag
            (activity as MainActivity).setSupportActionBar(toolbar_detail)
            (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun setUpRecyclerView() {
        mAdapter = PhotosAdapter(ArrayList(), {})
        recyclerViewPosts.layoutManager = LinearLayoutManager(context, GridLayoutManager.VERTICAL, false)
        recyclerViewPosts.adapter = mAdapter
    }

    private fun showProgress() {
        recyclerViewPosts.visibility = View.GONE
        progress_bar.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        recyclerViewPosts.visibility = View.VISIBLE
        progress_bar.visibility = View.GONE

    }

    override fun showError(error: String?) {
        super.showError(error)
        progress_bar.visibility = View.GONE
    }
}