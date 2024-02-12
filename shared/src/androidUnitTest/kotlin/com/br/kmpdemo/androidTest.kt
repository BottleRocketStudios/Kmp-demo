package com.br.kmpdemo

import org.junit.Assert.assertTrue
import org.junit.Test

class AndroidGreetingTest {

    @Test
    fun testExample() {
        assertTrue("Check Android is mentioned", Greeting(Platform()).greeting().contains("Android"))
    }
}