package com.br.kmpdemo.ui

import com.br.kmpdemo.ui.MainWindowControls.Companion.EMPTY_TOOLBAR_TITLE
import com.br.kmpdemo.compose.resources.SharedRes
import com.br.kmpdemo.utils.MutableStateFlowDelegate
import dev.icerock.moko.resources.StringResource

interface MainWindowControls {
    var title: StringResource
    var hideNavBar: Boolean

    fun reset()

    companion object {
        val EMPTY_TOOLBAR_TITLE = SharedRes.strings.empty_string
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