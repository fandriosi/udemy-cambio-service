package com.andriosi.udemy.cambioservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.Builder;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cambio implements Serializable {
    private  static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "from_currency", nullable = false, length = 3)
    private String from;
    @Column(name = "to_currency", nullable = false, length = 3)
    private String to;
    @Column(name = "conversion_factor", nullable = false)
    private BigDecimal convertionFactor;
    @Transient
    private BigDecimal convertedValue;
    @Transient
    private String enviroment;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cambio cambio = (Cambio) o;
        return Objects.equals(id, cambio.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
