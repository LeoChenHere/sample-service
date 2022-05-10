package org.sample.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity // This tells Hibernate to make a table out of this class
public class RawData {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Integer id;

  @Column(name = "json_input", columnDefinition = "json")
  private String jsonData;

  private String dataType;

  @UpdateTimestamp
  private LocalDateTime updatedTime;
  @CreationTimestamp
  private LocalDateTime createTime;

  private String status = "active";

}
