package com.colossalgrupo.studioflow.domain.repository

import com.colossalgrupo.studioflow.domain.model.Professional
import kotlinx.coroutines.flow.StateFlow

interface ProfessionalRepository {
    val professionals: StateFlow<List<Professional>>
    fun addProfessional(professional: Professional)
}
