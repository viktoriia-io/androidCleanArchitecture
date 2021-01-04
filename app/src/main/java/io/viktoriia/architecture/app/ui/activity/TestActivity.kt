package io.viktoriia.architecture.app.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.epoxy.EpoxyController
import io.viktoriia.architecture.app.R
import io.viktoriia.architecture.app.ui.view.ImageModel.Companion.prepareImage
import io.viktoriia.architecture.app.ui.view.TextModel.Companion.prepareText
import io.viktoriia.architecture.app.ui.view.image
import io.viktoriia.architecture.app.ui.view.text
import io.viktoriia.architecture.app.ui.viewmodel.TestViewModel
import io.viktoriia.architecture.base.viewmodel.StatefulAndroidViewModel
import kotlinx.android.synthetic.main.activivty_test_layout.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class TestActivity : AppCompatActivity() {
    private val testViewModel: TestViewModel by viewModel()
    private val tag = "TestActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activivty_test_layout)
        setupToolbar()
        activity_test_layout_recycler.apply {
            setItemSpacingPx(16)
            layoutManager = LinearLayoutManager(this@TestActivity)
            setControllerAndBuildModels(UiController())
        }

        subscribeToVm()
        testViewModel.loadTestData()
    }

    private fun subscribeToVm() {
        testViewModel.state().observe(this, Observer {
            when (it) {
                is StatefulAndroidViewModel.State.Success -> Log.d(tag, "Success")
                is StatefulAndroidViewModel.State.Empty -> Log.d(tag, "Empty")
                is StatefulAndroidViewModel.State.Error -> Log.d(tag, "Error")
                is StatefulAndroidViewModel.State.NetworkError -> Log.d(tag, "NetworkError")
                is StatefulAndroidViewModel.State.NoConnectionError -> Log.d(
                    tag,
                    "NoConnectionError"
                )
            }
        })

        testViewModel.viewEvents().observe(this, Observer {
            when (it) {
                is StatefulAndroidViewModel.ViewEvent.Progress -> Log.d(
                    tag,
                    "Progress ${it.showProgress}"
                )
                is StatefulAndroidViewModel.ViewEvent.NavigateTo -> Log.d(tag, "NavigateTo")
                is StatefulAndroidViewModel.ViewEvent.Message -> Log.d(tag, "Message")
                is StatefulAndroidViewModel.ViewEvent.Action -> Log.d(tag, "Action")
            }
        })
    }

    private fun setupToolbar() {
        title = "Some activity"
        setSupportActionBar(activity_test_layout_toolbar)
        val actionBar = this.supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(true)
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeButtonEnabled(true)
        }
    }

    inner class UiController : EpoxyController() {

        override fun buildModels() {
            text {
                id("large")
                viewState(prepareText {
                    text = "Some large text"
                    textAppearance = android.R.style.TextAppearance_Material_Large
                })
            }

            text {
                id("headline")
                viewState(prepareText {
                    text = "Some headline text"
                    textAppearance = android.R.style.TextAppearance_Material_Headline
                })
            }

            image {
                id("image")
                viewState(prepareImage {
                    url = "https://images.unsplash.com/photo-1462678522506-09a896e3d33f?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1000&q=80"
                })
            }

            text {
                id("medium")
                viewState(prepareText {
                    text = "Some medium text"
                    textAppearance = android.R.style.TextAppearance_Material_Medium
                })
            }

            text {
                id("body1")
                viewState(prepareText {
                    text = "Some body1 text"
                    textAppearance = android.R.style.TextAppearance_Material_Body1
                })
            }
        }
    }
}