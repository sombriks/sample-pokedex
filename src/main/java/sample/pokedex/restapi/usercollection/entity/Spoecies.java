package sample.pokedex.restapi.usercollection.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Entity
@Table(name="species")
public class Spoecies {
    @NotNull
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "updated", nullable = false)
    private LocalDateTime updated;
    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;
    @Size(max = 255)
    @Column(name = "image_url")
    private String imageUrl;
    @Size(max = 255)
    @NotNull
    @Column(name = "name", nullable = false)
    private String name;
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }
}
