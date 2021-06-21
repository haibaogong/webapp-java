package com.sdl.dxa.modules.audience.security;

import com.sdl.dxa.modules.audience.model.UserProfile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AudienceManagerAuthenticationProvider implements AuthenticationProvider {

    private final AudienceManagerUserService audienceManagerUserService;

    @Autowired
    public AudienceManagerAuthenticationProvider(AudienceManagerUserService audienceManagerUserService) {
        this.audienceManagerUserService = audienceManagerUserService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();

        UserProfile user = audienceManagerUserService.loadUserByUsername(username);

        if (!user.getIdentifiers().getIdentificationKey().equals(username)) {
            throw new BadCredentialsException("User '" + username + "' is found in AM, but with non-expecting IdentificationKey");
        }

        if (!user.verifyPassword(password)) {
            throw new BadCredentialsException("Wrong username/password for " + username);
        }

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());

        log.trace("Successful authentication for user '{}' and id '{}'", username, user.getId());
        return token;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}