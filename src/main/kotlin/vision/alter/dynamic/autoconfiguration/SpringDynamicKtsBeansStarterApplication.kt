package vision.alter.dynamic.autoconfiguration

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringDynamicKtsBeansStarterApplication

fun main(args: Array<String>) {
    runApplication<SpringDynamicKtsBeansStarterApplication>(*args)
}
