package com.instaclient.flow.main_activity

import android.app.AlertDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.MenuItem
import com.instaclient.R
import com.instaclient.flow.tag_search.TagSearchFragment
import com.instaclient.mvp.BaseMvpActivity

class MainActivity : BaseMvpActivity<MainActivityContact.View,
        MainActivityPresenter>(),
        MainActivityContact.View {

    override var mPresenter: MainActivityPresenter = MainActivityPresenter()
    private lateinit var warningDialog : AlertDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addFragment(TagSearchFragment())
        showWarningDialog()
    }

    private fun showWarningDialog()  {
        warningDialog =  AlertDialog.Builder(this)
                .setTitle(getString(R.string.warning))
                .setMessage(getString(R.string.warning_text))
                .setCancelable(false)
                .setPositiveButton(android.R.string.ok, null)
                .show()
    }

    fun getWarningDialog():AlertDialog{
        return warningDialog
    }

    private fun addFragment(fragment: Fragment){
        supportFragmentManager.inTransaction {
            add(R.id.mainContainer, fragment, fragment.tag)
        }
    }

    fun replaceFragment(fragment: Fragment){
        supportFragmentManager.inTransaction {
            replace(R.id.mainContainer, fragment, fragment.tag)
            addToBackStack(fragment.tag)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }
}
