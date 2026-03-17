package com.example.eventtix.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString(exclude = "sector")
@EqualsAndHashCode(exclude = "sector")
@Table(name = "seats", uniqueConstraints = {
    @UniqueConstraint(name = "uq_seats_sector_row_number", columnNames = {"sector_id", "row_number", "number"})
})
public class Seat {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(nullable = false)
  private Integer number;

  @Column(nullable = false)
  private Integer rowNumber;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "sector_id", nullable = false)
  private Sector sector;
}
