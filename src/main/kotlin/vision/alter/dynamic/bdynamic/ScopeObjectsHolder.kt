package vision.alter.dynamic.bdynamic

interface ScopeObjectsHolder : ScopeDynamicBeansProvider {
    fun addBean(name: String, bean: Any): Any
    fun removeBean(name: String)
}
