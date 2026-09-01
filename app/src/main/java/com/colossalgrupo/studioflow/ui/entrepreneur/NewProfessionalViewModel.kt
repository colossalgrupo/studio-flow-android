package com.colossalgrupo.studioflow.ui.entrepreneur

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.colossalgrupo.studioflow.data.repository.InMemoryProfessionalRepository
import com.colossalgrupo.studioflow.domain.model.PayoutPeriodicity
import com.colossalgrupo.studioflow.domain.model.Professional
import com.colossalgrupo.studioflow.domain.repository.ProfessionalRepository
import java.util.UUID

/**
 * Saves the new professional in memory only — there is no backend yet, so
 * this state is lost when the process dies. A future API-backed repository
 * will replace [InMemoryProfessionalRepository] without changing this screen.
 */
class NewProfessionalViewModel(
    private val repository: ProfessionalRepository = InMemoryProfessionalRepository.instance
) : ViewModel() {

    var name by mutableStateOf("")
        private set

    var cpf by mutableStateOf("")
        private set

    var specialtiesInput by mutableStateOf("")
        private set

    var payoutKey by mutableStateOf("")
        private set

    var payoutPeriodicity by mutableStateOf(PayoutPeriodicity.MONTHLY)
        private set

    var commissionPercentInput by mutableStateOf("")
        private set

    val canSave: Boolean
        get() = name.isNotBlank() &&
            cpf.isNotBlank() &&
            specialtiesInput.isNotBlank() &&
            payoutKey.isNotBlank() &&
            commissionPercentInput.toDoubleOrNull() != null

    fun onNameChange(value: String) { name = value }
    fun onCpfChange(value: String) { cpf = value }
    fun onSpecialtiesChange(value: String) { specialtiesInput = value }
    fun onPayoutKeyChange(value: String) { payoutKey = value }
    fun onPayoutPeriodicityChange(value: PayoutPeriodicity) { payoutPeriodicity = value }
    fun onCommissionPercentChange(value: String) { commissionPercentInput = value }

    fun save(): Boolean {
        val commission = commissionPercentInput.toDoubleOrNull() ?: return false
        if (!canSave) return false

        repository.addProfessional(
            Professional(
                id = UUID.randomUUID().toString(),
                name = name,
                cpf = cpf,
                specialties = specialtiesInput.split(",").map { it.trim() }.filter { it.isNotEmpty() },
                payoutKey = payoutKey,
                payoutPeriodicity = payoutPeriodicity,
                commissionPercent = commission
            )
        )
        return true
    }
}
