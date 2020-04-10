package bg.qponer.android

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import bg.qponer.android.auth.AuthModule
import bg.qponer.android.data.repository.RepositoryModule
import bg.qponer.android.data.service.ServiceModule
import bg.qponer.android.network.NetworkModule
import bg.qponer.android.ui.businesses.BusinessesViewModel
import bg.qponer.android.ui.login.LoginViewModel

class QponerApp : Application() {

    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate() {
        super.onCreate()

        val authModule = AuthModule(this)
        val networkModule = NetworkModule(authModule)
        val serviceModule = ServiceModule(networkModule)
        val repoModule = RepositoryModule(serviceModule, authModule)
        viewModelFactory = ViewModelFactory(repoModule)
    }

}

class ViewModelFactory(
    private val repoModule: RepositoryModule
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        when (modelClass) {
            LoginViewModel::class.java -> LoginViewModel(repoModule.createAuthRepository())
            BusinessesViewModel::class.java -> BusinessesViewModel(
                repoModule.createBusinessOwnerRepository()
            )
            else -> throw IllegalArgumentException("Unknown view model class: ${modelClass.canonicalName}")
        } as T

}