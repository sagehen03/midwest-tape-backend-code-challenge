package com.midwesttape.project.challengeapplication.service;

import com.midwesttape.project.challengeapplication.model.Address;
import com.midwesttape.project.challengeapplication.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final JdbcTemplate template;

    public User user(final Long userId) {
        try {

            return template.queryForObject(
                "select " +
                    "u.id, " +
                    "firstName, " +
                    "lastName, " +
                    "username, " +
                    "password, address1, address2, city, state, postal " +
                    "from User u join Address a on u.address_id = a.id " +
                    "where u.id = ?",
                (rs, rowNum) -> {
                    Address address = new Address(rs.getLong("id"), rs.getString("address1"),
                        rs.getString("address2"), rs.getString("city"),
                        rs.getString("state"), rs.getString("postal"));
                    return new User(rs.getLong("id"),
                        rs.getString("firstName"), rs.getString("lastName"),
                        rs.getString("username"), rs.getString("password"), address);
                },
                userId
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }

    public User updateUserAttribute(long userId, String attribute, String newValue){
        Set<String> allowedAttributes = Set.of("firstName", "lastName", "username", "password");
        if(!allowedAttributes.contains(attribute)){
            throw new IllegalArgumentException("Only " + allowedAttributes + " can be updated");
        }
        template.update("update User set " + attribute + " = ? where id = ?", newValue, userId);
        return user(userId);

    }

    public User updateUser(User updatedUser) {
        template.update("update User set firstName = ?, lastName = ?, password = ?, username = ? where id = ?",
            updatedUser.getFirstName(), updatedUser.getLastName(), updatedUser.getPassword(), updatedUser.getUsername(), updatedUser.getId());
        return updatedUser;
    }
}
