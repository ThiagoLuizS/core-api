package br.com.coreapi.config.clients.keycloak.utils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@NoArgsConstructor
public class Endpoint {
    private String path;
    private String prefix;
    private RequestMethod[] httpMethod;
    private String[] authoritys;

    public Endpoint(String prefix, String[] value, String[] path, RequestMethod[] httpMethod, String[] authoritys) {
        this();
        this.path = setRealPath(prefix, value, path);
        this.prefix = prefix;
        this.httpMethod = httpMethod;
        this.authoritys = authoritys;
    }

    private String setRealPath(String prefix, String[] listValue, String[] listPath) {
        List<String> completePath = new ArrayList<>();
        if (Objects.nonNull(prefix)) {
            completePath.add(prefix);
        }
        if (Objects.nonNull(listPath) && listPath.length > 0) {
            completePath.addAll(Arrays.asList(listPath));
        } else if (Objects.nonNull(listValue) && listValue.length > 0) {
            completePath.addAll(Arrays.asList(listValue));
        }
        return createRegexPath(completePath);
    }

    private String createRegexPath(final List<String> completePath) {
        String returnPath = removeDuplicateSeparator(String.join("/", completePath));
        Matcher matchPattern = Pattern.compile("\\{([^}]+)\\}").matcher(returnPath);
        while (matchPattern.find()) {
            returnPath = returnPath.replace("{" + matchPattern.group(1) + "}", "[\\w-]+");
        }
        return (returnPath.startsWith("/")) ? returnPath : ("/" + returnPath);
    }

    private static String removeDuplicateSeparator(final String path) {
        String pathNew = path;
        while (pathNew.contains("//") || pathNew.contains("\\/\\/")) {
            pathNew = pathNew.replace("//", "/");
            pathNew = pathNew.replace("\\/\\/", "/");
        }
        return pathNew;
    }

}
