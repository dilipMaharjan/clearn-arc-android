package com.arc.dilip.remote.data

import com.arc.dilip.data.model.ProjectEntity
import com.arc.dilip.remote.model.Owner
import com.arc.dilip.remote.model.Project
import com.arc.dilip.remote.model.ProjectResponse

object ProjectDataFactory {

    fun makeOwner(): Owner {
        return Owner(DataFactory.randomString(), DataFactory.randomString())
    }

    fun makeProject(): Project {
        return Project(DataFactory.randomString(),
                DataFactory.randomString(),
                DataFactory.randomString(),
                DataFactory.randomInt(),
                DataFactory.randomString(), makeOwner())
    }

    fun makeProjectEntity(): ProjectEntity {
        return ProjectEntity(DataFactory.randomString(),
                DataFactory.randomString(),
                DataFactory.randomString(),
                DataFactory.randomString(),
                DataFactory.randomString(),
                DataFactory.randomString(),
                DataFactory.randomString(),
                DataFactory.randomBoolean())
    }

    fun makeProjectResponse(): ProjectResponse {
        return ProjectResponse(listOf(makeProject(), makeProject()))

    }
}