import android.app.Application
import com.hyundeee.app.findnewusers.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication :Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            // Android context
            androidContext(this@MainApplication)
            // modules
            modules(appModules)
        }
    }
}
