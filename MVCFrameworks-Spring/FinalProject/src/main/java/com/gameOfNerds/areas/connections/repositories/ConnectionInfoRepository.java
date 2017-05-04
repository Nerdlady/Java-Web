package com.gameOfNerds.areas.connections.repositories;

import com.gameOfNerds.areas.connections.entities.ConnectionsInfo;
import com.gameOfNerds.areas.connections.entities.enums.ConnectionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConnectionInfoRepository extends JpaRepository<ConnectionsInfo,Long> {
    ConnectionsInfo findOneByConnectionType(ConnectionType connectionType);
}
