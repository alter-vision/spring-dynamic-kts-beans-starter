package vision.alter.dynamic.serverless

import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import ru.rshb.intech.umqa.service.serverless.impl.DynamicKtsClassLoader

class DynamicApplicationContextInitializer: ApplicationContextInitializer<ConfigurableApplicationContext> {

    override fun initialize(applicationContext: ConfigurableApplicationContext) {
        applicationContext.setClassLoader(DynamicKtsClassLoader())
    }
}