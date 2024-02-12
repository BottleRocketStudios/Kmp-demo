package com.br.kmpdemo.compose.ui.home

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.br.kmpdemo.compose.resources.theme.Colors
import com.br.kmpdemo.compose.resources.theme.largeCardCorner
import com.br.kmpdemo.compose.ui.utils.SHEET_PEEK_HEIGHT

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeBottomSheetScaffold(state: HomeState) {
    val sheetState = rememberBottomSheetScaffoldState()
    val isExpanded = sheetState.bottomSheetState.currentValue == SheetValue.Expanded

    BottomSheetScaffold(
        modifier = Modifier.fillMaxHeight(),
        scaffoldState = sheetState,
        content = { HomeScreen(state, isExpanded) },
        sheetPeekHeight = SHEET_PEEK_HEIGHT.dp,
        sheetDragHandle = null,
        topBar = null,
        sheetShape = if (isExpanded) RectangleShape else RoundedCornerShape(
            topStart = largeCardCorner,
            topEnd = largeCardCorner,
        ),
        sheetContainerColor = Colors.secondary,
        sheetContent = { HomeBottomSheet(state, isExpanded) })
}