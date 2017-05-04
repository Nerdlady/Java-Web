package com.gameOfNerds.areas.connections.services;

import com.gameOfNerds.areas.connections.entities.ConnectionsInfo;
import com.gameOfNerds.areas.connections.entities.enums.ConnectionType;
import com.gameOfNerds.areas.connections.models.DropBoxConnectionInfo;
import com.gameOfNerds.areas.connections.models.GmailConnectionInfo;
import com.gameOfNerds.areas.connections.repositories.ConnectionInfoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConnectionInfoServiceImpl implements ConnectionInfoService {
    private final ConnectionInfoRepository connectionInfoRepository;
    private final ModelMapper modelMapper;
    @Autowired
    public ConnectionInfoServiceImpl(ConnectionInfoRepository connectionInfoRepository, ModelMapper modelMapper) {
        this.connectionInfoRepository = connectionInfoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public DropBoxConnectionInfo getDropBoxInfo() {
        ConnectionsInfo connectionsInfo = this.connectionInfoRepository.findOneByConnectionType(ConnectionType.DROP_BOX);
        DropBoxConnectionInfo dropBoxConnectionInfo = this.modelMapper.map(connectionsInfo,DropBoxConnectionInfo.class);
        return dropBoxConnectionInfo;
    }

    @Override
    public GmailConnectionInfo getGmailInfo() {
        ConnectionsInfo connectionsInfo = this.connectionInfoRepository.findOneByConnectionType(ConnectionType.GOOGLE_EMAIL);
        GmailConnectionInfo gmailConnectionInfo = this.modelMapper.map(connectionsInfo,GmailConnectionInfo.class);
        return gmailConnectionInfo;
    }
}
