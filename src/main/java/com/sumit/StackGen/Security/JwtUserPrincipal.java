package com.sumit.StackGen.Security;

import com.sumit.StackGen.Enums.ProjectRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public record JwtUserPrincipal(Long userId, String username,
                               List<GrantedAuthority> authorities) {
}
