package vision.alter.dynamic.bdynamic

interface ScopeDynamicBeansProvider {
    fun getBean(qualifier: String): Any?
}
