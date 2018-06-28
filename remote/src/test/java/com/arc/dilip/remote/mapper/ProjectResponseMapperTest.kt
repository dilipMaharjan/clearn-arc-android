package com.arc.dilip.remote.mapper

import com.arc.dilip.remote.data.ProjectDataFactory
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class ProjectResponseMapperTest {

    private val mapper = ProjectResponseMapper()

    @Test
    fun mapFromModelMapsData() {
        val model = ProjectDataFactory.makeProject()
        val entity = mapper.mapFromModel(model)
        assertEquals(model.id, entity.id)
        assertEquals(model.name, entity.name)
        assertEquals(model.fullName, entity.fullName)
        assertEquals(model.starCount.toString(), entity.starCount)
        assertEquals(model.dateCreated, entity.dateCreated)
        assertEquals(model.owner.onwerName, entity.ownerName)
        assertEquals(model.id, entity.id)

    }
}