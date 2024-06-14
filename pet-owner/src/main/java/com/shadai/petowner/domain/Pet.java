package com.shadai.petowner.domain;

import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.CascadeType;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;

@Data
@Entity
@Table(name = "pets", schema = "petowner")
public class Pet implements Serializable{
    
    private static final long serialVersionUID = 1L;

    /**
     -GenerationType.IDENTITY is used. This instructs JPA to rely on the database's native auto-increment
     feature to generate unique IDs for each entity instance.
     */
    @Id
    @Column(name = "pet_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
     -GenerationType.IDENTITY	Relies on database's auto-increment functionality
     -GenerationType.SEQUENCE	Uses a database sequence object to generate IDs
     -GenerationType.TABLE	Uses a separate table to manage next available IDs
     -GenerationType.AUTO	JPA picks a suitable strategy based on database support
     */

    @Column(name = "name")
    private String name;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    /**
    -mappedBy: tells JPA how the relationship is mapped in the Visit entity. We can assume there's a field named "pet"
     (or similar) in the Visit class that holds a reference to the entity this code belongs to. 
    -orphanRemoval: this option instructs JPA that when an instance is removed from the collection (visits in this case),
     but not the owning entity (the class with this statement), the orphaned Visit object should be
      automatically deleted from the database.    
    -cascade: cascade = CascadeType.ALL This specifies that any persistence operations (save, update, delete) performed on this
     class should be cascaded to the associated Visit objects in the collection. This means when you save or update this class,
     the related Visit objects are also saved or updated.  
    */
    @OneToMany(mappedBy = "pet", orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<Visit> visits = new HashSet<>();
    /*Use Set when:
    -You need to ensure there are no duplicate visits for a pet.
    -Fast lookup performance is not a critical concern.
    Use List when:
    -The order of visits matters (e.g., maintaining chronological order of visits).
    -You specifically want to allow duplicate visits (unlikely in this case). */

    @ManyToOne
    @JoinColumn(name = "type_id")
    private PetType type;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

}
