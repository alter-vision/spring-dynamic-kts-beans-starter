package vision.alter.dynamic.bdynamic

import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import vision.alter.dynamic.bdynamic.impl.DynamicKtsClassLoader

class DynamicApplicationContextInitializer : ApplicationContextInitializer<ConfigurableApplicationContext> {

    override fun initialize(applicationContext: ConfigurableApplicationContext) {
        applicationContext.setClassLoader(DynamicKtsClassLoader())
    }
}