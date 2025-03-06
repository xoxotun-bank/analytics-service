package com.example.analyticsservice.provider;

import lombok.*;
import org.springframework.security.core.context.*;
import org.springframework.stereotype.*;
import org.springframework.web.context.annotation.*;

import com.example.analyticsservice.model.*;
import com.example.jwtstarter.*;
import com.example.jwtstarter.exception.*;

@Component
@RequestScope
@RequiredArgsConstructor
public class ScopeAuthProvider {

    private final JwtValidationService validationService;

    public UserModel getUserInfo() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || auth.getDetails() == null) {
            throw new InvalidJwtTokenException("Token not found");
        }
        var tokenEncoded = auth.getDetails();
        var tokenModel = validationService.extractAccessTokenPayload((String) tokenEncoded);

        var result = new UserModel(
            tokenModel.getUserId(),
            tokenModel.getCity(),
            tokenModel.getRegion()
        );
        return result;

    }

}
