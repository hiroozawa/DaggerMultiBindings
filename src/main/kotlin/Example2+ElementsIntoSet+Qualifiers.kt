import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.multibindings.ElementsIntoSet
import java.util.*
import javax.inject.Inject
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NintendoQualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DragonBallQualifier

class SalutationProvider @Inject constructor() {
    fun provide(): String = "Dear"
}

class NameTitleProvider @Inject constructor() {
    fun provide(): String = "Mister"
}

@Module
object NintendoModule {
    @Provides
    @ElementsIntoSet
    @NintendoQualifier
    fun provideSomeStrings(salutationProvider: SalutationProvider, nameTitleProvider: NameTitleProvider): Set<String> {
        return HashSet(
            listOf(
                "${salutationProvider.provide()} ${nameTitleProvider.provide()} Mario",
                "${salutationProvider.provide()} ${nameTitleProvider.provide()} Link"
            )
        )
    }
}

@Module
object DragonBallModule {
    @Provides
    @ElementsIntoSet
    @DragonBallQualifier
    fun provideSomeStrings(salutationProvider: SalutationProvider, nameTitleProvider: NameTitleProvider): Set<String> {
        return HashSet(
            listOf(
                "${salutationProvider.provide()} ${nameTitleProvider.provide()} Goku",
                "${salutationProvider.provide()} ${nameTitleProvider.provide()} Vegeta"
            )
        )
    }
}

@Component(modules = [NintendoModule::class, DragonBallModule::class])
internal interface Example2Component {
    @DragonBallQualifier
    fun dragonBallNames(): Set<String>

    @NintendoQualifier
    fun nintendoNames(): Set<String>
}

fun main() {
    val component = DaggerExample2Component.create()

    println(component.dragonBallNames())
    println(component.nintendoNames())
}