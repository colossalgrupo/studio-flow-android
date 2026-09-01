package com.colossalgrupo.studioflow.ui.client

import androidx.lifecycle.ViewModel
import com.colossalgrupo.studioflow.data.repository.InMemoryEstablishmentRepository
import com.colossalgrupo.studioflow.domain.model.Establishment
import com.colossalgrupo.studioflow.domain.repository.EstablishmentRepository
import kotlinx.coroutines.flow.StateFlow

class ClientHomeViewModel(
    private val repository: EstablishmentRepository = InMemoryEstablishmentRepository.instance
) : ViewModel() {

    val establishments: StateFlow<List<Establishment>> = repository.establishments
}
