package com.mentorship.demo.security;

import com.mentorship.demo.model.Abiturient;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class JwtAbiturientFactory {

    public static JwtAbiturient create(Abiturient abiturient) {
        return new JwtAbiturient(
                abiturient.getFirstName(),
                abiturient.getPassword(),
                null);
//                mapToGrantedAuthority(Arrays.asList(abiturient.getRole())));
    }

//    private static List<? extends GrantedAuthority> mapToGrantedAuthority(List<Role> roles) {
//        return roles.stream()
//                .map(role -> new SimpleGrantedAuthority(role.name()))
//                .collect(Collectors.toList());
//    }
}
