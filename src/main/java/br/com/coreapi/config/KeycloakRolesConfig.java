package br.com.coreapi.config;

import br.com.coreapi.config.clients.keycloak.request.get.KeycloakService;
import br.com.coreapi.config.clients.keycloak.request_dto.CreateRolesRequest;
import br.com.coreapi.config.clients.keycloak.response.RoleResponse;
import br.com.coreapi.config.clients.keycloak.utils.Endpoint;
import br.com.coreapi.config.clients.keycloak.utils.RequestAnnotation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
public class KeycloakRolesConfig {

    @SuppressWarnings("rawtypes")
    private static final List<Class> HTTP_ANNOTATION = Arrays.asList(RequestMapping.class, GetMapping.class,
            PostMapping.class, PutMapping.class, PatchMapping.class, DeleteMapping.class);

    @Value("${coreapi.keycloak.roles-client-id}")
    private String rolesClientId;

    private final KeycloakService keycloakService;
    private final ListableBeanFactory listableBeanFactory;

    @Bean
    public void attRoles() {
        var newRoles = mergeRoles();
        for (String name : newRoles) {
            keycloakService.createRoles(rolesClientId, new CreateRolesRequest(name));
        }
    }

    public List<String> mergeRoles() {
        var newRoles = new LinkedHashSet<String>();
        var keycloakRoles = getRolesOfKeycloak();
        if (Objects.nonNull(keycloakRoles)) {
            var sytemRoles = getRolesOfSystem(getAllControllers());
            for (String role : sytemRoles) {
                if (!keycloakRoles.contains(role)) {
                    newRoles.add(role);
                }
            }
        }
        return new ArrayList<>(newRoles);
    }

    private List<String> getRolesOfKeycloak() {
        try {
            return Optional.ofNullable(keycloakService.getAllRoles(rolesClientId)).map(ResponseEntity::getBody)
                    .map(body -> body.stream().map(RoleResponse::getName).collect(Collectors.toList()))
                    .orElse(new ArrayList<>());
        } catch (Exception e) {
            return null;
        }

    }

    public List<Object> getAllControllers() {
        Map<String, Object> controllers = listableBeanFactory.getBeansWithAnnotation(RestController.class);
        return controllers.entrySet().stream().map(Entry::getValue).collect(Collectors.toList());
    }

    private static List<String> getRolesOfSystem(List<Object> controllers) {
        var roles = new ArrayList<String>();
        for (Object object : controllers) {
            var methods = getAllMethod(object.getClass());
            for (Method method : methods) {
                var req = RequestAnnotation.getRequestMapping(method);
                if (Objects.nonNull(req) && Objects.nonNull(req.getAuthoritys())) {
                    roles.addAll(Arrays.asList(req.getAuthoritys()));
                }
            }
        }
        return roles;
    }

    public static Endpoint getCreateEndpoint(String prefix, RequestAnnotation req) {
        if (Objects.nonNull(req)) {
            return new Endpoint(prefix, req.getValue(), req.getPath(), req.getHttpMethod(), req.getAuthoritys());
        }
        return null;
    }

    @SuppressWarnings("rawtypes")
    private static List<Method> getAllMethod(Class clazz) {
        var listMethods = new ArrayList<Method>();
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
