package com.example.mymovieapplication_jetpackcompose.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.*
import com.example.mymovieapplication_jetpackcompose.data.localdatabase.MovieEntity
import com.example.mymovieapplication_jetpackcompose.data.repositories.isSuccess
import com.example.mymovieapplication_jetpackcompose.domain.usecases.*
import com.example.mymovieapplication_jetpackcompose.navigation.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val addFavouriteMovieUseCase: AddFavouriteMovieUseCase,
    private val addNewMovieUseCase: AddNewMovieUseCase,
    private val deleteMovieUseCase: DeleteMovieUseCase,
    private val fetchMovieDetailsUseCase: FetchMovieDetailsUseCase,
    private val fetchMoviesListUseCase: FetchMoviesListUseCase,
    private val getFavouriteMovieUseCase: GetFavouriteMovieUseCase,
    private val getMovieDataUseCase: GetMovieDataUseCase,
    private val removeFavouriteMovieUseCase: RemoveFavouriteMovieUseCase,
    private val searchMovieByIdUseCase: SearchMovieByIdUseCase,
    private val setMovieDataBaseUseCase: SetMovieDataBaseUseCase,
    private val getAddressUseCase: GetAddressUseCase,
    private val getPosterAddressUseCase: GetPosterAddressUseCase,
    private val saveThemeUseCase: SaveThemeUseCase,
    private val getThemeStatusUseCase: GetThemeStatusUseCase,
) : ViewModel() {
    var favouriteList = listOf<MovieEntity>()
    var movieDataList = listOf<MovieEntity>()

    private var _movieDetails = MutableLiveData<MovieEntity>()
    val movieDetailsObserver: LiveData<MovieEntity> = _movieDetails

    val movieList = mutableStateListOf<MovieEntity>()
    var darkTheme by mutableStateOf(getDarkThemeStatus())

    val movieListObserver: LiveData<List<MovieEntity>> =
        getMovieDataUseCase.invoke().distinctUntilChanged().asLiveData()

    val movieFavListObserver: LiveData<List<MovieEntity>> =
        getFavouriteMovieUseCase.invoke().distinctUntilChanged().asLiveData()

    private var _searchRes = MutableLiveData<MovieEntity>()
    val searchResObserver: LiveData<MovieEntity> = _searchRes

    private var _toastRes = MutableLiveData<String?>()
    val toastResObserver: LiveData<String?> = _toastRes


    fun getMovieListAPI() {
        viewModelScope.launch {
            val result = fetchMoviesListUseCase.invoke()
            if (result.status.isSuccess)
                _toastRes.value = ""
            else
                _toastRes.value = result.message
        }
    }

    fun searchMovieById(movieDetails: Long) {
        viewModelScope.launch {
            searchMovieByIdUseCase.invoke(movieDetails).distinctUntilChanged().collect {
                _searchRes.value = it
            }
        }
    }

    fun setResponse(response: List<MovieEntity>?) {
        viewModelScope.launch {
            setMovieDataBaseUseCase.invoke(response)
        }

    }

    fun removeFavouriteMovie(movie: MovieEntity) {
        viewModelScope.launch {
            removeFavouriteMovieUseCase.invoke(movie)
        }

    }

    fun addFavouriteMovie(movie: MovieEntity) {
        viewModelScope.launch {
            addFavouriteMovieUseCase.invoke(movie)
        }

    }

    fun deleteMovie(movie: MovieEntity) {
        viewModelScope.launch {
            deleteMovieUseCase.invoke(movie)
        }

    }

    fun fetchMovieDetails(movieDetails: Long) {
        viewModelScope.launch {
            val response = fetchMovieDetailsUseCase.invoke(movieDetails)
            if (response.status.isSuccess) {
                response.data?.let {
                    _movieDetails.postValue(MovieEntity(it.title.toInt().hashCode(), it))
                    _toastRes.value = ""
                }
            } else {
                _toastRes.value = response.message
            }
        }
    }

    fun setMovieDetail(item: MovieEntity) {
        _movieDetails.value = item
    }

    fun addNewMovie(movie: MovieEntity) {
        viewModelScope.launch {
            addNewMovieUseCase.invoke(movie)
        }

    }

    fun getAddress(url: String) = getAddressUseCase.invoke(url)

    fun getPosterAddress() = getPosterAddressUseCase.invoke()

    fun saveDarkThemeStatus(enabled:Boolean){
        saveThemeUseCase.invoke(enabled)
    }

    fun getDarkThemeStatus(): Boolean {
        return getThemeStatusUseCase.invoke()
    }
}
