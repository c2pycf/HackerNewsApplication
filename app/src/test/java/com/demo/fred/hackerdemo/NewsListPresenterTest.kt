package com.demo.fred.hackerdemo

import com.demo.fred.hackerdemo.net.HackerClient
import com.demo.fred.hackerdemo.ui.model.NewsListModel
import com.demo.fred.hackerdemo.ui.presenter.NewsListPresenter
import com.demo.fred.hackerdemo.ui.view.NewsListFragment
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.junit.Assert
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import javax.inject.Inject


/**
 * Class: NewsListPresenterTest
 * Author: Fred Chen
 * Time: 25/02/2019
 * Runner: Junit4
 * Test: NewsListPresenter
 *
 * Description:
 * Setup and perform unit test for mainly presenter NewsListFragment
 *
 */

@RunWith(JUnit4::class)
class NewsListPresenterTest {


    private lateinit var fragment: NewsListFragment
    private lateinit var presenter: NewsListPresenter
    @Inject
    private lateinit var model: NewsListModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        model = NewsListModel(mock(HackerClient::class.java))
        fragment = mock(NewsListFragment::class.java)
        presenter = NewsListPresenter(model)

    }

    /**
     * Test model to fetch data from mock server
     */
    @Test
    fun testService() {

        model.getHackTopNews()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Assert.assertNotNull(it)
            }
    }

    /**
     * Test presenter attachView method if no errors pass
     */
    @Test
    fun testAttach() {
        presenter.attachView(fragment)
        Assert.assertNotNull(presenter.getAdapter())
    }

    @Test
    fun testStart() {
        presenter.onStart()
    }

    companion object {
        @BeforeClass
        @JvmStatic
        fun setUpClass() {
            RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        }
    }
}