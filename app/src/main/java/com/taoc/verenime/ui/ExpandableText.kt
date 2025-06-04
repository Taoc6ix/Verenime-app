package com.taoc.verenime.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun ExpandableText(
    text: String,
    modifier: Modifier = Modifier,
    collapsedMaxLines: Int = 4, // Jumlah baris saat collapsed
    expanded: Boolean = false // Status awal
) {
    var isExpanded by remember { mutableStateOf(expanded) }

    Text(
        text = text,
        color = Color.White,
        modifier = modifier
            .clickable { isExpanded = !isExpanded } // Mengubah status saat diklik
            .padding(horizontal = 16.dp)
            .padding(8.dp),
        textAlign = TextAlign.Justify,
        maxLines = if (isExpanded) Int.MAX_VALUE else collapsedMaxLines, // Batasi baris jika tidak expanded
        overflow = TextOverflow.Clip // Tampilkan elipsis jika terpotong
    )
}