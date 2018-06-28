package com.arc.dilip.remote.mapper

import com.arc.dilip.data.model.ProjectEntity
import com.arc.dilip.remote.model.Project

open class ProjectResponseMapper : ModelMapper<Project, ProjectEntity> {
    override fun mapFromModel(model: Project): ProjectEntity {
        return ProjectEntity(
                model.id,
                model.name,
                model.fullName,
                model.starCount.toString(),
                model.dateCreated,
                model.owner.onwerName, model.owner.ownerAvatar, false)
    }
}