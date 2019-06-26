package com.example.searchcontent.data.executor

import com.example.searchcontent.domain.executor.ThreadExecutor
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class JobExecutor : ThreadExecutor {
    override fun execute(command: Runnable?) {
        this.threadPoolExecutor.execute(command)
    }

    private lateinit var threadPoolExecutor: ThreadPoolExecutor
    @Inject
    constructor()  {
        this.threadPoolExecutor = ThreadPoolExecutor(3, 5, 10, TimeUnit.SECONDS, LinkedBlockingQueue(), JobThreadFactory())
    }

    private class JobThreadFactory : ThreadFactory {
        private var counter = 0

        override fun newThread(runnable: Runnable): Thread {
            return Thread(runnable, "android_" + counter++)
        }
    }
}
