package com.colossalgrupo.studioflow.ui.profileselect

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Storefront
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.colossalgrupo.studioflow.domain.model.UserRole

@Composable
fun ProfileSelectionScreen(
    onProfileSelected: (UserRole) -> Unit
) {
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Como você quer usar o Studio Flow?",
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = "Você pode alternar entre os perfis depois, usando o mesmo login.",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 4.dp, bottom = 24.dp)
            )

            ProfileOptionCard(
                icon = Icons.Filled.Storefront,
                title = "Sou Empreendedor",
                description = "Gerencio um estabelecimento, meus profissionais e minha agenda.",
                onClick = { onProfileSelected(UserRole.ENTREPRENEUR) }
            )

            ProfileOptionCard(
                icon = Icons.Filled.Person,
                title = "Sou Cliente",
                description = "Quero agendar serviços de beleza e bem-estar.",
                onClick = { onProfileSelected(UserRole.CLIENT) },
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
}

@Composable
private fun ProfileOptionCard(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    description: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(140.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(top = 8.dp)
            )
            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}
