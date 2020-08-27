package vision.alter.dynamic.serverless.impl

import org.springframework.beans.factory.config.BeanFactoryPostProcessor
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory
import org.springframework.stereotype.Component
import vision.alter.dynamic.serverless.DynamicScope

@Component
class DynamicScopeBeanFactoryPostProcessor : BeanFactoryPostProcessor {
    override fun postProcessBeanFactory(beanFactory: ConfigurableListableBeanFactory) {
        beanFactory.registerScope(DynamicScope.NAME, DynamicScope(ScopeDynamicBeansHolderImpl()))
    }
}