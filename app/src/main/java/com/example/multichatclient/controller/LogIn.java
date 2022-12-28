package com.example.multichatclient.controller;

import android.os.AsyncTask;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class LogIn extends AsyncTask<String, Void, Void> {
    Socket s;
    DataOutputStream sender;
    DataInputStream reciver;
    PrintWriter pw;

    @Override
    protected Void doInBackground (String... voids) {
        try {
            String message = voids[0] + "\r\n" + voids[1];
            s = new Socket ("192.168.1.3",12000);
            pw = new PrintWriter(s.getOutputStream());
            pw.write(message);
            pw.flush();
            pw.close();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
