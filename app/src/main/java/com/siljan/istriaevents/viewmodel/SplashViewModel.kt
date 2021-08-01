package com.siljan.istriaevents.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.siljan.istriaevents.mvibase.MviViewModel
import com.siljan.istriaevents.ui.splash.SplashReducer.Companion.reduceState
import com.siljan.istriaevents.ui.splash.SplashViewIntent
import com.siljan.istriaevents.ui.splash.SplashViewResult
import com.siljan.istriaevents.ui.splash.SplashViewState
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

class SplashViewModel : ViewModel(), MviViewModel<SplashViewIntent, SplashViewState> {

    private val intentsSubject = PublishSubject.create<SplashViewIntent>()
    private val viewStateData = MutableLiveData<SplashViewState>()
    private val compositeDisposable = CompositeDisposable()
    private val viewState = SplashViewState(
        session = false
    )

    init {
        intentsSubject.switchMap {
            when (it) {
                SplashViewIntent.CheckAuthState -> checkIfSessionExists()
            }
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .reduceState(viewState)
            .subscribe { viewState -> viewStateData.value = viewState }
            .also { compositeDisposable.add(it) }
    }

    override fun processIntent(intent: SplashViewIntent) {
        intentsSubject.onNext(intent)
    }

    override fun states(): LiveData<SplashViewState> {
        return viewStateData
    }

    private fun checkIfSessionExists(): Observable<SplashViewResult> {
        //TODO connect with UseCase and real BL
        return Single.just(true).delaySubscription(2000, TimeUnit.MILLISECONDS)
            .flatMapObservable {
                Observable.just(
                    if (it) SplashViewResult.User
                    else SplashViewResult.Guest
                )
            }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}