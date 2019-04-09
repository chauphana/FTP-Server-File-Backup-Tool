package io.github.chauphana;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.io.ByteArrayInputStream;
import org.apache.commons.io.FileUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {

        FTPClient client = new FTPClient();
        //https://stackoverflow.com/questions/11202215/how-to-copy-a-file-on-the-ftp-server-to-a-directory-on-the-same-server-in-java

        String hostname = "";
        String username = "";
        String password = "";
        Scanner input = new Scanner(System.in);

        System.out.println("Enter hostname: ");
        hostname = input.nextLine();

        System.out.println("Enter Username: ");
        username = input.nextLine();

        System.out.println("Enter password (this is safe i swear): ");
        password = input.nextLine();



        try {
//            client. connect(hostname);
//            client.login(username, password);

            client. connect(hostname);
            client.login(username, password);
            System.out.println(client.getStatus());
            if (client.isConnected()) {
                System.out.println("is connected: " + client.isConnected());
                System.out.println(client.getStatus());

                System.out.println(client.getStatus("/plugins"));

                client.enterLocalPassiveMode();
                client.setFileType(FTP.BINARY_FILE_TYPE);
                String fileLocation = "eula.txt";
                File file = new File(fileLocation);
                //ByteArrayInputStream(FileUtils.readFileToByteArray(fileLocation));
                FileInputStream inputStream = new FileInputStream(new File(fileLocation));
                boolean result = client.storeFile("/backups/eula.txt", inputStream);
                inputStream.close();
                if (result) {
                    System.out.println("upload done");
                }

            }
            client.logout();


        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            try {
                client.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }



}
