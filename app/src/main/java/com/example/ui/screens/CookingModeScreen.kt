package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Replay
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.Strings
import com.example.model.AppLanguage
import com.example.model.Recipe
import com.example.ui.theme.CardamomCream
import com.example.ui.theme.CurryGreen
import com.example.ui.theme.SaffronDeep
import com.example.ui.theme.SaffronLight
import com.example.ui.theme.TurmericGold
import com.example.ui.theme.TurmericLight

@Composable
fun CookingModeScreen(
    recipe: Recipe,
    currentStepIndex: Int,
    currentLanguage: AppLanguage,
    isTimerRunning: Boolean,
    timerSecondsRemaining: Int,
    initialTimerDuration: Int,
    isSpeakingCurrentStep: Boolean,
    onNextStep: () -> Unit,
    onPrevStep: () -> Unit,
    onToggleTimer: () -> Unit,
    onToggleReadAloud: () -> Unit,
    onExitCooking: () -> Unit,
    onOpenVoiceAssistant: () -> Unit,
    modifier: Modifier = Modifier
) {
    BackHandler(onBack = onExitCooking)

    val step = recipe.instructions.getOrNull(currentStepIndex) ?: return
    val totalSteps = recipe.instructions.size
    val isLastStep = currentStepIndex == totalSteps - 1
    val hasTimer = initialTimerDuration > 0

    val scrollState = rememberScrollState()

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .statusBarsPadding()
            .testTag("cooking_mode_screen")
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 90.dp)
                .verticalScroll(scrollState)
        ) {
            // Header Bar: Close, Title, Voice Status
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = onExitCooking,
                    modifier = Modifier.testTag("exit_cooking_button")
                ) {
                    Icon(Icons.Default.Close, contentDescription = "Exit")
                }

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = Strings.cookingModeTitle.get(currentLanguage),
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = SaffronDeep
                        )
                    )
                    Text(
                        text = recipe.title.get(currentLanguage),
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        ),
                        maxLines = 1
                    )
                }

                // Voice Trigger
                IconButton(
                    onClick = onOpenVoiceAssistant,
                    modifier = Modifier
                        .size(38.dp)
                        .clip(CircleShape)
                        .background(SaffronLight)
                        .testTag("cooking_voice_button")
                ) {
                    Icon(
                        imageVector = Icons.Default.Mic,
                        contentDescription = "Voice Assistant",
                        tint = SaffronDeep,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            // Step Progress Bar
            Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 6.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "${Strings.step.get(currentLanguage)} ${currentStepIndex + 1} ${Strings.of.get(currentLanguage)} $totalSteps",
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = SaffronDeep
                        )
                    )
                    Text(
                        text = "${((currentStepIndex + 1) * 100) / totalSteps}%",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    )
                }
                Spacer(modifier = Modifier.height(6.dp))
                LinearProgressIndicator(
                    progress = { (currentStepIndex + 1).toFloat() / totalSteps.toFloat() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                        .clip(RoundedCornerShape(4.dp)),
                    color = SaffronDeep,
                    trackColor = SaffronLight
                )
            }

            // Voice Command Banner Helper
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .clickable { onOpenVoiceAssistant() },
                color = MaterialTheme.colorScheme.secondaryContainer
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Mic,
                        contentDescription = "Mic",
                        tint = MaterialTheme.colorScheme.onSecondaryContainer,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = Strings.voicePrompt.get(currentLanguage),
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = MaterialTheme.colorScheme.onSecondaryContainer,
                            fontWeight = FontWeight.Medium
                        )
                    )
                }
            }

            // Step Content Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = step.title.get(currentLanguage),
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    Text(
                        text = step.instruction.get(currentLanguage),
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontSize = 18.sp,
                            lineHeight = 28.sp,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    )

                    step.chefTip?.let { tip ->
                        Spacer(modifier = Modifier.height(16.dp))
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = TurmericLight,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "💡 ${Strings.chefNotes.get(currentLanguage)}: ${tip.get(currentLanguage)}",
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = Color(0xFF6B4500),
                                    lineHeight = 18.sp
                                ),
                                modifier = Modifier.padding(12.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Text-To-Speech Read Aloud Button
                    Button(
                        onClick = onToggleReadAloud,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (isSpeakingCurrentStep) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.surfaceVariant,
                            contentColor = if (isSpeakingCurrentStep) Color.White else MaterialTheme.colorScheme.onSurface
                        ),
                        shape = RoundedCornerShape(14.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(
                            imageVector = Icons.Default.VolumeUp,
                            contentDescription = "Read Aloud",
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = if (isSpeakingCurrentStep) Strings.pauseReading.get(currentLanguage) else Strings.readAloud.get(currentLanguage),
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }

            // Interactive Step Timer Widget (if step has timer)
            AnimatedVisibility(visible = hasTimer) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = if (timerSecondsRemaining <= 0 && isTimerRunning) Strings.timerDone.get(currentLanguage) else Strings.startTimer.get(currentLanguage),
                            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        val minutes = timerSecondsRemaining / 60
                        val seconds = timerSecondsRemaining % 60
                        val timeFormatted = String.format("%02d:%02d", minutes, seconds)

                        Box(
                            modifier = Modifier.size(130.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(
                                progress = {
                                    if (initialTimerDuration > 0) {
                                        timerSecondsRemaining.toFloat() / initialTimerDuration.toFloat()
                                    } else 0f
                                },
                                modifier = Modifier.size(120.dp),
                                strokeWidth = 8.dp,
                                color = SaffronDeep,
                                trackColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
                            )

                            Text(
                                text = timeFormatted,
                                style = MaterialTheme.typography.headlineMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = if (timerSecondsRemaining <= 10 && isTimerRunning) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurface
                                )
                            )
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Button(
                                onClick = onToggleTimer,
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (isTimerRunning) MaterialTheme.colorScheme.error else SaffronDeep
                                ),
                                shape = RoundedCornerShape(12.dp),
                                modifier = Modifier.testTag("toggle_timer_button")
                            ) {
                                Icon(
                                    imageVector = if (isTimerRunning) Icons.Default.Pause else Icons.Default.PlayArrow,
                                    contentDescription = "Timer action"
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = if (isTimerRunning) "Pause" else "Start Timer",
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }
        }

        // Bottom Navigation Controls: Previous / Next Step
        Surface(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 10.dp,
            shadowElevation = 8.dp
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 14.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedButton(
                    onClick = onPrevStep,
                    enabled = currentStepIndex > 0,
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp)
                        .testTag("cooking_prev_step_button"),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(Strings.prevStep.get(currentLanguage))
                }

                Button(
                    onClick = {
                        if (isLastStep) onExitCooking() else onNextStep()
                    },
                    modifier = Modifier
                        .weight(1.3f)
                        .height(50.dp)
                        .testTag("cooking_next_step_button"),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isLastStep) CurryGreen else SaffronDeep
                    )
                ) {
                    Text(
                        text = if (isLastStep) Strings.finishCooking.get(currentLanguage) else Strings.nextStep.get(currentLanguage),
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
