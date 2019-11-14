package hu.attilavegh.dressit.di

import dagger.Component
import hu.attilavegh.dressit.ui.activities.InitialDataEntryActivity
import hu.attilavegh.dressit.ui.fragments.shape.ShapeFragment

@Component
interface InjectorComponent {
    fun inject(activity: InitialDataEntryActivity)
    fun inject(activity: ShapeFragment)
}