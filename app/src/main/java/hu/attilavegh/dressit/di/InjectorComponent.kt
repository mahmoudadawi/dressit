package hu.attilavegh.dressit.di

import dagger.Component
import hu.attilavegh.dressit.ui.activities.InitialDataEntryActivity

@Component
interface InjectorComponent {
    fun inject(activity: InitialDataEntryActivity)
}