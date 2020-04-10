package bg.qponer.android.data.service

import bg.qponer.android.network.NetworkModule

class ServiceModule(
    private val networkModule: NetworkModule
) {

    fun createAuthService(): AuthService = networkModule.retrofit.create(AuthService::class.java)

    fun createBusinessOwnerService(): BusinessOwnerService =
        networkModule.retrofit.create(BusinessOwnerService::class.java)

    fun createContributorService(): ContributorService =
        networkModule.retrofit.create(ContributorService::class.java)

}