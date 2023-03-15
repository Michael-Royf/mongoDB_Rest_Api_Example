package com.michael.project.payload.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserRequest {

    private String firstName;

    private String lastName;

    private String username;

    private String  email;

    private String password;
}
