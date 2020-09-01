package info.firozansari.stackoverflowapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import info.firozansari.stackoverflowapp.api.ApiStatus
import info.firozansari.stackoverflowapp.api.StackoverflowRepository
import info.firozansari.stackoverflowapp.api.model.Question
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.time.ZonedDateTime
import java.util.Date
import javax.inject.Inject
import kotlin.collections.ArrayList


class MainViewModel @Inject constructor(private val stackoverflowRepository: StackoverflowRepository): ViewModel()  {

    // The most recent API response
    private val _apiStatus = MutableLiveData<ApiStatus>()
    val apiStatus: LiveData<ApiStatus>
        get() = _apiStatus

    private val _questions = MutableLiveData<List<Question>>()
    val questions: LiveData<List<Question>>
        get() = _questions

    // Holds the selected question data
    private val _navigateToSelectedQuestion = MutableLiveData<Question>()
    val navigateToSelectedQuestion: LiveData<Question>
        get() = _navigateToSelectedQuestion

    private var viewModelJob = Job() // Coroutines Job

    // A coroutine scope for that new job using the main dispatcher
    private val coroutineScope = CoroutineScope(
        viewModelJob + Dispatchers.Main )

    init {
        getUnansweredQuestion()
    }

    private fun getUnansweredQuestion() {
        // Using Coroutines
        coroutineScope.launch {
            val date: Date = Date.from(ZonedDateTime.now().minusMonths(1).toInstant())
            val time: Long = date.time
            // Will get the topic/ tagged value and min votes dynamically in future
            var getQuestionDeferred = stackoverflowRepository.getQuestions(time, 5, "android")
            try {
                _apiStatus.value = ApiStatus.LOADING
                var apiResult = getQuestionDeferred.await()
                _apiStatus.value = ApiStatus.DONE
                _questions.value = apiResult.questionsList
            } catch (e: Exception) {
                _apiStatus.value = ApiStatus.ERROR
                _questions.value = ArrayList()
            }
        }
    }

    fun displayQuestionDetails(question: Question) {
        _navigateToSelectedQuestion.value = question
    }

    fun displayQuestionDetailsComplete() {
        _navigateToSelectedQuestion.value = null
    }

    // Cancel the Coroutines Job
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}