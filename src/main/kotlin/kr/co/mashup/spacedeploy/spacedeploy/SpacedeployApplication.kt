package kr.co.mashup.spacedeploy.spacedeploy

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpacedeployApplication

fun main(args: Array<String>) {
    runApplication<SpacedeployApplication>(*args)
}
