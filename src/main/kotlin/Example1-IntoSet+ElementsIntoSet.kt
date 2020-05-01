import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.multibindings.ElementsIntoSet
import dagger.multibindings.IntoSet
import java.util.*
import javax.inject.Inject

class ProtocolProvider @Inject constructor() {
    fun provide(): String = "https://"
}

class PathProvider @Inject constructor() {
    fun provide(): String = "/user"
}

@Module
object CompanyOneModule {
    @Provides
    @IntoSet
    fun provideOneString(protocolProvider: ProtocolProvider, pathProvider: PathProvider): String {
        return protocolProvider.provide() + "my-company-1.com" + pathProvider.provide()
    }
}

@Module
object CompanyTwoAndThreeModule {
    @Provides
    @ElementsIntoSet
    fun provideSomeStrings(protocolProvider: ProtocolProvider, pathProvider: PathProvider): Set<String> {
        return HashSet(
            listOf(
                protocolProvider.provide() + "my-company-2.com" + pathProvider.provide(),
                protocolProvider.provide() + "my-company-3.com" + pathProvider.provide()
            )
        )
    }
}

@Component(modules = [CompanyOneModule::class, CompanyTwoAndThreeModule::class])
internal interface Example1Component {
    fun urls(): Set<String>
}

fun main() {
    val strings = DaggerExample1Component.create().urls()

    println(strings)
}