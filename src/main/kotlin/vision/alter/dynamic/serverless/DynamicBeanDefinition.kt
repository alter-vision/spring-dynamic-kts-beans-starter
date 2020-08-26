package vision.alter.dynamic.serverless

import org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition

class DynamicBeanDefinition(clz: Class<*>,
                            val version: String,
                            val compiledData: ByteArray,
                            val path: String
): AnnotatedGenericBeanDefinition(clz)