package com.colossalgrupo.studioflow.domain.repository

import com.colossalgrupo.studioflow.domain.model.Establishment
import kotlinx.coroutines.flow.StateFlow

interface EstablishmentRepository {
    val establishments: StateFlow<List<Establishment>>
}
