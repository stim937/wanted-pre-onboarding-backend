package wanted.preonboarding.backend.domain;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id", updatable = false)
	private Long id;

	@Column(name = "user_name", nullable = false)
	private String name;

	@Column(name = "user_email", nullable = false)
	private String email;
	
	@OneToMany(mappedBy = "user")
	private Set<Application> applications;
}
