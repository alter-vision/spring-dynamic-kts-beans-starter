package vision.alter.dynamic.bdynamic.impl

import org.springframework.beans.factory.config.BeanFactoryPostProcessor
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory
import org.springframework.stereotype.Component
import vision.alter.dynamic.bdynamic.DynamicScope

@Component
class DynamicScopeBeanFactoryPostProcessor : BeanFactoryPostProcessor {
    override fun postProcessBeanFactory(beanFactory: ConfigurableListableBeanFactory) {
        beanFactory.registerScope(DynamicScope.NAME, DynamicScope(ScopeDynamicBeansHolderImpl()))
    }
}