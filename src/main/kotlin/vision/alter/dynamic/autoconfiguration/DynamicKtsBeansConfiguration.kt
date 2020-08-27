package vision.alter.dynamic.autoconfiguration

import org.jetbrains.kotlin.script.jsr223.KotlinJsr223JvmLocalScriptEngine
import org.springframework.beans.factory.support.BeanDefinitionRegistry
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import vision.alter.dynamic.serverless.DynamicBeanDefinition
import vision.alter.dynamic.serverless.DynamicDeclarationBeanProvider
import vision.alter.dynamic.serverless.DynamicScope
import vision.alter.dynamic.serverless.impl.DynamicDeclarationBeanProviderImpl
import vision.alter.dynamic.serverless.impl.DynamicKtsClassLoader
import javax.script.ScriptEngineManager


@Configuration
@EnableAutoConfiguration
@ComponentScan("vision.alter.dynamic")
class DynamicKtsBeansConfiguration {

    fun scriptEngine(ctx: ConfigurableApplicationContext): KotlinJsr223JvmLocalScriptEngine =
        ScriptEngineManager(ctx.classLoader).getEngineByExtension("kts") as KotlinJsr223JvmLocalScriptEngine


    @Bean
    fun dynamicDeclarationBeanProvider(
        context: ConfigurableApplicationContext,
        initializeBean: (Any, String) -> Any
    ): DynamicDeclarationBeanProvider =
        if (context.classLoader !is DynamicKtsClassLoader)
            throw IllegalStateException("context.classLoader не является DynamicKtsClassLoader")
        else DynamicDeclarationBeanProviderImpl(
            scriptEngine(context),
            context.classLoader as DynamicKtsClassLoader,
            { name, clazz, version, path, compiledData ->
                val registry = context.beanFactory as BeanDefinitionRegistry
                registry.beanDefinitionNames.filter { it == name }.forEach { registry.removeBeanDefinition(it) }
                registry.registerBeanDefinition(
                    name,
                    DynamicBeanDefinition(
                        clz = clazz,
                        version = version,
                        path = path,
                        compiledData = compiledData
                    ).apply {
                        scope = DynamicScope.NAME
                    }
                )
            },
            initializeBean
        ) { clz -> context.getBean(clz) }

    @Bean
    fun initializeBean(context: ConfigurableApplicationContext): (Any, String) -> Any = { bean, name ->
        context.autowireCapableBeanFactory.initializeBean(bean, name)
    }
}