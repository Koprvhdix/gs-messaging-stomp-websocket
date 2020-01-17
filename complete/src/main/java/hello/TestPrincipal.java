package hello;

import java.security.Principal;

import javax.security.auth.Subject;

/**
 * @author Clockworkai
 * @since 2020/1/17
 */
public class TestPrincipal implements Principal {
    private String name;

    public TestPrincipal(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean implies(Subject subject) {
        return false;
    }
}
