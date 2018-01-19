package com.instaclient.flow.tag_search

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.instaclient.R
import com.instaclient.flow.main_activity.MainActivity
import com.instaclient.flow.post_detail.TagResultFragment
import com.instaclient.model.Tag
import com.instaclient.mvp.BaseMvpFragment
import kotlinx.android.synthetic.main.fragment_search_tag.*
import kotlin.collections.ArrayList

class TagSearchFragment : BaseMvpFragment<TagSearchContact.View,
        TagSearchPresenter>(),
        TagSearchContact.View {

    private var mAdapter: TagAdapter? = null
    override var mPresenter: TagSearchPresenter = TagSearchPresenter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater?.inflate(R.layout.fragment_search_tag, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        tagEditText.afterTextChanged { mPresenter.loadTags(it, getString(R.string.access_token)) }
    }

    private fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
        this.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(editable: Editable?) {
                if (!editable.toString().isEmpty()) afterTextChanged.invoke(editable.toString())
            }
        })
    }

    override fun showTags(tags: MutableList<Tag>) {
        recyclerViewTags.visibility = View.VISIBLE
        mAdapter?.addTags(tags)
        mAdapter?.notifyDataSetChanged()
    }

    private fun setUpRecyclerView() {
        mAdapter = TagAdapter(ArrayList(), { tag ->
            val fragment = TagResultFragment()
            fragment.arguments = TagResultFragment.newIntent(tag.name)
            ((activity as MainActivity).replaceFragment(fragment))
        })
        recyclerViewTags.layoutManager = LinearLayoutManager(context, GridLayoutManager.VERTICAL, false)
        recyclerViewTags.adapter = mAdapter
    }


    override fun showError(error: String?) {
        super.showError(error)
        progress_bar.visibility = View.GONE
    }
}