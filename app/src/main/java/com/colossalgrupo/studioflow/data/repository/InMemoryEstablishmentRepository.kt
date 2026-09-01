package com.colossalgrupo.studioflow.data.repository

import com.colossalgrupo.studioflow.data.mock.MockData
import com.colossalgrupo.studioflow.domain.model.Establishment
import com.colossalgrupo.studioflow.domain.repository.EstablishmentRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class InMemoryEstablishmentRepository : EstablishmentRepository {

    private val _establishments = MutableStateFlow(MockData.establishments)
    override val establishments: StateFlow<List<Establishment>> = _establishments

    companion object {
        val instance: EstablishmentRepository by lazy { InMemoryEstablishmentRepository() }
    }
}
