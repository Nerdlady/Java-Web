package server.routing.annotations;

import server.http.HttpRequestType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {
    String value();
    HttpRequestType type() default HttpRequestType.GET;
}
