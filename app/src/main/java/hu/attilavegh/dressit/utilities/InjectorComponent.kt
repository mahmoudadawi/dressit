package hu.attilavegh.dressit.utilities

import dagger.Component
import hu.attilavegh.dressit.LoginActivity
import hu.attilavegh.dressit.MainActivity

@Component(modules = {LoginActivity.class})
interface InjectorComponent {

    @Component.Builder
    interface Builder {
        fun build(): InjectorComponent
    }

    fun inject(app: MainActivity)
}