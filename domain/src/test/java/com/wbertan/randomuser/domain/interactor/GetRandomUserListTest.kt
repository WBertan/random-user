package com.wbertan.randomuser.domain.interactor

import com.wbertan.randomuser.domain.RandomUser
import com.wbertan.randomuser.domain.executor.ThreadExecutor
import com.wbertan.randomuser.domain.repository.RandomUserRepository
import io.mockk.classMockk
import io.mockk.every
import io.mockk.verifyAll
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.TestScheduler
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GetRandomUserListTest {
  private var getRandomUserListUseCase: GetRandomUserList? = null

  private val mockThreadExecutor: ThreadExecutor = classMockk(ThreadExecutor::class)
  private val mockRandomUserRepository: RandomUserRepository = classMockk(RandomUserRepository::class)

  @BeforeEach
  fun setUp() {
    getRandomUserListUseCase = GetRandomUserList(mockRandomUserRepository, mockThreadExecutor,
        mockThreadExecutor)
  }

  @Test
  fun testGetPostListObservableCase() {
    val userList = listOf(classMockk(RandomUser::class))
    val testObserver = TestObserver<List<RandomUser>>()
    val testScheduler = TestScheduler()
    every { mockThreadExecutor.getScheduler() } returns testScheduler
    every { mockRandomUserRepository.getAll() } returns Single.just(userList)

    getRandomUserListUseCase!!.asObservable().subscribe(testObserver)
    testScheduler.triggerActions()
    testObserver.awaitTerminalEvent()

    verifyAll {
      mockRandomUserRepository.getAll()
      mockThreadExecutor.getScheduler()
    }

    testObserver.assertValueCount(1)
    testObserver.assertNoErrors()
    testObserver.assertComplete()
    testObserver.assertValue(userList)
  }
}