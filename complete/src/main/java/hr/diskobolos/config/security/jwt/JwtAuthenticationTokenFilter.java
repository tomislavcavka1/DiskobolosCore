package hr.diskobolos.config.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import hr.diskobolos.config.security.authentication.AuthenticatedUser;
import org.slf4j.LoggerFactory;

public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(JwtTokenHandler.class);

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenHandler jwtTokenHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        String jwt = request.getHeader(this.tokenHeader);
        logger.info("Checking JWT : " + jwt);

        // User not already authenticated, try to authenticate him through JWT
        if (SecurityContextHolder.getContext().getAuthentication() == null && StringUtils.isNotEmpty(jwt)) {
            if (jwtTokenHandler.isTokenValid(jwt)) {

                // Parse JWT
                AuthenticatedUser user = jwtTokenHandler.parseToken(jwt);

                // If JWT valid, populate SecurityContext with the data
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }
}
