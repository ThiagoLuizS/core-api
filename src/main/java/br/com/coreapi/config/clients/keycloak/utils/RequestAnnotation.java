package br.com.coreapi.config.clients.keycloak.utils;

import lombok.Getter;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.reflect.Method;

@Getter
public class RequestAnnotation {

    private String[] value;
    private String[] path;
    private RequestMethod[] httpMethod;
    private String[] authoritys;

    private RequestAnnotation() {
        super();
    }

    private RequestAnnotation(String[] path, String[] value, RequestMethod[] httpMethod, String[] authoritys) {
        this();
        this.path = path;
        this.value = value;
        this.httpMethod = httpMethod;
        this.authoritys = authoritys;
    }

    private RequestAnnotation(RequestMapping req, String[] authoritys) {
        this(req.path(), req.value(), req.method(), authoritys);
    }

    private RequestAnnotation(GetMapping req, String[] authoritys) {
        this(req.path(), req.value(), new RequestMethod[] { RequestMethod.GET }, authoritys);
    }

    private RequestAnnotation(PostMapping req, String[] authoritys) {
        this(req.path(), req.value(), new RequestMethod[] { RequestMethod.POST }, authoritys);
    }

    private RequestAnnotation(DeleteMapping req, String[] authoritys) {
        this(req.path(), req.value(), new RequestMethod[] { RequestMethod.DELETE }, authoritys);
    }

    private RequestAnnotation(PutMapping req, String[] authoritys) {
        this(req.path(), req.value(), new RequestMethod[] { RequestMethod.PUT }, authoritys);
    }

    private RequestAnnotation(PatchMapping req, String[] authoritys) {
        this(req.path(), req.value(), new RequestMethod[] { RequestMethod.PATCH }, authoritys);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static RequestAnnotation getRequestMapping(final Class clazz) {
        if (clazz.isAnnotationPresent(RequestMapping.class)) {
            return new RequestAnnotation((RequestMapping) clazz.getAnnotation(RequestMapping.class), null);
        } else if (clazz.isAnnotationPresent(GetMapping.class)) {
            return new RequestAnnotation((GetMapping) clazz.getAnnotation(GetMapping.class), null);
        } else if (clazz.isAnnotationPresent(PostMapping.class)) {
            return new RequestAnnotation((PostMapping) clazz.getAnnotation(PostMapping.class), null);
        } else if (clazz.isAnnotationPresent(DeleteMapping.class)) {
            return new RequestAnnotation((DeleteMapping) clazz.getAnnotation(DeleteMapping.class), null);
        } else if (clazz.isAnnotationPresent(PutMapping.class)) {
            return new RequestAnnotation((PutMapping) clazz.getAnnotation(PutMapping.class), null);
        } else if (clazz.isAnnotationPresent(PatchMapping.class)) {
            return new RequestAnnotation((PatchMapping) clazz.getAnnotation(PatchMapping.class), null);
        }
        return null;
    }

    public static RequestAnnotation getRequestMapping(final Method method) {
        String[] authoritys = null;
        if (method.isAnnotationPresent(Secured.class)) {
            authoritys = method.getAnnotation(Secured.class).value();
        }
        if (method.isAnnotationPresent(RequestMapping.class)) {
            return new RequestAnnotation(method.getAnnotation(RequestMapping.class), authoritys);
        } else if (method.isAnnotationPresent(GetMapping.class)) {
            return new RequestAnnotation(method.getAnnotation(GetMapping.class), authoritys);
        } else if (method.isAnnotationPresent(PostMapping.class)) {
            return new RequestAnnotation(method.getAnnotation(PostMapping.class), authoritys);
        } else if (method.isAnnotationPresent(DeleteMapping.class)) {
            return new RequestAnnotation(method.getAnnotation(DeleteMapping.class), authoritys);
        } else if (method.isAnnotationPresent(PutMapping.class)) {
            return new RequestAnnotation(method.getAnnotation(PutMapping.class), authoritys);
        } else if (method.isAnnotationPresent(PatchMapping.class)) {
            return new RequestAnnotation(method.getAnnotation(PatchMapping.class), authoritys);
        }
        return null;
    }

}
