package com.mhasancse15.search.domain.repository

import com.mhasancse15.search.domain.model.Recipe
import com.mhasancse15.search.domain.model.RecipeDetails
import kotlinx.coroutines.flow.Flow

interface SearchRepository {

    suspend fun getRecipes(s:String): Result<List<Recipe>>

    suspend fun getRecipeDetails(id: String): Result<RecipeDetails>

    suspend fun insertRecipe(recipe: Recipe)

    suspend fun deleteRecipe(recipe: Recipe)

    fun getAllRecipes():Flow<List<Recipe>>


}