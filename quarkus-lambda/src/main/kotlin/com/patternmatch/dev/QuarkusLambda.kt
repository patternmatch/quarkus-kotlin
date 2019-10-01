package com.patternmatch.dev

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

@ApplicationScoped
class EchoPrinter {
    fun echo(s: String?) = "you said $s"
}

class EchoRequest(var echo: String? = null)

class EchoResponse(var echo: String)

class EchoLambda : RequestHandler<EchoRequest, EchoResponse> {
    @Inject
    var echoPrinter: EchoPrinter? = null

    override fun handleRequest(request: EchoRequest, context: Context): EchoResponse {
        return EchoResponse(echoPrinter!!.echo(request.echo))
    }
}
