package ru.sber.services

import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
@Component("firstService")
class FirstService {
    override fun toString(): String {
        return "I am firstService"
    }
}
@Component("SecondService")
class SecondService {
    override fun toString(): String {
        return "I am secondService"
    }
}

@Component("thirdService")
class ThirdService {
    override fun toString(): String {
        return "I am thirdService"
    }
}

@Service("fourthService")
class FourthService {
    override fun toString(): String {
        return "I am fourthService"
    }
}