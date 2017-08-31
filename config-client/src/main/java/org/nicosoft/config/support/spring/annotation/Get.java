package org.nicosoft.config.support.spring.annotation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;


@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
public @interface Get {
    /**
     * The actual value expression: e.g. "#{systemProperties.myProp}".
     */
    @AliasFor(annotation = Value.class)
    String value();
    
}
