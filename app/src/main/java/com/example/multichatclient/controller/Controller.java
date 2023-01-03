package com.example.multichatclient.controller;

import android.app.Application;

import androidx.annotation.NonNull;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class Controller extends Application {

    Socket s;
    PrintWriter pw;
    static boolean error = false;


    public void connetti() throws Exception {
        error = false;
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (s == null || !s.isConnected()) {
                        s = new Socket();
                        InetSocketAddress sockAdr = new InetSocketAddress("192.168.1.3", 10000);
                        s.connect(sockAdr, 2000);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    error = true;
                }
            }
        });
        t.start();
        t.join();
        if (error) {
            throw new SocketTimeoutException();
        }
    }

    public void log_in(@NonNull String nome, @NonNull String password) throws InterruptedException {
        String message = "cmd=login\r\nnome=" + nome + "\r\npassword=" + password + "\r\n";
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    pw = new PrintWriter(s.getOutputStream());
                    pw.write(message);
                    pw.flush();
                    pw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
        t.join();

    }

    public void register(String nome, String password) throws InterruptedException {
        String message = "cmd=register\r\nnome=" + nome + "\r\npassword=" + password+ "\r\n";
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    pw = new PrintWriter(s.getOutputStream());
                    pw.write(message);
                    pw.flush();
                    pw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
        t.join();
    }
}
