package info.firozansari.stackoverflowapp.dagger

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface AppComponent {


    @Component.Factory
    interface Factory {
        fun create(): AppComponent
    }
}
