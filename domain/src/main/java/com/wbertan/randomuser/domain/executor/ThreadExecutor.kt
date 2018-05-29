package com.wbertan.randomuser.domain.executor

import io.reactivex.Scheduler

interface ThreadExecutor {
  fun getScheduler(): Scheduler
}