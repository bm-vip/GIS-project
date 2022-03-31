package se.dzm.electricvehiclechargingstationmanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class AuditConfig {
    @Bean
    public AuditorAware<String> auditorAware() {
        return new AuditorAwareImpl();
    }

    public class AuditorAwareImpl implements AuditorAware<String> {
        @Override
        public Optional<String> getCurrentAuditor() {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth.getName().equals("anonymousUser"))
                return Optional.ofNullable("b.mohamadi");
            return Optional.ofNullable(auth.getName());
        }
    }
}