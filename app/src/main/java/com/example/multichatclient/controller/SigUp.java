package com.example.multichatclient.controller;

import android.os.AsyncTask;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class SigUp extends AsyncTask <String, Void, Void> {
    Socket s;
    PrintWriter pw;

    @Override
    protected Void doInBackground (String... voids) {
        try {
            String message = "cmd=sigup\r\nnome=" + voids[0] + "\r\npassword=" + voids[1]+ "\r\n";
            s = new Socket ("192.168.1.3",10000);
            pw = new PrintWriter(s.getOutputStream());
            pw.write(message);
            pw.flush();
            pw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
