package com.wbertan.randomuser.domain.interactor

import com.wbertan.randomuser.domain.RandomUser
import com.wbertan.randomuser.domain.executor.ThreadExecutor
import com.wbertan.randomuser.domain.repository.RandomUserRepository
import io.reactivex.Observable

class GetRandomUser(private val repository: RandomUserRepository,
                    subscribeThread: ThreadExecutor,
                    observeThread: ThreadExecutor)
  : UseCase<RandomUser, GetRandomUser.Params>(subscribeThread, observeThread) {
  override fun buildUseCaseObservable(params: GetRandomUser.Params?): Observable<RandomUser> {
    return when (params?.userId) {
        null -> Observable.error(IllegalArgumentException("Param cannot be null."))
        else -> repository
            .get(params.userId)
            .toObservable()
      }
  }

  class Params(val userId: RandomUser.Id)
}