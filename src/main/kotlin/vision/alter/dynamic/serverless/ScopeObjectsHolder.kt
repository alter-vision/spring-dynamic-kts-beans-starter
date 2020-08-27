package vision.alter.dynamic.serverless

interface ScopeObjectsHolder : ScopeDynamicBeansProvider {
    fun addBean(name: String, bean: Any): Any
    fun removeBean(name: String)
}
