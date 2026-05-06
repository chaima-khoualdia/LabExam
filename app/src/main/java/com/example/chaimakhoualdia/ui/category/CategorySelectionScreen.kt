package com.example.chaimakhoualdia.ui.category

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.chaimakhoualdia.R
import com.example.chaimakhoualdia.ui.quiz.CategoryCardModel

@Composable
fun CategorySelectionScreen(
    categories: List<CategoryCardModel>,
    onCategorySelected: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = stringResource(id = R.string.select_category_title), style = MaterialTheme.typography.headlineMedium)
        Text(text = stringResource(id = R.string.select_category_subtitle), style = MaterialTheme.typography.bodyMedium)
        LazyVerticalGrid(
            columns = GridCells.Adaptive(160.dp),
            contentPadding = PaddingValues(vertical = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(categories) { category ->
                Card(onClick = { onCategorySelected(category.name) }) {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text(text = category.iconLabel, style = MaterialTheme.typography.headlineMedium)
                        Text(text = category.name, style = MaterialTheme.typography.titleMedium)
                        Text(text = stringResource(id = R.string.question_count_value, category.questionCount), style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }
    }
}

