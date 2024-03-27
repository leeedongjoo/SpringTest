package com.daelim.SpringTest

import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.launch
import net.datafaker.Faker
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.util.*

@SpringBootTest
class CoroutineQuizSeo {
    @Test
    fun testDataFaker() {
        val faker = Faker(Locale.KOREA)
        println(faker.name().name())
        println(faker.internet().emailAddress())
        println(faker.address().fullAddress())
    }
    @Test
    fun quiz1TestSeo() {
        val faker = Faker(Locale.KOREA)
        runBlocking {
            List(100) {
                launch {
                    println("${faker.name().name()}")
                }
            }
        }
    }
    @Test
    fun quiz2TestSeo() {
        val faker = Faker(Locale.KOREA)
        runBlocking {
            List(50) {
                launch {
                    println("${faker.name().name()} , ${faker.internet().emailAddress()} , ${faker.address().fullAddress()}")
                }
            }
        }
    }

    data class User(val name: String, val age: Int)

    @Test
    fun quiz3TestSeo() {
        runBlocking {
            val faker = Faker(Locale.KOREA)
            val users = List(30) {
                async {
                    User(
                        name = faker.name().name(),
                        age = faker.number().numberBetween(1, 100)
                    )
                }
            }
            users.map { it.await() }.sortedBy { it.age }.map {
                println("${it.name}, ${it.age}")
            }
        }
    }
}