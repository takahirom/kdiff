package com.github.takahirom.kdiff

import kotlin.reflect.KProperty1
import kotlin.test.fail

fun assertDataEquals(expected: Any, actual: Any) {
    if (expected == actual) return
    val visitResult = visit("target", expected, actual)
    fun collectAssertionErrors(visitResult: VisitResult): List<VisitResult.VerifyError> {
        return when (visitResult) {
            is VisitResult.VerifyError -> listOf(visitResult)
            is VisitResult.GroupResult -> visitResult.childErrors.flatMap { collectAssertionErrors(it) }
            else -> listOf()
        }
    }

    val assertionErrors = collectAssertionErrors(visitResult)
    fail("\n" + assertionErrors
        .joinToString("\n") { it.errorMessage() })
}

private fun visit(name: String, expected: Any?, actual: Any?): VisitResult {
    when {
        expected == actual -> return VisitResult.NoError(name)
        expected != null && actual != null && expected::class.isData -> return VisitResult.GroupResult(
            childErrors = expected::class.members.mapNotNull { property ->
                @Suppress("UNCHECKED_CAST")
                val castedProperty = property as? KProperty1<Any, Any?> ?: return@mapNotNull null
                visit(
                    name + "." + castedProperty.name,
                    castedProperty.get(expected),
                    castedProperty.get(actual)
                )
            }.filter { it !is VisitResult.NoError }
        )
        expected is List<*> -> {
            actual as List<*>
            if (expected.size != actual.size) {
                return VisitResult.VerifyError.ListSizeError(
                    name = name,
                    expectedSize = expected.size,
                    actualSize = actual.size
                )
            }
            return VisitResult.GroupResult(
                expected.mapIndexed { index, any ->
                    visit("$name[$index]", any, actual[index])
                }
            )
        }
        else -> return if (expected == actual) {
            VisitResult.NoError(name)
        } else {
            VisitResult.VerifyError.EqualsError(name, expected, actual)
        }
    }
}

sealed class VisitResult {
    sealed class VerifyError(
        open val name: String
    ) : VisitResult() {
        abstract fun errorMessage(): String
        data class EqualsError<T>(
            override val name: String,
            val expected: T,
            val actual: T
        ) : VerifyError(name) {
            override fun errorMessage(): String {
                return "${name} Expected <${expected}>, actual <${actual}> is not same."
            }
        }

        data class ListSizeError(override val name: String, val expectedSize: Int, val actualSize: Int) :
            VerifyError(name) {
            override fun errorMessage(): String {
                return "${name} Expected size <${expectedSize}>, actual size <${actualSize}> is not same."
            }
        }
    }

    data class NoError(val name: String) : VisitResult()

    data class GroupResult(
        val childErrors: List<VisitResult>
    ) : VisitResult()
}
