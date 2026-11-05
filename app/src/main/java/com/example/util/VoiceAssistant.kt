package com.example.util

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.speech.tts.TextToSpeech
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.model.AppLanguage
import java.util.Locale

class VoiceAssistant(
    private val context: Context,
    private val onCommandRecognized: (String) -> Unit
) : RecognitionListener {

    private var speechRecognizer: SpeechRecognizer? = null
    private var textToSpeech: TextToSpeech? = null
    var isTtsReady by mutableStateOf(false)
        private set

    var isListening by mutableStateOf(false)
        private set

    var lastSpokenText by mutableStateOf("")
        private set

    var speechError by mutableStateOf<String?>(null)
        private set

    init {
        try {
            if (SpeechRecognizer.isRecognitionAvailable(context)) {
                speechRecognizer = SpeechRecognizer.createSpeechRecognizer(context).apply {
                    setRecognitionListener(this@VoiceAssistant)
                }
            }
        } catch (_: Exception) {
            speechRecognizer = null
        }

        try {
            textToSpeech = TextToSpeech(context) { status ->
                if (status == TextToSpeech.SUCCESS) {
                    isTtsReady = true
                }
            }
        } catch (_: Exception) {
            isTtsReady = false
        }
    }

    fun startListening(language: AppLanguage) {
        val recognizer = speechRecognizer ?: run {
            speechError = "Speech recognition is not available on this device."
            return
        }

        val langCode = when (language) {
            AppLanguage.ENGLISH -> "en-IN"
            AppLanguage.HINDI -> "hi-IN"
            AppLanguage.TELUGU -> "te-IN"
        }

        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, langCode)
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE, langCode)
            putExtra(RecognizerIntent.EXTRA_ONLY_RETURN_LANGUAGE_PREFERENCE, langCode)
            putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 3)
            putExtra(RecognizerIntent.EXTRA_PROMPT, "Say a recipe command...")
        }

        try {
            speechError = null
            isListening = true
            recognizer.startListening(intent)
        } catch (e: Exception) {
            isListening = false
            speechError = e.localizedMessage
        }
    }

    fun stopListening() {
        try {
            speechRecognizer?.stopListening()
        } catch (_: Exception) {}
        isListening = false
    }

    fun speak(text: String, language: AppLanguage) {
        val tts = textToSpeech ?: return
        if (!isTtsReady) return

        val locale = when (language) {
            AppLanguage.ENGLISH -> Locale("en", "IN")
            AppLanguage.HINDI -> Locale("hi", "IN")
            AppLanguage.TELUGU -> Locale("te", "IN")
        }

        try {
            tts.language = locale
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, "rasoi_step_${System.currentTimeMillis()}")
        } catch (_: Exception) {}
    }

    fun stopSpeaking() {
        try {
            textToSpeech?.stop()
        } catch (_: Exception) {}
    }

    fun destroy() {
        try {
            speechRecognizer?.destroy()
            textToSpeech?.stop()
            textToSpeech?.shutdown()
        } catch (_: Exception) {}
    }

    override fun onReadyForSpeech(params: Bundle?) {
        isListening = true
    }

    override fun onBeginningOfSpeech() {}
    override fun onRmsChanged(rmsdB: Float) {}
    override fun onBufferReceived(buffer: ByteArray?) {}

    override fun onEndOfSpeech() {
        isListening = false
    }

    override fun onError(error: Int) {
        isListening = false
        speechError = when (error) {
            SpeechRecognizer.ERROR_NO_MATCH -> "No command heard. Please try again."
            SpeechRecognizer.ERROR_NETWORK -> "Network issue while processing speech."
            SpeechRecognizer.ERROR_AUDIO -> "Audio recording error."
            else -> "Speech recognition error code: $error"
        }
    }

    override fun onResults(results: Bundle?) {
        isListening = false
        val matches = results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
        if (!matches.isNullOrEmpty()) {
            val spoken = matches[0]
            lastSpokenText = spoken
            onCommandRecognized(spoken)
        }
    }

    override fun onPartialResults(partialResults: Bundle?) {}
    override fun onEvent(eventType: Int, params: Bundle?) {}
}
