package info.firozansari.stackoverflowapp.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import info.firozansari.stackoverflowapp.ui.main.MainActivity
import info.firozansari.stackoverflowapp.ui.main.MainFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ApiModule::class])
interface AppComponent {

    // Factory to create instances of the AppComponent
    @Component.Factory
    interface Factory {
        // With @BindsInstance, the Context passed in will be available in the graph
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(activity: MainActivity)
    fun inject(fragment: MainFragment)
}
