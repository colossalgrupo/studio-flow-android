package com.colossalgrupo.studioflow.ui.entrepreneur

import androidx.lifecycle.ViewModel
import com.colossalgrupo.studioflow.data.repository.InMemoryProfessionalRepository
import com.colossalgrupo.studioflow.domain.model.Professional
import com.colossalgrupo.studioflow.domain.model.SubscriptionPlan
import com.colossalgrupo.studioflow.domain.repository.ProfessionalRepository
import kotlinx.coroutines.flow.StateFlow

class EntrepreneurHomeViewModel(
    private val repository: ProfessionalRepository = InMemoryProfessionalRepository.instance
) : ViewModel() {

    val professionals: StateFlow<List<Professional>> = repository.professionals

    // Mocked for now — plan management will read/write the real subscription once billing exists.
    val currentPlan: SubscriptionPlan = SubscriptionPlan.BLACK
}
