package com.example.SOCApplication.Entity;


import com.example.SOCApplication.Entity.subEntity.ReportDocument;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "document")

//Entity that stores the uploaded document information
public class FileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String objectKey;

    private String fileName;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy ="document")
    private List<ReportDocument> reportDocument;

}
