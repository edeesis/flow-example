package com.edeesis.micronaut.flows
import io.micronaut.test.extensions.kotest.annotation.MicronautTest
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client

@MicronautTest
class FlowControllerTest(
    @Client("/")
    private val httpClient: HttpClient
): StringSpec({

    listOf("Flow", "FlowResponse", "Publisher", "PublisherResponse", "Flux", "FluxResponse").map { name ->
        name {
            val result: HttpResponse<List<Int>> = httpClient.toBlocking().exchange(name)
            result.status shouldBe HttpStatus.OK
        }
    }
})
