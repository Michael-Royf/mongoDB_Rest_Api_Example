package com.michael.project.payload.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserResponse {
    private String id;
    private String firstName;
    private String lastName;
    private String username;
    private String  email;
    private String password;
}
