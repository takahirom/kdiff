package com.github.takahirom.kdiff

import kotlin.test.Test
import kotlin.test.assertFailsWith

data class Article(
    val title: String,
    val writer: Writer,
    val tags: List<Tag>,
    val dateTime: DateTime
)

data class Writer(val name: String)
data class Tag(val name: String)
data class DateTime(val date: Date, val time: Time)
data class Date(val month: Int, val dayOfMonth: Int)
data class Time(val hour: Int, val minute: Int)

class SampleTests {
    @Test
    fun testMe() {

        val expected = listOf(
            Article(
                title = "Can detect title diff",
                writer = Writer("takahirom"),
                tags = listOf(
                    Tag("Kotlin"),
                    Tag("Multiplatform"),
                    Tag("Android")
                ),
                dateTime = DateTime(
                    date = Date(
                        month = 10,
                        dayOfMonth = 23
                    ),
                    time = Time(
                        hour = 9,
                        minute = 0
                    )
                )
            ),
            Article(
                title = "Can detect list size diff",
                writer = Writer("takahirom"),
                tags = listOf(
                    Tag("Kotlin"),
                    Tag("Multiplatform"),
                    Tag("Android")
                ),
                dateTime = DateTime(
                    date = Date(
                        month = 10,
                        dayOfMonth = 23
                    ),
                    time = Time(
                        hour = 9,
                        minute = 0
                    )
                )
            ),
            Article(
                title = "Can detect nested class diff",
                writer = Writer("takahirom"),
                tags = listOf(
                    Tag("Kotlin"),
                    Tag("Multiplatform"),
                    Tag("Android")
                ),
                dateTime = DateTime(
                    date = Date(
                        month = 10,
                        dayOfMonth = 23
                    ),
                    time = Time(
                        hour = 9,
                        minute = 0
                    )
                )
            )
        )
        val actual = listOf(
            Article(
                title = "Can detect title change?",
                writer = Writer("takahirom"),
                tags = listOf(
                    Tag("Kotlin"),
                    Tag("Multiplatform"),
                    Tag("Android")
                ),
                dateTime = DateTime(
                    date = Date(
                        month = 10,
                        dayOfMonth = 23
                    ),
                    time = Time(
                        hour = 9,
                        minute = 0
                    )
                )
            ),
            Article(
                title = "Can detect list size diff",
                writer = Writer("takahirom"),
                tags = listOf(
                    Tag("Kotlin"),
                    Tag("Multiplatform"),
                    Tag("Android"),
                    Tag("Test")
                ),
                dateTime = DateTime(
                    date = Date(
                        month = 10,
                        dayOfMonth = 23
                    ),
                    time = Time(
                        hour = 9,
                        minute = 0
                    )
                )
            ),
            Article(
                title = "Can detect nested class diff",
                writer = Writer("takahirom"),
                tags = listOf(
                    Tag("Kotlin"),
                    Tag("Multiplatform"),
                    Tag("Android")
                ),
                dateTime = DateTime(
                    date = Date(
                        month = 10,
                        dayOfMonth = 23
                    ),
                    time = Time(
                        hour = 9,
                        minute = 1
                    )
                )
            )
        )
//        assertEquals(expected, actual)
        assertFailsWith(AssertionError::class) {
            assertDataEquals(expected, actual)
        }
    }
}