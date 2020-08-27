package vision.alter.dynamic.serverless.impl

import org.springframework.stereotype.Component
import vision.alter.dynamic.serverless.ScopeObjectsHolder

@Component
class ScopeDynamicBeansHolderImpl : ScopeObjectsHolder {


    private val beans = mutableMapOf<String, Any>()


    override fun getBean(qualifier: String): Any? = beans[qualifier]

    override fun addBean(name: String, bean: Any): Any {
        beans[name] = bean
        return bean
    }

    override fun removeBean(name: String) {
        beans.remove(name)
    }

}