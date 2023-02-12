package br.com.coreapi.config;


import br.com.coreapi.config.clients.keycloak.utils.RequestAnnotation;
import br.com.coreapi.config.converter.CustomJwtAuthenticationConverter;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class SecurityConfigUtil {

    private static final String ROLES_CLIENT = "roles-client";
    @SuppressWarnings("rawtypes")
    private static final List<Class> HTTP_ANNOTATION = Arrays.asList(RequestMapping.class, GetMapping.class,
            PostMapping.class, PutMapping.class, PatchMapping.class, DeleteMapping.class);

    private SecurityConfigUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static void configureWebSecurity(final WebSecurity web) {
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**").antMatchers("/swagger-ui.html")
                .antMatchers("/swagger-ui/**").antMatchers("/v3/api-docs/**")
                .antMatchers(HttpMethod.POST, KeycloakConfig.ESTABILISHMENT)
                .antMatchers(HttpMethod.POST, KeycloakConfig.USER);
    }

    public static void configureSecurityKeycloak(final String prefix, final HttpSecurity http, List<Object> controllers)
            throws Exception {
        String filterMatcher = prefix + "/**";
        for (Endpoint endpoint : getEndpoints(prefix, controllers)) {
            for (RequestMethod requestMethod : endpoint.getHttpMethod()) {
                addSecurityConfig(http, filterMatcher, HttpMethod.valueOf(requestMethod.name()), endpoint.getPath(),
                        endpoint.getAuthoritys());
            }
        }
    }

    private static void addSecurityConfig(final HttpSecurity http, String filterMatcher, HttpMethod httpMethod,
            String path, String[] authoritys) throws Exception {
        if (Objects.nonNull(authoritys) && authoritys.length > 0) {
            http.antMatcher(filterMatcher).authorizeRequests().regexMatchers(httpMethod, path)
                    .hasAnyAuthority(authoritys).regexMatchers(httpMethod, path).authenticated().and()
                    .oauth2ResourceServer().jwt()
                    .jwtAuthenticationConverter(new CustomJwtAuthenticationConverter(ROLES_CLIENT));

        } else {
            http.antMatcher(filterMatcher).authorizeRequests().regexMatchers(httpMethod, path).authenticated().and()
                    .oauth2ResourceServer().jwt()
                    .jwtAuthenticationConverter(new CustomJwtAuthenticationConverter(ROLES_CLIENT));
        }
    }

    private static List<Endpoint> getEndpoints(final String prefix, List<Object> controllers) {
        List<Endpoint> listEndpoints = new ArrayList<>();
        for (Object object : controllers) {
            String requestMapping = getRequestMapping(object.getClass());
            List<Method> methods = getAllMethod(object.getClass());
            for (Method method : methods) {
                var endpoint = getCreateEndpoint(requestMapping, RequestAnnotation.getRequestMapping(method));
                if (Objects.nonNull(endpoint) && endpoint.getPath().startsWith(prefix)) {
                    listEndpoints.add(endpoint);
                }
            }
        }
        return listEndpoints;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private static String getRequestMapping(final Class clazz) {
        String[] listPath = {};
        if (HTTP_ANNOTATION.stream().anyMatch(clazz::isAnnotationPresent)) {
            var request = RequestAnnotation.getRequestMapping(clazz);
            if (Objects.nonNull(request)) {
                listPath = ((request.getPath().length > 0) ? request.getPath() : request.getValue());
            }
        }
        return String.join("/", listPath);
    }

    public static Endpoint getCreateEndpoint(String prefix, RequestAnnotation req) {
        if (Objects.nonNull(req)) {
            return new Endpoint(prefix, req.getValue(), req.getPath(), req.getHttpMethod(), req.getAuthoritys());
        }
        return null;
    }

    @SuppressWarnings("rawtypes")
    private static List<Method> getAllMethod(Class clazz) {
        List<Method> listMethods = new ArrayList<>();
        while (clazz != null) {
            for (Method method : clazz.getDeclaredMethods()) {
                if (HTTP_ANNOTATION.stream().anyMatch(method::isAnnotationPresent)
                        && !existsMethod(method, listMethods)) {
                    listMethods.add(method);
                }
            }
            clazz = clazz.getSuperclass();
        }
        return listMethods;
    }

    private static boolean existsMethod(Method method, List<Method> listMethods) {
        for (Method methodExisting : listMethods) {
            if (methodExisting.getName().equals(method.getName())
                    && Arrays.deepEquals(method.getParameterTypes(), methodExisting.getParameterTypes())) {
                return true;
            }
        }
        return false;
    }
}
