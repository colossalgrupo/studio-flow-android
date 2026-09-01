package com.colossalgrupo.studioflow.data.mock

import com.colossalgrupo.studioflow.domain.model.Establishment
import com.colossalgrupo.studioflow.domain.model.PayoutPeriodicity
import com.colossalgrupo.studioflow.domain.model.Professional
import com.colossalgrupo.studioflow.domain.model.ServiceCategory
import com.colossalgrupo.studioflow.domain.model.ServiceOffering

object MockData {

    val establishments: List<Establishment> = listOf(
        Establishment(
            id = "est-1",
            name = "Barbearia Vintage",
            category = ServiceCategory.BARBEARIA,
            city = "São Paulo, SP",
            rating = 4.8,
            professionalName = "Rafael Souza",
            services = listOf(
                ServiceOffering("srv-1", "Corte masculino", 40, 55.0),
                ServiceOffering("srv-2", "Barba", 25, 35.0)
            )
        ),
        Establishment(
            id = "est-2",
            name = "Studio Pilates Equilíbrio",
            category = ServiceCategory.PILATES,
            city = "São Paulo, SP",
            rating = 4.9,
            professionalName = "Camila Torres",
            services = listOf(
                ServiceOffering("srv-3", "Aula individual", 50, 90.0),
                ServiceOffering("srv-4", "Aula em dupla", 50, 70.0)
            )
        ),
        Establishment(
            id = "est-3",
            name = "João Personal Trainer",
            category = ServiceCategory.PERSONAL_TRAINER,
            city = "Rio de Janeiro, RJ",
            rating = 4.7,
            professionalName = "João Pedro Lima",
            services = listOf(
                ServiceOffering("srv-5", "Treino funcional", 60, 80.0)
            )
        ),
        Establishment(
            id = "est-4",
            name = "Espaço Unhas & Cia",
            category = ServiceCategory.MANICURE,
            city = "Belo Horizonte, MG",
            rating = 4.6,
            professionalName = "Beatriz Andrade",
            services = listOf(
                ServiceOffering("srv-6", "Manicure completa", 45, 45.0),
                ServiceOffering("srv-7", "Esmaltação em gel", 60, 65.0)
            )
        ),
        Establishment(
            id = "est-5",
            name = "Trança & Estilo",
            category = ServiceCategory.TRANCISTA,
            city = "Salvador, BA",
            rating = 5.0,
            professionalName = "Aline Costa",
            services = listOf(
                ServiceOffering("srv-8", "Box braids", 240, 250.0)
            )
        ),
        Establishment(
            id = "est-6",
            name = "Clínica PodoCare",
            category = ServiceCategory.PODOLOGIA,
            city = "Curitiba, PR",
            rating = 4.9,
            professionalName = "Marina Ferreira",
            services = listOf(
                ServiceOffering("srv-9", "Podologia clínica", 50, 90.0)
            )
        )
    )

    val professionals: List<Professional> = listOf(
        Professional(
            id = "prof-1",
            name = "Rafael Souza",
            cpf = "123.456.789-00",
            specialties = listOf("Corte masculino", "Barba"),
            payoutKey = "rafael.souza@pix.com",
            payoutPeriodicity = PayoutPeriodicity.WEEKLY,
            commissionPercent = 60.0
        ),
        Professional(
            id = "prof-2",
            name = "Beatriz Andrade",
            cpf = "987.654.321-00",
            specialties = listOf("Manicure", "Esmaltação em gel"),
            payoutKey = "beatriz.andrade@pix.com",
            payoutPeriodicity = PayoutPeriodicity.MONTHLY,
            commissionPercent = 55.0
        ),
        Professional(
            id = "prof-3",
            name = "Marina Ferreira",
            cpf = "456.789.123-00",
            specialties = listOf("Podologia clínica"),
            payoutKey = "marina.ferreira@pix.com",
            payoutPeriodicity = PayoutPeriodicity.WEEKLY,
            commissionPercent = 65.0
        )
    )

    val availableTimeSlots: List<String> = listOf(
        "09:00", "10:00", "11:00", "14:00", "15:00", "16:00", "17:00"
    )
}
