package me.whiteship.demospringsecurityform.accont;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.springframework.security.test.context.support.WithMockUser;

@WithMockUser(username ="keesun", roles ="USER")
@Retention(RetentionPolicy.RUNTIME)
public @interface WithUser {

}
