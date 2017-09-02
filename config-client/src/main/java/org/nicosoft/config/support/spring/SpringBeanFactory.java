package org.nicosoft.config.support.spring;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Spring bean factory support
 *
 * @author nico
 * @since 2017.9.1
 */
@Component
public class SpringBeanFactory implements ApplicationContextAware, BeanFactoryPostProcessor {

    ApplicationContext applicationContext;
    ConfigurableListableBeanFactory beanFactory;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * Reloading spring bean
     *
     * @param clazz
     * @param initMethod
     * @param destroyMethod
     * @throws Exception
     */
    public void reloadBean(Class<?> clazz, String initMethod, String destroyMethod) throws Exception {

        BeanDefinitionRegistry beanDefinitionRegistry = ((BeanDefinitionRegistry) this.beanFactory);
        String beanName = clazz.getSimpleName().substring(0, 1).toLowerCase() + clazz.getSimpleName().substring(1);

        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(clazz);
        beanDefinition.setAutowireCandidate(true);

        if (StringUtils.isNotBlank(initMethod)) {
            beanDefinition.setInitMethodName(initMethod);
        }

        if (StringUtils.isNotBlank(destroyMethod)) {
            beanDefinition.setDestroyMethodName(destroyMethod);
        }

        if (beanDefinitionRegistry.containsBeanDefinition(beanName)) {
            beanDefinitionRegistry.removeBeanDefinition(beanName);
        }

        beanDefinitionRegistry.registerBeanDefinition(beanName, beanDefinition);
        applicationContext.getBean(beanName);
    }

}
