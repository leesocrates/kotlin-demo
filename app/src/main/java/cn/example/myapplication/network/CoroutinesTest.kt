package cn.example.myapplication.network

import kotlinx.coroutines.*
import kotlinx.coroutines.GlobalScope.coroutineContext
import java.time.Instant.now
import java.util.concurrent.Executors
import kotlin.coroutines.Continuation
import kotlin.coroutines.ContinuationInterceptor

suspend fun main() {
//    log(1)
//    val job = GlobalScope.launch {
//        log(2)
//        delay(1000)
//    }
//    log(3)
//    job.join()
//    log(4)
//
//    // 我们可以通过指定上下文为协程添加一些特性，一个很好的例子就是为协程添加名称，方便调试：
//    GlobalScope.launch(CoroutineName("Hello")) {
//    }
//
//    //如果有多个上下文需要添加，直接用 + 就可以了：
//    GlobalScope.launch(Dispatchers.Main + CoroutineName("Hello")) {
//    }
//
//
//    // 使用自定义的拦截器
//    GlobalScope.launch(MyContinuationInterceptor()) {
//        log(1)
//        val job = async {
//            log(2)
//            delay(1000)
//            log(3)
//            "Hello"
//        }
//        log(4)
//        val result = job.await()
//        log("5. $result")
//    }.join()
//    log(6)
//
//
//    // 使用自定义调度器
//    val myDispatcher= Executors.newSingleThreadExecutor{ r -> Thread(r, "MyThread") }.asCoroutineDispatcher()
//    GlobalScope.launch(myDispatcher) {
//        log(1)
//    }.join()
//    log(2)
//    //主动关闭线程池或者调用 否则程序不会退出
//    myDispatcher.close()
//
//
//    // 异常处理
//    GlobalScope.launch(Dispatchers.Main) {
//        try {
//
//        } catch (e: Exception) {
//            val s = "Get User Error: $e"
//        }
//    }
//
//
//    // 设置协程中异常处理
//    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
//        log("Throws an exception with message: ${throwable.message}")
//    }
//
//    log(1)
//    GlobalScope.launch(exceptionHandler) {
//        throw ArithmeticException("Hey!")
//    }.join()
//    log(2)
//
//    // 设置协程全局异常处理，要使用ServiceLoader,在JVM层面设置异常处理器，就像设置调度器一样
//
//
//    // 异常传播 异常传播还涉及到协程作用域的概念，例如我们启动协程的时候一直都是用的 GlobalScope，意味着这是一个独立的顶级协程作用域，
//    // 此外还有 coroutineScope { ... } 以及 supervisorScope { ... }。
//
//
//    // async 启动协程
//    val deferred = GlobalScope.async<Int> {
//        throw ArithmeticException()
//    }
//    try {
//        val value = deferred.await()
//        log("1. $value")
//    } catch (e: Exception) {
//        log("2. $e")
//    }
//
//
//    // 协程的取消
//    val job1 = GlobalScope.launch { // ①
//        log(1)
//        delay(1000) // ②
//        log(2)
//    }
//    delay(100)
//    log(3)
//    job1.cancel() // ③
//    log(4)
}

fun log(msg: Any?) = println("${System.currentTimeMillis()} [${Thread.currentThread().name}] $msg")

// 所以我们也可以仿照 Thread.currentThread() 来一个获取当前 Job 的方法：
suspend inline fun Job.Key.currentJob() = coroutineContext[Job]

suspend fun coroutineJob() {
    GlobalScope.launch {
        log(Job.currentJob())
    }
    log(Job.currentJob())
}


// 我们可以自己定义一个拦截器放到我们的协程上下文中，看看会发生什么。
class MyContinuationInterceptor: ContinuationInterceptor {
    override val key = ContinuationInterceptor
    override fun <T> interceptContinuation(continuation: Continuation<T>) = MyContinuation(continuation)
}

class MyContinuation<T>(val continuation: Continuation<T>): Continuation<T> {
    override val context = continuation.context
    override fun resumeWith(result: Result<T>) {
        log("<MyContinuation> $result" )
        continuation.resumeWith(result)
    }
}