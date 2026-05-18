
package com.example.login_auth_api.domain.project;

import com.example.login_auth_api.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "affiliation_requests")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    private AffiliationStatus status;
}