package com.br.kmpdemo.compose.ui

import com.br.kmpdemo.compose.ui.MainWindowControls.Companion.EMPTY_TOOLBAR_TITLE
import com.br.kmpdemo.viewmodels.MainActivityViewModel
import com.br.kmpdemo.utils.MutableStateFlowDelegate

// TODO - Move this to compose app module

interface MainWindowControls {
    var title: String
    var hideNavBar: Boolean

    fun reset()

    companion object {
        const val EMPTY_TOOLBAR_TITLE = ""
    }
}

class MainWindowControlsImplementation(viewModel: MainActivityViewModel) : MainWindowControls {
    override var title by MutableStateFlowDelegate(viewModel.appTitle)
    override var hideNavBar by MutableStateFlowDelegate(viewModel.hideNavBar)

    override fun reset() {
        title = EMPTY_TOOLBAR_TITLE
        hideNavBar = false
    }
}