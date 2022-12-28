package com.example.multichatclient.controller;

import androidx.annotation.NonNull;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Controller implements Serializable {

    private Socket sockLog;
    private DataInputStream input;
    private DataOutputStream output;


    public void log_in (@NonNull String nome,@NonNull String password) throws Exception {
        String mess;
        mess = nome + "\r\n" + password;
        try {
            sockLog = new Socket(InetAddress.getLocalHost().getHostAddress(),5678);
            input = new DataInputStream(System.in);
            output = new DataOutputStream(sockLog.getOutputStream());
            output.writeUTF(mess);
            output.flush();
            output.close();
        }
        catch (UnknownHostException u) {

        }
        catch (IOException i) {

        }
        catch (SecurityException s) {

        }
        catch (IllegalArgumentException i) {

        }
    }
    public void register (String nome, String password) {

    }
}
