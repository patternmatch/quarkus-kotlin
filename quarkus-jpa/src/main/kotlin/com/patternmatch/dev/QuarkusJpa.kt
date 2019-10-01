package com.patternmatch.dev

import net.bytebuddy.utility.RandomString
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import javax.persistence.Entity
import javax.persistence.EntityManager
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.transaction.Transactional
import javax.ws.rs.*
import javax.ws.rs.core.MediaType

@Entity
class Person(@Id @GeneratedValue var id: Int? = null, var name: String? = null)

@ApplicationScoped
class PersonRepository {

    @Inject
    var entityManager: EntityManager? = null

    @Transactional
    fun createPerson(name: String): Person {
        val u = Person(name = name)
        entityManager?.persist(u)
        return u
    }

    fun getName(id: Int): String? =
        entityManager?.createQuery("select p from Person p where p.id = :id", Person::class.java)?.setParameter(
            "id",
            id
        )?.singleResult?.name
}

@Path("/person")
class PersonResource {

    @Inject
    internal var userRepository: PersonRepository? = null

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun personById(@PathParam("id") id: Int) = userRepository?.getName(id)

    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    fun createRandomPerson() = userRepository?.createPerson(RandomString.make(16))
}