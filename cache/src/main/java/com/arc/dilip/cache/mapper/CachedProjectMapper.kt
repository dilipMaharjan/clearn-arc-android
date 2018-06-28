package com.arc.dilip.cache.mapper

import com.arc.dilip.cache.model.CachedProject
import com.arc.dilip.data.model.ProjectEntity
import com.arc.dilip.domain.model.Project

class CachedProjectMapper : CacheMapper<CachedProject, ProjectEntity> {
    override fun mapFromCached(type: CachedProject): ProjectEntity {
        return ProjectEntity(
                type.id,
                type.name,
                type.fullName,
                type.starCount,
                type.dateCreated,
                type.ownerName,
                type.ownerAvatar,
                type.isBookmarked
        )
    }

    override fun mapToCached(type: ProjectEntity): CachedProject {
        return CachedProject(
                type.id,
                type.name,
                type.fullName,
                type.starCount,
                type.dateCreated,
                type.ownerName,
                type.ownerAvatar,
                type.isBookmarked
        )
    }
}