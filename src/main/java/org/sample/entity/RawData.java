package org.sample.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.sample.utility.Status;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@IdClass(RawData.Keys.class)
@Data
@Entity // This tells Hibernate to make a table out of this class
public class RawData implements Serializable{
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Integer id;


  @Id
  private String app;

  private String dataType;

  private String data;

  @Column(name = "json_input", columnDefinition = "json")
  private String jsonData;

  @UpdateTimestamp
  private LocalDateTime updatedTime;
  @CreationTimestamp
  private LocalDateTime createTime;

  private String status = Status.ACTIVE.getMsg();

  public static class Keys implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String app;

  }

}
