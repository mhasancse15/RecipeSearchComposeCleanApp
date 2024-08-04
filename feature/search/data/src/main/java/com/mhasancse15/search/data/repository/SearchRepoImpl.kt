package com.mhasancse15.search.data.repository

import com.mhasancse15.search.data.mappers.toDomain
import com.mhasancse15.search.data.remote.SearchApiService
import com.mhasancse15.search.domain.model.Recipe
import com.mhasancse15.search.domain.model.RecipeDetails
import com.mhasancse15.search.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow

class SearchRepoImpl(private val searchApiService: SearchApiService) : SearchRepository {
    override suspend fun getRecipes(s: String): Result<List<Recipe>> {
        return try {
            val response = searchApiService.getRecipes(s)
            if (response.isSuccessful) {
                response.body()?.meals?.let {
                    Result.success(it.toDomain())
                } ?: run { Result.failure(Exception("error occurred")) }
            } else {
                Result.failure(Exception("error occurred"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getRecipeDetails(id: String): Result<RecipeDetails> {
        return try {
            val response = searchApiService.getRecipeDetails(id)
            if (response.isSuccessful) {
                response.body()?.meals?.let {
                    if (it.isNotEmpty()) {
                        Result.success(it.first().toDomain())
                    } else {
                        Result.failure(Exception("error occurred"))
                    }
                } ?: run {
                    Result.failure(Exception("error occurred"))
                }
            } else {
                Result.failure(Exception("error occurred"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun insertRecipe(recipe: Recipe) {
       // TODO("Not yet implemented")
    }

    override suspend fun deleteRecipe(recipe: Recipe) {
      //  TODO("Not yet implemented")
    }

    override fun getAllRecipes(): Flow<List<Recipe>> {
        TODO("Not yet implemented")
    }
}