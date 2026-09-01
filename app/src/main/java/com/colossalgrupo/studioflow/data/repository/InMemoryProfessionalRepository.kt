package com.colossalgrupo.studioflow.data.repository

import com.colossalgrupo.studioflow.data.mock.MockData
import com.colossalgrupo.studioflow.domain.model.Professional
import com.colossalgrupo.studioflow.domain.repository.ProfessionalRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * Holds professionals in memory only. There is no backend yet; persistence
 * and the real payout/commission rules will come from a future API.
 */
class InMemoryProfessionalRepository : ProfessionalRepository {

    private val _professionals = MutableStateFlow(MockData.professionals)
    override val professionals: StateFlow<List<Professional>> = _professionals

    override fun addProfessional(professional: Professional) {
        _professionals.value = _professionals.value + professional
    }

    companion object {
        val instance: ProfessionalRepository by lazy { InMemoryProfessionalRepository() }
    }
}
