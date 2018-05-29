package com.wbertan.randomuser.domain.repository

import com.wbertan.randomuser.domain.RandomUser
import io.reactivex.Single

interface RandomUserRepository {
  fun get(userId: RandomUser.Id): Single<RandomUser>
  fun getAll(): Single<List<RandomUser>>
}