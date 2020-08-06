package com.api.template.helpers;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.stereotype.Service;

import lombok.Data; 

@Data 
@Service
public class FtpClient {
 
    private String server;
    private int port;
    private String user;
    private String password;
    private FTPClient ftp;

    public FtpClient(){

    }

    public FtpClient(String server, int port, String user, String password){
        this.server = server;
        this.port = port;
        this.user = user;
        this.password = password;
    }
 
    void open() throws IOException {
        ftp = new FTPClient();
 
        ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
 
        ftp.connect(this.server, this.port);
        int reply = ftp.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)) {
            ftp.disconnect();
            throw new IOException("Exception in connecting to FTP Server");
        }
 
        ftp.login(this.user, this.password);
        ftp.enterLocalPassiveMode();
        ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
        ftp.setBufferSize(1024);
    }
 
    public void close() throws IOException {
        ftp.disconnect();
    }

    public boolean storeFile(String path, InputStream file) throws IOException{
        this.open();
        return this.ftp.storeFile(path, file);
    }

    public boolean deleteFile(String name) throws IOException{
        return this.ftp.deleteFile(name);
    }
}