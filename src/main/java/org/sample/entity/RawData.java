package org.sample.entity;

import lombok.Data;
import org.hibernate.annotations.*;
import org.sample.utility.Status;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

// composite keys of table setup 2
@IdClass(RawData.Keys.class)
@Data
@Entity // This tells Hibernate to make a table out of this class
@SelectBeforeUpdate // to avoid unnecessary updates
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = { "app", "dataType", "status" })
})
public class RawData implements Serializable{
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Integer id;

  // composite keys of table setup 3
  @Id
  private String app;

  private String dataType;

  private String data;

  // json field setup here
  @Column(name = "json_input", columnDefinition = "json")
  private String jsonData;

  @UpdateTimestamp
  private LocalDateTime updatedTime;
  @CreationTimestamp
  private LocalDateTime createTime;

  private String status = Status.Active.getMsg();

  // composite key : setup keys of table here to ensure you can set @Id in particular field.
  // composite keys of table setup 1
  public static class Keys implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String app;

  }

}
