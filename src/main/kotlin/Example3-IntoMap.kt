import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey

@Module
object Example3Module {
    @Provides
    @IntoMap
    @StringKey("foo")
    fun provideFooValue(): Long {
        return 100L
    }

    @Provides
    @IntoMap
    @StringKey("boo")
    fun provideBooValue(): Long {
        return 200L
    }

    @Provides
    @IntoMap
    @ClassKey(Thing::class)
    fun provideThingValue(): String {
        return "value for Thing"
    }
}

@Component(modules = [Example3Module::class])
interface Example3Component {
    fun longsByString(): Map<String, Long>
    fun stringsByClass(): Map<Class<*>, String>
}

fun main() {
    val component = DaggerExample3Component.create()
    val longMap = component.longsByString()
    val stringMap = component.stringsByClass()

    println(longMap)
    println(stringMap)
}

class Thing
