package com.test.anr.backgroundThread

import android.app.Activity
import android.os.Bundle
import java.io.FileWriter
import java.io.IOException
import kotlinx.coroutines.*

class MainActivity : Activity() {

    private var fw: FileWriter? = null
    private val supervisorScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            fw = FileWriter("s.txt")
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
        runCase()
        runCase()
    }

    private fun runCase() {
        supervisorScope.launch {
            IOBlockingCall("case10-supervisor-scope-io")
        }
    }

    private fun IOBlockingCall(tag: String) {
        try {
            fw?.write("IOBlockingCall: $tag\n")
            fw?.flush()
        } catch (e: IOException) {
        }
    }
}
