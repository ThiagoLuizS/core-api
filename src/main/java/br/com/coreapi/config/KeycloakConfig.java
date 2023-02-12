package br.com.coreapi.config;

import br.com.coreapi.config.clients.handler.CustomKeycloakAuthenticationHandler;
import lombok.RequiredArgsConstructor;
import org.keycloak.adapters.KeycloakConfigResolver;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.adapters.springsecurity.KeycloakSecurityComponents;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.keycloak.adapters.springsecurity.filter.KeycloakAuthenticationProcessingFilter;
import org.keycloak.adapters.springsecurity.management.HttpSessionManager;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.web.bind.annotation.RestController;
import org.zalando.problem.spring.web.advice.security.SecurityProblemSupport;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

//Defines all annotations that are needed to integrate Keycloak in Spring Security
@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
@Import(SecurityProblemSupport.class)
@ComponentScan(basePackageClasses = KeycloakSecurityComponents.class)
@Order(1)
class KeycloakConfig extends KeycloakWebSecurityConfigurerAdapter {
    public static final String ESTABILISHMENT = "/api/v2/establishment";
    public static final String SUB_ACCOUNT = "/api/v2/sub-account";
    public static final String USER = "/api/v2/user/first";
    private static final String PREFIX_MATCHER = "/api/v2";

    private final ListableBeanFactory listableBeanFactory;

    private final CustomKeycloakAuthenticationHandler customKeycloakAuthenticationHandler;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        SimpleAuthorityMapper grantedAuthorityMapper = new SimpleAuthorityMapper();
        grantedAuthorityMapper.setPrefix("ROLE_");

        KeycloakAuthenticationProvider keycloakAuthenticationProvider = keycloakAuthenticationProvider();
        keycloakAuthenticationProvider.setGrantedAuthoritiesMapper(grantedAuthorityMapper);
        auth.authenticationProvider(keycloakAuthenticationProvider);

    }

    //Keycloak auth exception handler
    @Bean
    @Override
    protected KeycloakAuthenticationProcessingFilter keycloakAuthenticationProcessingFilter() throws Exception {
        KeycloakAuthenticationProcessingFilter filter = new KeycloakAuthenticationProcessingFilter(
                this.authenticationManagerBean()
        );
        filter.setSessionAuthenticationStrategy(this.sessionAuthenticationStrategy());
        filter.setAuthenticationFailureHandler(customKeycloakAuthenticationHandler);
        return filter;
    }

    @Bean
    @Override
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
    }

    @Bean
    @Override
    @ConditionalOnMissingBean(HttpSessionManager.class)
    protected HttpSessionManager httpSessionManager() {
        return new HttpSessionManager();
    }

    @Override
    public void init(WebSecurity builder) throws Exception {

    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
            .antMatchers(HttpMethod.POST, ESTABILISHMENT)
            .antMatchers(HttpMethod.POST, USER)
            .antMatchers("/h2-console/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        SecurityConfigUtil.configureSecurityKeycloak(PREFIX_MATCHER, http, getAllControllers());
    }

    @Bean
    public KeycloakConfigResolver keycloakConfigResolver() {
        return new KeycloakSpringBootConfigResolver();
    }

    private List<Object> getAllControllers() {
        Map<String, Object> controllers = listableBeanFactory.getBeansWithAnnotation(RestController.class);
        return controllers.entrySet().stream().map(Entry::getValue).collect(Collectors.toList());
    }

}
