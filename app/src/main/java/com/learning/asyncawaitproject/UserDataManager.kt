package com.learning.asyncawaitproject

import kotlinx.coroutines.*

class UserDataManager {
    // So once we call this getTotalUserCount function before it return to the caller all the coroutine launched with in this scope
    // will complete.

    suspend fun getTotalUsreCount(): Int {
        var count = 0
        lateinit var deffered: Deferred<Int>
        // we are going to use coroutine suspended function inside this new suspend function
        // this coroutineScope Interface guarantees the completion of all the task with in the child scope provided by it
        // before return of the function
        coroutineScope {
            launch(Dispatchers.IO) {                                // here no need to add any dispatcher cause this coroutine
                // will launch in the dispatcher of parent scope
                delay(1000)
                count = 50
            }

             deffered =  async(Dispatchers.IO) {
                 delay(3000)
                 return@async 70
             }


        }


        return count + deffered.await()
    }


}