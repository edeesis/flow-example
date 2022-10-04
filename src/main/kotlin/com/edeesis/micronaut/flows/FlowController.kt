package com.edeesis.micronaut.flows

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.serde.annotation.Serdeable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.reactive.asPublisher
import kotlinx.coroutines.reactor.asFlux
import org.reactivestreams.Publisher
import reactor.core.publisher.Flux

@Controller("/")
class FlowController {
    private val flow = flowOf(1, 2, 3)

    @Get("/Flow")
    fun flow(): Flow<Int> {
        return flow
    }

    @Get("/FlowResponse")
    fun flowResponse(): FlowResponse<Int> {
        return FlowResponse(flow)
    }

    @Get("/Publisher")
    fun publisher(): Publisher<Int> {
        return flow.asPublisher()
    }

    @Get("/PublisherResponse")
    fun publisherResponse(): PublisherResponse<Int> {
        return PublisherResponse(flow.asPublisher())
    }

    @Get("/Flux")
    fun flux(): Flux<Int> {
        return flow.asFlux()
    }

    @Get("/FluxResponse")
    fun fluxResponse(): FluxResponse<Int> {
        return FluxResponse(flow.asFlux())
    }
}

@Serdeable
class PublisherResponse<T>(
    val publisher: Publisher<T>
)

@Serdeable
class FluxResponse<T>(
    val flux: Flux<T>
)

@Serdeable
class FlowResponse<T>(
    val flow: Flow<T>
)
