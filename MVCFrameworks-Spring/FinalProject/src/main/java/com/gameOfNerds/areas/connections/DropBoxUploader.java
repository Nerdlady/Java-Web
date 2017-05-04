package com.gameOfNerds.areas.connections;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import com.dropbox.core.v2.users.FullAccount;
import com.gameOfNerds.areas.connections.models.DropBoxConnectionInfo;
import com.gameOfNerds.areas.connections.services.ConnectionInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Component
public class DropBoxUploader {

    private static ConnectionInfoService connectionInfoService;

    @Autowired
    public DropBoxUploader(ConnectionInfoService connectionInfoService) {
        DropBoxUploader.connectionInfoService = connectionInfoService;
    }

    public static String uploadImage(String username, MultipartFile file) throws DbxException {
        if (file == null || file.isEmpty()){
            return null;
        }
        DbxRequestConfig config = new DbxRequestConfig("Game Of Nerds");
        DropBoxConnectionInfo dropBoxConnectionInfo = connectionInfoService.getDropBoxInfo();
        DbxClientV2 client = new DbxClientV2(config, dropBoxConnectionInfo.getSecretKey());

        // Get current account info
        FullAccount account = client.users().getCurrentAccount();
//

        // Get files and folder metadata from Dropbox root directory
        ListFolderResult result = client.files().listFolder("/userspictures/");
        while (true) {
            for (Metadata metadata : result.getEntries()) {
                String pathLower = metadata.getPathLower();
                if (pathLower.equals("/userspictures/" + username.toLowerCase())){
                    client.files().delete("/userspictures/" + username);
                }

            }

            if (!result.getHasMore()) {
                break;
            }

            result = client.files().listFolderContinue(result.getCursor());
        }
        // Upload to Dropbox
        try (InputStream in = new ByteArrayInputStream(file.getBytes())) {

            FileMetadata metadata = client.files().uploadBuilder("/userspictures/" + username)
                    .uploadAndFinish(in);


        } catch (IOException e) {
            e.printStackTrace();
        }


        return client.files().getTemporaryLink("/userspictures/" + username).getLink();
    }
}
