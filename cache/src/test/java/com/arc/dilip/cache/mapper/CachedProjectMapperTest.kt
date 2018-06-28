package com.arc.dilip.cache.mapper

import com.arc.dilip.cache.data.ProjectDataFactory
import com.arc.dilip.cache.model.CachedProject
import com.arc.dilip.data.model.ProjectEntity
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class CachedProjectMapperTest {
    private val mapper = CachedProjectMapper()


    @Test
    fun mapFromCachedMapsData() {
        val model = ProjectDataFactory.makeCachedProject()
        val entity = mapper.mapFromCached(model)
        assertEqualData(model, entity)
    }

    @Test
    fun mapToCachedMapsData() {
        val entity = ProjectDataFactory.makeProjectEntity()
        val model = mapper.mapToCached(entity)
        assertEqualData(model, entity)
    }

    private fun assertEqualData(model: CachedProject, entity: ProjectEntity) {
        assertEquals(model.id, entity.id)
        assertEquals(model.name, entity.name)
        assertEquals(model.fullName, entity.fullName)
        assertEquals(model.dateCreated, entity.dateCreated)
        assertEquals(model.starCount, entity.starCount)
        assertEquals(model.isBookmarked, entity.isBookmarked)
        assertEquals(model.ownerName, entity.ownerName)
        assertEquals(model.ownerAvatar, entity.ownerAvatar)
    }
}