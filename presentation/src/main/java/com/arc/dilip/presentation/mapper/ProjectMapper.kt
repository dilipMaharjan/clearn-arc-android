package com.arc.dilip.presentation.mapper

import com.arc.dilip.presentation.model.Project
import javax.inject.Inject

class ProjectMapper @Inject constructor() : Mapper<Project, com.arc.dilip.domain.model.Project> {
    override fun mapToView(type: com.arc.dilip.domain.model.Project): Project {
        return Project(type.id,
                type.name,
                type.fullName,
                type.starCount,
                type.dateCreated,
                type.ownerName,
                type.ownerAvatar,
                type.isBookmarked)
    }
}