package com.gameOfNerds.areas.connections.entities;

import com.gameOfNerds.areas.connections.entities.enums.ConnectionType;

import javax.persistence.*;

@Entity
@Table(name = "conntections_info")
public class ConnectionsInfo {
    private Long id;
    private ConnectionType connectionType;
    private String email;
    private String secretKey;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Enumerated(EnumType.STRING)
    public ConnectionType getConnectionType() {
        return connectionType;
    }

    public void setConnectionType(ConnectionType connectionType) {
        this.connectionType = connectionType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}
