package hu.attilavegh.dressit.di

import dagger.Component
import hu.attilavegh.dressit.activities.InitialDataEntryActivity

@Component
interface InjectorComponent {
    fun inject(activity: InitialDataEntryActivity)
}