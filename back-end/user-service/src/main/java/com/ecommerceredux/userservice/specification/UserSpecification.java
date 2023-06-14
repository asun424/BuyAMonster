package com.ecommerceredux.userservice.specification;

import com.ecommerceredux.userservice.model.User;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification {
    public static Specification<User> matchUsername(String username){
        return (root, query, cb) ->
                cb.equal(root.<String>get("username"), username);
    }
}
