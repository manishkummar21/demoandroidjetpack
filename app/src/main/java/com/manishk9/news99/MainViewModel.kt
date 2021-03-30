package com.manishk9.news99

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manishk9.news99.entities.HeadLine
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.Flow
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepo: MainRepo) : ViewModel() {

    fun getTopHeadlines(): MutableLiveData<ResultWrapper<List<HeadLine>>> {
        val response = MutableLiveData<ResultWrapper<List<HeadLine>>>()
        viewModelScope.launch {
            response.postValue(ResultWrapper.Loading)
            mainRepo.getTopHeadLines()
                .flatMapLatest {
                    if (it.isSuccessful && it.body() != null)
                        mainRepo.insertHeadLines(it.body()!!.articles)
                    else
                        emptyFlow()
                }
                .catch { e ->
                    when (e) {
                        is IOException -> {
                            mainRepo.getTopHeadlinesFromDB().collect {
                                emit(it)
                            }
                        }
                        is HttpException -> response.postValue(
                            ResultWrapper.Error(
                                Utils.convertErrorBody(
                                    e
                                )
                            )
                        )
                        else -> response.postValue(ResultWrapper.Error(null))
                    }
                }
                .flowOn(Dispatchers.IO)
                .collect {
                    response.postValue(ResultWrapper.Success(it))
                }
        }
        return response
    }

    fun getHeadLineDetailsByID(id: Int): MutableLiveData<HeadLine> {
        val response = MutableLiveData<HeadLine>()
        viewModelScope.launch {
            mainRepo.getHeadLineDetailsByID(id)
                .flowOn(Dispatchers.IO)
                .catch {
                    print("error")
                }
                .collect {
                    response.postValue(it)
                }
        }
        return response
    }
}