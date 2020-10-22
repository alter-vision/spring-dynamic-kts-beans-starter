package vision.alter.dynamic.bdynamic

import kotlin.reflect.KClass

interface DynamicDeclarationBeanProvider {

    fun declareBeans(version: String, scriptsData: List<String>): List<Class<*>> =
        scriptsData.flatMap { declareBeans(version, it) }

    fun declareBeans(version: String, script: String): List<Class<*>>

    fun <T : Any> declareBean(type: KClass<out T>, version: String, script: String): T?

    fun bean(clz: Class<*>): Any

    fun initializeObjectAsBean(name: String, bean: Any)

    companion object {

        inline fun <reified T : Any> DynamicDeclarationBeanProvider.declareAndGetBean(
            version: String,
            script: String
        ): T? =
            declareBean(T::class, version, script)

    }

}