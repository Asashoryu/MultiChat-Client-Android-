package com.mcc.multichatclone.controller;

import android.text.BoringLayout;

import com.mcc.multichatclone.model.Gruppo;
import com.mcc.multichatclone.model.Messaggio;
import com.mcc.multichatclone.viewcontroller.ChatViewModel;
import com.mcc.multichatclone.viewcontroller.CreaGruppoViewModel;
import com.mcc.multichatclone.viewcontroller.GruppiViewModel;
import com.mcc.multichatclone.viewcontroller.LoginViewModel;
import com.mcc.multichatclone.viewcontroller.NotificaViewModel;
import com.mcc.multichatclone.viewcontroller.RegistrazioneViewModel;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.Buffer;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Controller {
    private static final String LOGIN = "100";
    private static final String SIGNIN = "200";
    private static final String CREAGRUP = "300";
    private static final String SENDMESS = "400";
    private static final String SEARCHGRUP = "500";
    private static final String SENDNOTIFICA = "600";
    private static final String ACCETTAUT = "700";
    // comandi OK (server)
    private static final String LOGINOK = "111";
    private static final String SIGNINOK = "211";
    private static final String CREAGRUPOK = "311";
    private static final String SENDMESSOK = "411";
    private static final String SEARCHGRUPOK = "511";
    private static final String SENDNOTIFICAOK =  "611";
    private static final String ACCETTAUTOK = "711";
    // comandi ERROR (server)
    private static final String LOGINERR = "112";
    private static final String SIGNINERR = "212";
    private static final String CREAGRUPERR = "312";
    private static final String SENDMESSERR = "412";
    private static final String SEARCHGRUPERR = "512";
    private static final String SENDNOTIFICAERR =  "612";
    private static final String ACCETTAUTERR = "712";
    // 3
    private static final String LOGINNONTROVATO = "113";
    private static final String SIGNGIAREGISTRATO = "213";
    private static final String CREAGRUPGIAREGISTRATO = "313";
    private static Socket socket = null;
    private static final String indirizzoServer = "192.168.1.145";
    private static final int portaServer = 10000;
    private static String pacchetto = null;
    // comandi (client)

    private ArrayList<Gruppo> gruppiController;
    private Gruppo gruppoNavigato;
    private static Controller controller = null;

    private static String nome = "";
    private static String password = "";

    private static Boolean ascolta = false;

    private static LoginViewModel loginModel;

    private static RegistrazioneViewModel registrazioneModel;

    private static GruppiViewModel gruppiModel;

    private static CreaGruppoViewModel creaGruppoModel;
    private static ChatViewModel chatModel;

    private static NotificaViewModel notificaModel;



    public Controller() {
        gruppiController = new ArrayList<>();
        setAscoltaFalse();
        setAscoltaTrue();

        gruppoNavigato = null;
    }

    public static Controller getNewInstance() {
        controller = null;
        controller = new Controller();
        return controller;
    }

    public static Controller getInstance() {
        if (controller == null) {
            controller = new Controller();
        }
        return controller;
    }

    private static Socket getSocketInstance() throws IOException {
        if (socket == null) {
            socket = new Socket();
            InetSocketAddress sockAdr = new InetSocketAddress(indirizzoServer, portaServer);
            socket.connect(sockAdr, 5000);
        }
        return socket;
    }
    private static Socket getNewSocketInstance() throws IOException {
        if( socket != null) {
            socket.close();
        }
        socket = new Socket();
        InetSocketAddress sockAdr = new InetSocketAddress(indirizzoServer, portaServer);
        socket.connect(sockAdr, 2000);
        return socket;
    }

    public boolean isConnected() {
        return socket.isConnected();
    }

    public void login(String nome, String password) throws Exception {
        String messaggio = "\r\ncmd=" + LOGIN + "\r\nnome=" + nome + "\r\npassword=" + password + "\r\n\r\n";
        Thread t = new Thread(new Runnable() {
            @Override
            public void run()  {
                try {
                    System.err.println("Superato il while della login");
                    PrintWriter output = new PrintWriter(socket.getOutputStream());
                    output.write(messaggio);
                    output.flush();
                } catch (IOException e) {
                    System.out.println("login non riuscito socket chiusa");
                }
            }
        });
        // serve per creare una sincronizzazione pezzotta con l'attivazione della socket
        t.sleep(25);
        t.start();
        t.join();
        setNome(nome);
        setPassword(password);
    }

    public void signin(String nome, String password) throws Exception {
        String messaggio = "\r\ncmd=" + SIGNIN + "\r\nnome=" + nome + "\r\npassword=" + password + "\r\n\r\n";
        Thread t = new Thread(new Runnable() {
            @Override
            public void run()  {
                try {
                    System.err.println("Superato il while della signin");
                    PrintWriter output = new PrintWriter(socket.getOutputStream());
                    output.write(messaggio);
                    output.flush();
                } catch (IOException e) {
                    System.out.println("signin non riuscito socket chiusa");
                }
            }
        });
        // serve per creare una sincronizzazione pezzotta con l'attivazione della socket
        t.sleep(25);
        t.start();
        t.join();
        setNome(nome);
        setPassword(password);
    }
    public void creaGruppo(String gruppo) throws InterruptedException {
        String utente = getNome();
        String messaggio = "\r\ncmd=" + CREAGRUP + "\r\ngruppo=" + gruppo + "\r\nutente=" + utente + "\r\n\r\n";
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while(socket == null || !socket.isConnected());
                    PrintWriter output = new PrintWriter(socket.getOutputStream());
                    output.write(messaggio);
                    output.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
        t.join();
    }

    public void sendMessaggio(String contenuto) throws InterruptedException {
        String minutaggio = Long.toString(Instant.now().toEpochMilli());
        String gruppo = getGruppoNavigato().getNome();
        String utente = getNome();
        String messaggio = "\r\ncmd=" + SENDMESS + "\r\ngruppo=" + gruppo + "\r\nutente=" + utente + "\r\nmessaggio=" + contenuto +"\r\nminutaggio=" + minutaggio + "\r\n\r\n";
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while(socket == null || !socket.isConnected());
                    PrintWriter output = new PrintWriter(socket.getOutputStream());
                    output.write(messaggio);
                    output.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
        t.join();
    }

    public void cercaGruppo(String gruppo) throws InterruptedException {
        String utente = getNome();
        String messaggio = "\r\ncmd=" + SEARCHGRUP + "\r\ngruppo=" + gruppo + "\r\nutente=" + utente + "\r\n\r\n";
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while(socket == null || !socket.isConnected());
                    PrintWriter output = new PrintWriter(socket.getOutputStream());
                    output.write(messaggio);
                    output.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
        t.join();
    }

    public void mandaNotifica(String gruppo) throws InterruptedException {
        String utente = getNome();
        String messaggio = "\r\ncmd=" + SENDNOTIFICA + "\r\ngruppo=" + gruppo + "\r\nutente=" + utente + "\r\n\r\n";
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while(socket == null || !socket.isConnected());
                    PrintWriter output = new PrintWriter(socket.getOutputStream());
                    output.write(messaggio);
                    output.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
        t.join();
    }

    public void accettaNotifica(String gruppo, String richiedente) throws InterruptedException {
        String utente = getNome();
        String messaggio = "\r\ncmd=" + ACCETTAUT + "\r\ngruppo=" + gruppo + "\r\nutente=" + utente + "\r\nrichiedente=" + richiedente +"\r\n\r\n";
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while(socket == null || !socket.isConnected());
                    PrintWriter output = new PrintWriter(socket.getOutputStream());
                    output.write(messaggio);
                    output.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
        t.join();
    }

    public void ascolta() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    socket = getNewSocketInstance();
                    DataInputStream input = new DataInputStream(socket.getInputStream());
                    BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    while (ascolta == true) {
                        if (socket != null && !socket.isClosed()) {
                            String line = "";
                            String tmp = "";
                            while(br.ready() && (line = br.readLine()) != null) {
                                tmp = tmp + line + "\r\n";
                            }
                            if (tmp != null && !tmp.equals("")) {
                                interpreta(tmp);
                            }
                        }
                        else {
                            System.out.println("Socket chiusa");
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    //throw new Exception("Errore di connessione alla socket");
                }
            }
        });
        t.start();
    }

    private void interpreta(@NotNull String pacchetto) {
        String intestazione = "";
        String codice = "";
        String codiceMessaggio = "";

        System.out.println(pacchetto);

        intestazione = getIntestazione(pacchetto);
        codice = getCodice(intestazione);
        codiceMessaggio = getCodiceMessaggio(intestazione);

        System.out.println("codice messaggio : " + codiceMessaggio);

        if (codice.equals(LOGINOK)) {
            //TODO: scrivere il codice di quando il login è avvenuto con successo
            String body = getBody(pacchetto);
            String gruppi = getGruppi(body);
            String messaggi;
            ArrayList<String> listaGruppi = getListaGruppi(gruppi);
            for (String gruppo : listaGruppi) {
                Gruppo nuovoGruppo = new Gruppo(getNomeGruppo(gruppo));
                gruppiController.add(nuovoGruppo);
                messaggi = getMessaggi(gruppo);
                ArrayList<String> listaMessaggi = getListaMessaggi(messaggi);
                for (String messaggio: listaMessaggi) {
                    Messaggio nuovoMessaggio = new Messaggio(getMittenteMessaggio(messaggio), getContenutoMessaggio(messaggio), getMinutaggioMessaggio(messaggio));
                    nuovoGruppo.getMessaggi().add(nuovoMessaggio);
                }
                //TODO:fare lo stesso for ma per le notifiche
            }

            loginModel.setLoggato("true");
        }

        if (codice.equals(SIGNINOK)) {
            registrazioneModel.setRegistrato("true");
        }

        if (codice.equals(CREAGRUPOK)) {
            String body = getBody(pacchetto);
            String gruppi = getGruppi(body);
            ArrayList<String> listaGruppi = getListaGruppi(gruppi);
            String gruppo = listaGruppi.get(0);
            String nomeGruppo = getNomeGruppo(gruppo);

        }

        if (codice.equals(SENDMESSOK)) {
            String body = getBody(pacchetto);
            String gruppi = getGruppi(body);
            String messaggi;
            ArrayList<String> listaGruppi = getListaGruppi(gruppi);
            String gruppo = listaGruppi.get(0);
            String nomeGruppo = getNomeGruppo(gruppo);
            messaggi = getMessaggi(gruppo);
            ArrayList<String> listaMessaggi = getListaMessaggi(messaggi);
            String messaggio = listaMessaggi.get(0);
            String mittente = getMittenteMessaggio(messaggio);
            String contenuto = getContenutoMessaggio(messaggio);
            String minutaggio = getMinutaggioMessaggio(messaggio);

            List<Gruppo> lista = gruppiController.stream().filter(g -> g.getNome().equals(nomeGruppo)).collect(Collectors.toList());
            Gruppo gruppoAggiornato = lista.get(0);
            gruppoAggiornato.getMessaggi().add(new Messaggio(mittente, contenuto, minutaggio));

            if (gruppoAggiornato == getGruppoNavigato()) {
                chatModel.aggiorna();
                chatModel.setRicevutoMessaggio("true");
            }
        }

        if (codice.equals(SEARCHGRUPOK)) {

        }

        if (codice.equals(SENDNOTIFICAOK)) {

        }

        if (codice.equals(ACCETTAUTOK)) {

        }

        if (codice.equals(LOGINERR)) {
            System.err.println("LOGINERR con codice messaggio: " + codiceMessaggio);
            loginModel.setLoggato(codiceMessaggio);
        }

        if (codice.equals(SIGNINERR)) {
            registrazioneModel.setRegistrato(codiceMessaggio);
        }

        if (codice.equals(CREAGRUPERR)) {

        }

        if (codice.equals(SENDMESSERR)) {
            chatModel.setRicevutoMessaggio(codiceMessaggio);
        }

        if (codice.equals(SEARCHGRUPERR)) {
            registrazioneModel.setRegistrato(codiceMessaggio);
        }

        if (codice.equals(SENDNOTIFICAERR)) {

        }

        if (codice.equals(ACCETTAUTERR)) {

        }

        if (codice.equals(LOGINNONTROVATO)) {
            loginModel.setLoggato(codiceMessaggio);
        }

        if (codice.equals(SIGNGIAREGISTRATO)) {
            registrazioneModel.setRegistrato(codiceMessaggio);
        }

        if (codice.equals(CREAGRUPGIAREGISTRATO)) {

        }
    }

    private static String getIntestazione(String pacchetto) {
        int inizio;
        int fine;

        inizio = pacchetto.indexOf("<head>");
        fine = pacchetto.indexOf("</head>");

        String intestazione = pacchetto.substring(inizio + "<head>\r\n".length(), fine);

        return intestazione;
    }

    private static String getCodice(String intestazione) {
        String[] token;
        int indice;
        String codice = "";

        token = intestazione.split("\r\n");

        indice = token[0].indexOf("=");
        codice = token[0].substring(indice + 1);
        return codice;
    }

    private static String getCodiceMessaggio(String intestazione) {
        String[] token;
        int indice;
        String messaggio = "";

        token = intestazione.split("\r\n");

        indice = token[1].indexOf("=");
        messaggio = token[1].substring(indice + 1);
        return messaggio;
    }

    private static String getBody(String pacchetto) {
        int inizio;
        int fine;

        inizio = pacchetto.indexOf("<body>");
        fine = pacchetto.lastIndexOf("</body>");

        String body = pacchetto.substring(inizio + "<body>\r\n".length(), fine);

        return body;
    }

    private static String getGruppi(String pacchetto) {
        int inizio;
        int fine;

        inizio = pacchetto.indexOf("<gruppi>");
        fine = pacchetto.lastIndexOf("</gruppi>");

        String gruppi = pacchetto.substring(inizio + "<gruppi>\r\n".length(), fine);

        return gruppi;
    }

    private static ArrayList<String> getListaGruppi(String gruppi) {
        int inizio;
        int fine;

        ArrayList<String> listaGruppi = new ArrayList<>();
        while ((inizio = gruppi.indexOf("<gruppo>")) != -1 && (fine = gruppi.indexOf("</gruppo>")) != -1) {
            String gruppo = gruppi.substring(inizio + "<gruppo>\r\n".length(), fine);
            System.out.println("gruppo : " + gruppo + "\n\n");
            listaGruppi.add(gruppo);
            gruppi = gruppi.substring(fine + "</gruppo>\r\n".length());
        }

        return listaGruppi;
    }

    private static String getMessaggi(String gruppo) {
        int inizio;
        int fine;

        inizio = gruppo.indexOf("<messaggi>");
        fine = gruppo.lastIndexOf("</messaggi>");

        String messaggi = gruppo.substring(inizio + "<messaggi>\r\n".length(), fine);

        return messaggi;
    }

    private static ArrayList<String> getListaMessaggi(String messaggi) {
        int inizio;
        int fine;

        ArrayList<String> listaMessaggi = new ArrayList<>();
        while ((inizio = messaggi.indexOf("<messaggio>")) != -1 && (fine = messaggi.indexOf("</messaggio>")) != -1) {
            String messaggio = messaggi.substring(inizio + "<messaggio>\r\n".length(), fine);
            listaMessaggi.add(messaggio);
            messaggi = messaggi.substring(fine + "</messaggio>\r\n".length());
        }

        return listaMessaggi;
    }

    private static String getNomeGruppo(String gruppo) {
        String[] token;
        int indice;
        String nome = "";

        token = gruppo.split("\r\n");

        indice = token[0].indexOf("=");
        nome = token[0].substring(indice + 1);
        return nome;
    }

    private static String getMittenteMessaggio(String messaggio) {
        String[] token;
        int indice;
        String mittente = "";

        token = messaggio.split("\r\n");

        indice = token[0].indexOf("=");
        mittente = token[0].substring(indice + 1);
        return mittente;
    }

    private static String getContenutoMessaggio(String messaggio) {
        String[] token;
        int indice;
        String contenuto = "";

        token = messaggio.split("\r\n");

        indice = token[1].indexOf("=");
        contenuto = token[1].substring(indice + 1);
        return contenuto;
    }

    private static String getMinutaggioMessaggio(String messaggio) {
        String[] token;
        int indice;
        String minutaggio = "";

        token = messaggio.split("\r\n");

        indice = token[2].indexOf("=");
        minutaggio = token[2].substring(indice + 1);
        return minutaggio;
    }

    public void setAscoltaTrue() {
        ascolta = true;
        ascolta();
    }

    public void setAscoltaFalse() {
        ascolta = false;
    }
    public static void setLoginModel(LoginViewModel loginModel) {
        Controller.loginModel = loginModel;
    }

    public static void setChatModel(ChatViewModel chatModel) {
        Controller.chatModel = chatModel;
    }

    public static void setRegistrazioneModel(RegistrazioneViewModel registrazioneModel) {
        Controller.registrazioneModel = registrazioneModel;
    }

    public static void setgruppiModel(GruppiViewModel gruppiModel) {
        Controller.gruppiModel = gruppiModel;
    }

    public static void setcreaGruppoModel(CreaGruppoViewModel creaGruppoModel) {
        Controller.creaGruppoModel = creaGruppoModel;
    }
    public static void setchatModel(ChatViewModel chatModel) {
        Controller.chatModel = chatModel;
    }

    public static void setnotificaModel(NotificaViewModel notificaModel) {
        Controller.notificaModel = notificaModel;
    }

    public ArrayList<Gruppo> getGruppi() {
        return gruppiController;
    }

    public void setGruppi(ArrayList<Gruppo> gruppi) {
        this.gruppiController = gruppi;
    }

    public Gruppo getGruppoNavigato() {
        return gruppoNavigato;
    }

    public void setGruppoNavigato(Gruppo gruppoNavigato) {
        this.gruppoNavigato = gruppoNavigato;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
