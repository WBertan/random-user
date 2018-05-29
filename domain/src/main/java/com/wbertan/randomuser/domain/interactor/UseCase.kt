package com.wbertan.randomuser.domain.interactor

import com.wbertan.randomuser.domain.executor.ThreadExecutor
import io.reactivex.Observable

abstract class UseCase<T, Params>(private val subscribeThread: ThreadExecutor,
                                  private val observeThread: ThreadExecutor) {
  abstract fun buildUseCaseObservable(params: Params?): Observable<T>

  fun asObservable(params: Params?): Observable<T> {
    return buildUseCaseObservable(params)
        .subscribeOn(subscribeThread.getScheduler())
        .observeOn(observeThread.getScheduler())
  }

  fun asObservable(): Observable<T> = asObservable(null)
}