package bg.qponer.android.data.repository

import bg.qponer.android.auth.AuthModule
import bg.qponer.android.data.service.ServiceModule

class RepositoryModule(
    private val serviceModule: ServiceModule,
    private val authModule: AuthModule
) {

    fun createAuthRepository() = AuthRepository(
        serviceModule.createAuthService(),
        authModule.session
    )

    fun createBusinessOwnerRepository() = BusinessOwnerRepository(
        serviceModule.createBusinessOwnerService()
    )

    fun createContributorRepository() = ContributorRepository(
        serviceModule.createContributorService()
    )

    fun createVoucherRepository() = VoucherRepository(serviceModule.createVoucherService())

    fun createCardRepository() = CardRepository(
        serviceModule.createCardService(),
        authModule.session
    )
}