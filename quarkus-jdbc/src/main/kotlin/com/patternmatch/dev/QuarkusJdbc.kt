package com.patternmatch.dev

import io.agroal.api.AgroalDataSource
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@ApplicationScoped
class UserRepository {

    companion object {
        const val SELECT_USER_NAME_QUERY = "select name from person where id = ?"
    }

    @Inject
    var defaultDataSource: AgroalDataSource? = null

    fun getName(id: Int) = defaultDataSource?.connection?.let { conn ->
        conn.prepareStatement(SELECT_USER_NAME_QUERY).use { stmt ->
            stmt.setInt(1, id)
            stmt.executeQuery().use { rs ->
                if (rs.next()) rs.getString("name") else null
            }
        }
    }
}

@Path("/person")
class PersonResource {

    @Inject
    internal var userRepository: UserRepository? = null

    @GET
    @Path("/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    fun personById(@PathParam("id") id: Int) = userRepository?.getName(id)
}

