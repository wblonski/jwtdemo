package pl.wblo.jwtmodule.security.config;

public enum Permission {
    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete"),
    MANAGER_READ("management:read"),
    MANAGER_UPDATE("management:update"),
    MANAGER_CREATE("management:create"),
    MANAGER_DELETE("management:delete"),
    USER_READ("user:read");
    private final String content;

    Permission(String content) {
        this.content = content;
    }

    String getRoleMethodStr() {
        return this.content;
    }
}
