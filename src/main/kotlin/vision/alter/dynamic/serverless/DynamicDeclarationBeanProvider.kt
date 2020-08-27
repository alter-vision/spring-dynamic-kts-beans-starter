package vision.alter.dynamic.serverless

interface DynamicDeclarationBeanProvider {
    fun declareBeans(version: String, scriptsData: List<String>) = scriptsData.forEach { declareBeans(version, it) }
    fun declareBeans(version: String, script: String): List<Class<*>>
    fun bean(clz: Class<*>): Any
    fun initializeObjectAsBean(name: String, bean: Any)
}