package happyprogfrog.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@EntityScan(basePackages = ["happyprogfrog.domain"])
@ComponentScan(basePackages = ["happyprogfrog"])
@EnableCaching
class ApiApplication

fun main(args: Array<String>) {
    runApplication<ApiApplication>(*args)
}