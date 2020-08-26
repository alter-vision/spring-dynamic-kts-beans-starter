package vision.alter.dynamic.serverless.impl

import mu.KLogging

import org.jetbrains.kotlin.cli.common.repl.KotlinJsr223JvmScriptEngineBase
import org.jetbrains.kotlin.script.jsr223.KotlinJsr223JvmLocalScriptEngine
import ru.rshb.intech.umqa.service.serverless.impl.DynamicKtsClassLoader
import vision.alter.dynamic.serverless.DynamicDeclarationBeanProvider

class DynamicDeclarationBeanProviderImpl(
    private val scriptEngine: KotlinJsr223JvmLocalScriptEngine,
    private val classLoader: DynamicKtsClassLoader,
    private val registerBean: (String, Class<*>, String, String, ByteArray) -> Unit,
    private val initializeBean: (Any, String) -> Any,
    private val getBean: (Class<*>) -> Any
) : DynamicDeclarationBeanProvider {

    override fun declareBeans(version: String, script: String): List<Class<*>> {
        val compiledScript = scriptEngine.compile(script) as KotlinJsr223JvmScriptEngineBase.CompiledKotlinScript
        return compiledScript.compiledData.classes.filterNot { it.path.startsWith("META-INF") }.map {
            val clz = classLoader.defineClassFromByteCode(
                it.bytes,
                it.path.replace(".class", "")
            )
            if (!clz.isInterface) {
                val name = clz.name.split("$").last()
                registerBean("${name[0].toLowerCase()}${name.substring(1)}", clz, version , it.path, it.bytes)
            }
            clz
        }
    }

    override fun bean(clz: Class<*>): Any = getBean(clz)

    override fun initializeObjectAsBean(name: String, bean: Any) {
        initializeBean(bean, name)
    }

    companion object: KLogging()
}