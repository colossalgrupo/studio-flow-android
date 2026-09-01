package com.colossalgrupo.studioflow.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.unit.dp
import com.colossalgrupo.studioflow.ui.theme.RoseDark
import com.colossalgrupo.studioflow.ui.theme.RoseLight

@Composable
fun CategoryTag(label: String) {
    val isDark = MaterialTheme.colorScheme.background.luminance() < 0.5f
    Surface(
        color = if (isDark) RoseDark.copy(alpha = 0.25f) else RoseLight.copy(alpha = 0.12f),
        contentColor = if (isDark) RoseDark else RoseLight,
        shape = RoundedCornerShape(50)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
        )
    }
}
