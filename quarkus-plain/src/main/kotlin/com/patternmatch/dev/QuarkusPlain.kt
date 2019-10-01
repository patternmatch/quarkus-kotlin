package com.patternmatch.dev

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.QueryParam
import javax.ws.rs.core.MediaType

@Path("/echo")
class EchoResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    fun hello(@QueryParam("str") str: String) = "you said $str"

    @GET
    @Path("/hello")
    fun hello() = "hello"
}