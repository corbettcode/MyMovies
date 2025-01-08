package com.corbettcode.mymovies.ui.common

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.AwaitPointerEventScope
import androidx.compose.ui.input.pointer.PointerEvent
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import platform.UIKit.UIScreen

internal actual val CurrentPlatformTarget = PlatformTarget.Android

@OptIn(ExperimentalComposeUiApi::class)
@Composable
actual fun getScreenWidth(): Dp = LocalWindowInfo.current.containerSize.width.pxToPoint().dp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
actual fun getScreenHeight(): Dp = LocalWindowInfo.current.containerSize.height.pxToPoint().dp

fun Int.pxToPoint(): Double = this.toDouble() / UIScreen.mainScreen.scale

@Composable
internal actual fun Modifier.onPointerEventCommon(
    eventType: PointerEventType,
    pass: PointerEventPass,
    onEvent: AwaitPointerEventScope.(event: PointerEvent) -> Unit
): Modifier = this



@Composable
internal actual fun VerticalScrollbarCommon(
    adapter: Any,
    modifier: Modifier,
    reverseLayout: Boolean,
    style: Any?,
    interactionSource: MutableInteractionSource
) = Unit



@Composable
internal actual fun rememberScrollbarAdapterCommon(scrollState: LazyGridState): Any = Any()
@Composable
internal actual fun rememberScrollbarAdapterCommon(scrollState: LazyListState): Any = Any()

internal actual fun defaultScrollbarStyleCommon(background: String?): Any = Any()

@Composable
internal actual fun HorizontalScrollbarCommon(
    adapter: Any,
    modifier: Modifier,
    reverseLayout: Boolean,
    style: Any,
    interactionSource: MutableInteractionSource
) = Unit


/**
 * Ios
 *
 *
 */