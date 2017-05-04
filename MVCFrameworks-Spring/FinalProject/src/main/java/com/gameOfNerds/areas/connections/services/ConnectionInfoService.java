package com.gameOfNerds.areas.connections.services;

import com.gameOfNerds.areas.connections.models.DropBoxConnectionInfo;
import com.gameOfNerds.areas.connections.models.GmailConnectionInfo;

public interface ConnectionInfoService {
    DropBoxConnectionInfo getDropBoxInfo();
    GmailConnectionInfo getGmailInfo();
}
