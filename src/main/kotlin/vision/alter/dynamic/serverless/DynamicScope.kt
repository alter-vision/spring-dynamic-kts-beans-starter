package vision.alter.dynamic.serverless

import org.springframework.beans.factory.ObjectFactory
import org.springframework.beans.factory.config.Scope
import vision.alter.dynamic.serverless.ScopeObjectsHolder


class DynamicScope(
    private val scopeObjectsHolder: ScopeObjectsHolder
) : Scope {

    override fun get(name: String, objectFactory: ObjectFactory<*>): Any =
        scopeObjectsHolder.getBean(name) ?: scopeObjectsHolder.addBean(name, objectFactory.`object`)

    override fun remove(name: String): Any? {
        TODO("Not yet implemented")
    }

    override fun registerDestructionCallback(name: String, callback: Runnable) {
        TODO("Not yet implemented")
    }

    override fun resolveContextualObject(key: String): Any? {
        TODO("Not yet implemented")
    }

    override fun getConversationId(): String? {
        TODO("Not yet implemented")
    }

    companion object {
        val NAME = "dynamic-scope"
    }
}