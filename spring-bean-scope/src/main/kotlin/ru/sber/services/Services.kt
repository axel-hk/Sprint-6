package ru.sber.services

import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

@Component("singletonService")
@Scope("singleton")
class FirstService {
    override fun toString(): String {
        return "I am single"
    }
}
@Component("prototypeService")
@Scope("prototype")
class SecondService {
    override fun toString(): String {
        return "I am prototype"
    }
}