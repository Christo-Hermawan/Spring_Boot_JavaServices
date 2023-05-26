package demo.tesSwagger.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;


import javax.persistence.ParameterMode;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity

@NamedStoredProcedureQuery (name = "userEntity.ProcQueryUtm", 
        procedureName = "ProcQueryUtm", parameters = {

            @StoredProcedureParameter(mode = ParameterMode.IN, name = "TransactionName", type = String.class),

            @StoredProcedureParameter(mode = ParameterMode.IN, name = "utm_source", type = String.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "utm_medium", type = String.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "utm_campaign", type = String.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "utm_content", type = String.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "utm_term", type = String.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "type", type = String.class),
    
            @StoredProcedureParameter(mode = ParameterMode.OUT, name = "count_out", type = Integer.class)
        },
        //Mapping userEntity
        resultClasses = { userEntity.class }
        
        )

@Table(name = "ASK_KPR")
public class userEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_ASK_KPR")
    private UUID id;

    @Column(name =  "NAMA")
    private String nama;

    @Column(name =  "NO_TLP")
    private String no_tlp;

    @Column(name =  "EMAIL")
    private String email;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "URL")
    private String url;

    @Column(name = "QUESTION")
    private String question;

    public userEntity() {
    }

   
}

    