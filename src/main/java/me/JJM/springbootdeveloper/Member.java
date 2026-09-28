package me.JJM.springbootdeveloper;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;

@Entity
@Getter
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = Generation.IDENTITY)
    private Long id;

    @Colum (nullable = false)
    private String name;
    private String email;

    public Member(String name, String email) {
        this.name = name;
        this.email = email;
    }
}