package com.ysol.ptapp.parentteacherservices.child;

import com.ysol.ptapp.parentteacherservices.parent.Parent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"firstName", "lastName", "parent_id"})})
public class Child {
    @GeneratedValue
    @Id
    private Long id;

    private String firstName;

    private String lastName;

    @OneToOne
    @JoinColumn(name = "parent_id")
    private Parent parent;
}
