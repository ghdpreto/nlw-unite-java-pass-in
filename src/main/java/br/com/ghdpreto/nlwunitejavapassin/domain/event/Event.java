package br.com.ghdpreto.nlwunitejavapassin.domain.event;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.Normalizer;

@Entity
@Table(name = "events")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Event {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String details;

    @Column(nullable = false, unique = true)
    private String slug;

    @Column(nullable = false, name="maximum_attendees")
    private Integer maximumAttendees;

    public void createSlug(String text) {
        // normalizer
        // São Paulo -> Sa~o Paulo
        String normalized = Normalizer.normalize(text, Normalizer.Form.NFD);

        // remove os acentos
        this.slug =  normalized.replaceAll("[\\p{InCOMBINING_DIACRITICAL_MARKS}]", "")
                .replaceAll("[^\\w\\s]", "")
                .replaceAll("\\s+", "-")
                .toLowerCase();
    }
}
