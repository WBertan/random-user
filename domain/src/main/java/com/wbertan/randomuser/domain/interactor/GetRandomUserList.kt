package com.wbertan.randomuser.domain.interactor

import com.wbertan.randomuser.domain.RandomUser
import com.wbertan.randomuser.domain.executor.ThreadExecutor
import com.wbertan.randomuser.domain.repository.RandomUserRepository
import io.reactivex.Observable

class GetRandomUserList(private val repository: RandomUserRepository,
                        subscribeThread: ThreadExecutor,
                        observeThread: ThreadExecutor)
  : UseCase<List<RandomUser>, Void>(subscribeThread, observeThread) {
  override fun buildUseCaseObservable(params: Void?): Observable<List<RandomUser>> =
      repository
          .getAll()
          .toObservable()
}