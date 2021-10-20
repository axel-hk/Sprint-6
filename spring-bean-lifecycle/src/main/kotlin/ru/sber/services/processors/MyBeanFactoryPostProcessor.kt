package ru.sber.services.processors

import org.springframework.beans.factory.config.BeanFactoryPostProcessor
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class MyBeanFactoryPostProcessor : BeanFactoryPostProcessor {
    override fun postProcessBeanFactory(beanFactory: ConfigurableListableBeanFactory) {
        val names = beanFactory.beanDefinitionNames
        for(name in names) {
            val beanDefinition = beanFactory.getBeanDefinition(name)
            for (clas in beanFactory.javaClass.classes){
            for (method in clas.methods) {
                if (method.isAnnotationPresent(PostConstruct::class.java)) {
                    beanDefinition.initMethodName = method.name
                }
            }
            }
        }
    }
}