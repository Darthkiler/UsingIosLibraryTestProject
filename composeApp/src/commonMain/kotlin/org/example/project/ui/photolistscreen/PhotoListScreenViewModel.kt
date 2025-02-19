package org.example.project.ui.photolistscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.project.domain.Photo
import org.example.project.domain.PhotoRepository

class PhotoListScreenViewModel(
    private val photoRepository: PhotoRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(PhotoListScreenUIState())
    val uiState = _uiState.asStateFlow()

    init {
        initPhotoList()
    }

    fun onRetryClick() {
        if (_uiState.value.isLoading.not()) {
            _uiState.update { it.copy(isLoading = true) }
            initPhotoList()
        }
    }

    private fun initPhotoList() {
        viewModelScope.launch {
            photoRepository.getPhotoList().let { result: Result<List<Photo>> ->
                println(result)
                result.onSuccess { list ->
                    _uiState.update {
                        it.copy(
                            list = list,
                            isLoading = false
                        )
                    }
                }
                result.onFailure {
                    _uiState.update { it.copy(
                        isLoading = false,
                        isError = true
                    ) }
                }
            }
        }
    }

    data class PhotoListScreenUIState(
        val list: List<Photo> = emptyList(),
        val isLoading: Boolean = true,
        val isError: Boolean = false,
    )
}