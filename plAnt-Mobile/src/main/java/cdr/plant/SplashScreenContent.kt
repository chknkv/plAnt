package cdr.plant

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.hapticfeedback.HapticFeedback
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cdr.corecompose.theming.PlAntTokens
import cdr.corecompose.theming.getThemedColor
import kotlinx.coroutines.delay

/**
 * Контент для кастомного Splash Screen
 *
 * @param launchNextScreen лямбда запуска следующего экрана
 *
 * @author Alexandr Chekunkov
 */
@Composable
internal fun SplashScreenContent(launchNextScreen: () -> Unit) {
    val hapticFeedback = LocalHapticFeedback.current
    var isNeedAnimateIconSize by remember { mutableStateOf(false) }
    val iconRotation = remember { Animatable(ICON_DEFAULT_ROTATION_ANGLE) }
    val iconSize by animateDpAsState(
        targetValue = if (isNeedAnimateIconSize) 256.dp else 128.dp,
        animationSpec = tween(durationMillis = ICON_ANIMATION_DURATION, easing = FastOutLinearInEasing),
        label = ICON_ANIMATION_LABEL
    )

    LaunchedEffect(Unit) { isNeedAnimateIconSize = true }
    LaunchedEffect(Unit) { rotateIconAnim(iconRotation, hapticFeedback) }
    LaunchedEffect(true) {
        delay(1850)
        launchNextScreen.invoke()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(PlAntTokens.Background0.getThemedColor()),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier
                .size(iconSize)
                .rotate(iconRotation.value)
                .clip(CircleShape),
            painter = painterResource(id = R.drawable.ic_application),
            contentDescription = null
        )
    }
}

private suspend fun rotateIconAnim(
    iconRotation: Animatable<Float, AnimationVector1D>,
    hapticFeedback: HapticFeedback
) {
    iconRotation.animateTo(
        targetValue = -ICON_ROTATION_ANGLE,
        animationSpec = tween(durationMillis = ICON_ROTATION_DURATION, easing = LinearEasing)
    )
    hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)

    iconRotation.animateTo(
        targetValue = ICON_ROTATION_ANGLE,
        animationSpec = tween(durationMillis = ICON_ROTATION_DURATION, easing = LinearEasing)
    )
    hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)

    iconRotation.animateTo(
        targetValue = ICON_DEFAULT_ROTATION_ANGLE,
        animationSpec = tween(durationMillis = ICON_ROTATION_DURATION, easing = LinearEasing)
    )
    hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)
}

private const val ICON_ROTATION_ANGLE = 15f
private const val ICON_DEFAULT_ROTATION_ANGLE = 0f
private const val ICON_ROTATION_DURATION = 400
private const val ICON_ANIMATION_DURATION = 1850
private const val ICON_ANIMATION_LABEL = "ICON_ANIMATION_LABEL"
