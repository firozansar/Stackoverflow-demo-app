package info.firozansari.stackoverflowapp.ui

import android.app.Application
import info.firozansari.stackoverflowapp.dagger.AppComponent
import info.firozansari.stackoverflowapp.dagger.DaggerAppComponent

open class MainApplication : Application() {

    // Instance of the AppComponent that will be used by all the Activities in the project
    val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    open fun initializeComponent(): AppComponent {
        // Creates an instance of AppComponent using its Factory constructor
        // We pass the applicationContext that will be used as Context in the graph
        return DaggerAppComponent.factory().create(applicationContext)
    }

}