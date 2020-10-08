package ru.zneik.mapapi.to;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class UserAuthTO {
    @NotBlank
    private String username;
    @NotBlank
    private String password;

}
