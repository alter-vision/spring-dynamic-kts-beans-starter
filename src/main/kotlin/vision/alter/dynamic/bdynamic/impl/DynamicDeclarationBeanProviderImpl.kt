package vision.alter.dynamic.bdynamic.impl

import mu.KLogging
import org.jetbrains.kotlin.cli.common.repl.KotlinJsr223JvmScriptEngineBase
import org.jetbrains.kotlin.script.jsr223.KotlinJsr223JvmLocalScriptEngine
import vision.alter.dynamic.bdynamic.DynamicDeclarationBeanProvider
import kotlin.reflect.KClass

class DynamicDeclarationBeanProviderImpl(
    private val scriptEngine: KotlinJsr223JvmLocalScriptEngine,
    private val classLoader: DynamicKtsClassLoader,
    private val registerBean: (String, Class<*>, String, String, ByteArray) -> Unit,
    private val initializeBean: (Any, String) -> Any,
    private val getBean: (Class<*>, String?) -> Any
) : DynamicDeclarationBeanProvider {

    override fun declareBeans(version: String, script: String): List<Class<*>> =
        declareScriptAsBean(version, script).values.toList()

    override fun bean(clz: Class<*>): Any = getBean(clz, null)

    override fun initializeObjectAsBean(name: String, bean: Any) {
        initializeBean(bean, name)
    }

    override fun <T : Any> declareBean(type: KClass<out T>, version: String, script: String): T? {
        val beans = declareScriptAsBean(version, script)
        val bean = beans.entries
            .firstOrNull { type.java.isAssignableFrom(it.value) }

        @Suppress("UNCHECKED_CAST")
        return bean?.let { getBean(bean.value, bean.key) } as? T
    }

    private fun declareScriptAsBean(version: String, script: String): Map<String, Class<*>> =
        (scriptEngine.compile(script) as KotlinJsr223JvmScriptEngineBase.CompiledKotlinScript)
            .compiledData.classes
            .asSequence()
            .filterNot { it.path.startsWith("META-INF") }
            .map {
                it to classLoader.defineClassFromByteCode(
                    it.bytes,
                    it.path.removeSuffix(".class")
                )
            }
            .filter { (_, beanClass) -> !beanClass.isInterface }
            .associate { (compiledData, beanClass) ->
                val className = beanClass.name
                    .substringAfterLast("$")
                    .plus("_${version.ifBlank { "001" }}")

                val suffix = System.currentTimeMillis().toString()

                val beanName = "get${className}_${suffix}"
                registerBean(beanName, beanClass, version, compiledData.path, compiledData.bytes)

                beanName to beanClass
            }

    companion object : KLogging()
}
