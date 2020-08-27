package vision.alter.dynamic.serverless

import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import vision.alter.dynamic.serverless.impl.DynamicKtsClassLoader

class DynamicApplicationContextInitializer : ApplicationContextInitializer<ConfigurableApplicationContext> {

    override fun initialize(applicationContext: ConfigurableApplicationContext) {
        applicationContext.setClassLoader(DynamicKtsClassLoader())
    }
}