package com.rma.expensetracker.data.interactors.network

import com.rma.expensetracker.data.models.raw.account.AddAccountRequest
import com.rma.expensetracker.data.models.raw.account.AddAccountResponse
import com.rma.expensetracker.data.models.raw.account.AddUserToAccountRequest
import com.rma.expensetracker.data.models.raw.account.AddUserToAccountResponse
import com.rma.expensetracker.data.models.raw.account.GetAccountByIdRequest
import com.rma.expensetracker.data.models.raw.account.GetAccountByIdResponse
import com.rma.expensetracker.data.models.raw.account.GetUserGroupAccountsRequest
import com.rma.expensetracker.data.models.raw.account.GetUserGroupAccountsResponse
import com.rma.expensetracker.data.models.raw.account.GetUserPersonalAccountsRequest
import com.rma.expensetracker.data.models.raw.account.GetUserPersonalAccountsResponse
import com.rma.expensetracker.data.models.raw.account.RemoveUserFromAccountRequest
import com.rma.expensetracker.data.models.raw.account.RemoveUserFromAccountResponse
import com.rma.expensetracker.data.models.raw.account.UpdateAccountRequest
import com.rma.expensetracker.data.models.raw.account.UpdateAccountResponse
import com.rma.expensetracker.data.models.raw.authentication.LoginRequest
import com.rma.expensetracker.data.models.raw.authentication.LoginResponse
import com.rma.expensetracker.data.models.raw.authentication.RegistrationRequest
import com.rma.expensetracker.data.models.raw.authentication.RegistrationResponse
import com.rma.expensetracker.data.models.raw.category.AddCategoryRequest
import com.rma.expensetracker.data.models.raw.category.AddCategoryResponse
import com.rma.expensetracker.data.models.raw.category.DeleteCategoryRequest
import com.rma.expensetracker.data.models.raw.category.DeleteCategoryResponse
import com.rma.expensetracker.data.models.raw.category.GetCategoriesByUserIdRequest
import com.rma.expensetracker.data.models.raw.category.GetCategoriesByUserIdResponse
import com.rma.expensetracker.data.models.raw.category.GetCategoryByIdRequest
import com.rma.expensetracker.data.models.raw.category.GetCategoryByIdResponse
import com.rma.expensetracker.data.models.raw.record.AddRecordRequest
import com.rma.expensetracker.data.models.raw.record.AddRecordResponse
import com.rma.expensetracker.data.models.raw.record.DeleteRecordRequest
import com.rma.expensetracker.data.models.raw.record.DeleteRecordResponse
import com.rma.expensetracker.data.models.raw.record.GetRecordByIdRequest
import com.rma.expensetracker.data.models.raw.record.GetRecordByIdResponse
import com.rma.expensetracker.data.models.raw.record.GetRecordsByAccAndUserIdRequest
import com.rma.expensetracker.data.models.raw.record.GetRecordsByAccAndUserIdResponse
import com.rma.expensetracker.data.models.raw.record.GetRecordsByAccountIdRequest
import com.rma.expensetracker.data.models.raw.record.GetRecordsByAccountIdResponse
import com.rma.expensetracker.data.models.raw.record.GetRecordsByUserIdRequest
import com.rma.expensetracker.data.models.raw.record.GetRecordsByUserIdResponse
import com.rma.expensetracker.data.models.raw.record.UpdateRecordRequest
import com.rma.expensetracker.data.models.raw.record.UpdateRecordResponse
import com.rma.expensetracker.data.models.raw.user.GetAllUsersRequest
import com.rma.expensetracker.data.models.raw.user.GetAllUsersResponse
import com.rma.expensetracker.data.models.raw.user.GetUserByIdRequest
import com.rma.expensetracker.data.models.raw.user.GetUserByIdResponse
import com.rma.expensetracker.data.models.raw.user.GetUsersByAccountIdRequest
import com.rma.expensetracker.data.models.raw.user.GetUsersByAccountIdResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ExpenseTrackerApi {
    @POST("index.php")
    suspend fun registerUser(
        @Body registrationRequest: RegistrationRequest
    ): Response<RegistrationResponse>
    @POST("index.php")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): Response<LoginResponse>

    //// ACCOUNTS

    @POST("index.php")
    suspend fun getUserPersonalAccounts(
        @Body getUserPersonalAccountsRequest: GetUserPersonalAccountsRequest
    ): Response<GetUserPersonalAccountsResponse>

    @POST("index.php")
    suspend fun getUserGroupAccounts(
        @Body getUserGroupAccountsRequest: GetUserGroupAccountsRequest
    ): Response<GetUserGroupAccountsResponse>

    @POST("index.php")
    suspend fun getAccountById(
        @Body getAccountByIdRequest: GetAccountByIdRequest
    ): Response<GetAccountByIdResponse>

    @POST("index.php")
    suspend fun addAccount(
        @Body addAccountRequest: AddAccountRequest
    ): Response<AddAccountResponse>

    @POST("index.php")
    suspend fun updateAccount(
        @Body updateAccountRequest: UpdateAccountRequest
    ): Response<UpdateAccountResponse>

    @POST("index.php")
    suspend fun addUserToAccount(
        @Body addUserToAccountRequest: AddUserToAccountRequest
    ): Response<AddUserToAccountResponse>

    @POST("index.php")
    suspend fun removeUserFromAccount(
        @Body removeUserFromAccountRequest: RemoveUserFromAccountRequest
    ): Response<RemoveUserFromAccountResponse>


    //// RECORDS

    @POST("index.php")
    suspend fun getRecordById(
        @Body getRecordByIdRequest: GetRecordByIdRequest
    ): Response<GetRecordByIdResponse>

    @POST("index.php")
    suspend fun getRecordsByAccountId(
        @Body getRecordsByAccountIdRequest: GetRecordsByAccountIdRequest
    ): Response<GetRecordsByAccountIdResponse>

    @POST("index.php")
    suspend fun getRecordsByUserId(
        @Body getRecordsByUserIdRequest: GetRecordsByUserIdRequest
    ): Response<GetRecordsByUserIdResponse>

    @POST("index.php")
    suspend fun getRecordsByAccAndUserId(
        @Body getRecordsByAccAndUserIdRequest: GetRecordsByAccAndUserIdRequest
    ): Response<GetRecordsByAccAndUserIdResponse>

    @POST("index.php")
    suspend fun addRecord(
        @Body addRecordRequest: AddRecordRequest
    ): Response<AddRecordResponse>

    @POST("index.php")
    suspend fun deleteRecord(
        @Body deleteRecordRequest: DeleteRecordRequest
    ): Response<DeleteRecordResponse>

    @POST("index.php")
    suspend fun updateRecord(
        @Body updateRecordRequest: UpdateRecordRequest,
    ): Response<UpdateRecordResponse>

    //// CATEGORIES

    @POST("index.php")
    suspend fun addCategory(
        @Body addCategoryRequest: AddCategoryRequest
    ): Response<AddCategoryResponse>

    @POST("index.php")
    suspend fun deleteCategory(
        @Body deleteCategoryRequest: DeleteCategoryRequest
    ): Response<DeleteCategoryResponse>

    @POST("index.php")
    suspend fun getCategoryById(
        @Body getCategoryByIdRequest: GetCategoryByIdRequest
    ): Response<GetCategoryByIdResponse>

    @POST("index.php")
    suspend fun getCategoriesByUserId(
        @Body getCategoriesByUserId: GetCategoriesByUserIdRequest,
    ): Response<GetCategoriesByUserIdResponse>


    //// USERS

    @POST("index.php")
    suspend fun getAllUsers(
        @Body getAllUsersRequest: GetAllUsersRequest
    ): Response<GetAllUsersResponse>

    @POST("index.php")
    suspend fun getUserById(
        @Body getUserByIdRequest: GetUserByIdRequest
    ): Response<GetUserByIdResponse>

    @POST("index.php")
    suspend fun getUsersByAccountId(
        @Body getUsersByAccountIdRequest: GetUsersByAccountIdRequest
    ): Response<GetUsersByAccountIdResponse>
}