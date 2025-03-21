package com.icosahedron.graphql

import com.expediagroup.graphql.server.ktor.GraphQL
import com.expediagroup.graphql.server.ktor.defaultGraphQLStatusPages
import com.expediagroup.graphql.server.ktor.graphQLPostRoute
import com.expediagroup.graphql.server.operations.Query
import io.ktor.server.application.Application
import io.ktor.server.netty.EngineMain
import io.ktor.server.application.install
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.routing

fun main(args: Array<String>) {
    EngineMain.main(args)
}

class HelloWorldQuery : Query {
    fun hello(): String = "Hello World!"
    fun goodbye(): String = "Goodbye Cruel World!"
}

fun Application.graphQLModule() {
    install(GraphQL) {
        schema {
            packages = kotlin.collections.listOf() //listOf("com.icosahedron.graphql")
            queries = kotlin.collections.listOf(
                HelloWorldQuery()
            )
        }
    }

    install(StatusPages) {
        defaultGraphQLStatusPages()
    }

    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        graphQLPostRoute()
    }
}