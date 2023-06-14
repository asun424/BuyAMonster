package com.ecommerceRedux.apigateway.authenticate;


import com.ecommerceRedux.apigateway.config.JwtService;
import com.ecommerceRedux.apigateway.model.User;
import com.ecommerceRedux.apigateway.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Slf4j
@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RouteValidator validator;

    @Autowired
    private JwtService jwtService;

    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            if (validator.isSecured.test(exchange.getRequest())) {
                //header contains token or not
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new RuntimeException("missing authorization header");
                }

                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7);
                }

                String userId = jwtService.extractId(authHeader);
                String username = jwtService.extractUsername(authHeader);

                User user = userRepository.findById(Long.parseLong(userId)).orElse(null);

                if(user.getUsername() != username) {
                    System.out.println("invalid access...!");
                    throw new RuntimeException("unauthorized to access to application");
                }
            }
            return chain.filter(exchange);
        });
    }

    public static class Config {

    }
}
