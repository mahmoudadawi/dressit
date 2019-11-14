package hu.attilavegh.dressit

import android.app.Application
import hu.attilavegh.dressit.di.DaggerInjectorComponent

class Application: Application() {
    val appComponent = DaggerInjectorComponent.create()
}