package vision.alter.dynamic.serverless

interface ScopeDynamicBeansProvider {
    fun getBean(qualifier: String): Any?
}
