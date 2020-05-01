import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import dagger.multibindings.IntoSet

@Module
object ParentModule {
    @Provides
    @IntoSet
    fun string1(): String {
        return "parent string 1"
    }

    @Provides
    @IntoSet
    fun string2(): String {
        return "parent string 2"
    }
}

@Module
object ChildModule {
    @Provides
    @IntoSet
    fun string3(): String {
        return "child string 3"
    }

    @Provides
    @IntoSet
    fun string4(): String {
        return "child string 4"
    }
}

@Component(modules = [ParentModule::class])
interface ParentComponent {
    fun strings(): Set<String>
    fun childComponent(): ChildComponent
}

@Subcomponent(modules = [ChildModule::class])
interface ChildComponent {
    fun strings(): Set<String>
}

fun main() {
    val parentComponent = DaggerParentComponent.create()
    val childComponent = parentComponent.childComponent()

    println(parentComponent.strings())
    println(childComponent.strings())
}
