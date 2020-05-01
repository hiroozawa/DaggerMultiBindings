import dagger.Component
import dagger.MapKey
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

enum class MyEnum {
    ABC, DEF
}

@MapKey
annotation class MyEnumKey(val value: MyEnum)

@Module
object EnumKeyModule {
    @Provides
    @IntoMap
    @MyEnumKey(MyEnum.ABC)
    fun provideABCValue(): String {
        return "value for ABC"
    }

    @Provides
    @IntoMap
    @MyEnumKey(MyEnum.DEF)
    fun provideDEFValue(): String {
        return "value for DEF"
    }
}
@Component(modules = [EnumKeyModule::class])
interface Example4Component {
    fun stringByEnum(): Map<MyEnum, String>
}

fun main() {
    val component = DaggerExample4Component.create()

    println(component.stringByEnum())
}
