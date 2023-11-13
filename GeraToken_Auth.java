package javaapplication14;

//Desenvolvido pela Consultoria Técnica da Gerencianet

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.util.Base64;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

public class GeraToken_Auth {
    public static void main(String[] args) throws Exception {
        String client_id = "Client_Id_64eb659d5dda1c13f29cd98a8bd1e5e39110e23c";
        String client_secret = "Client_Secret_b738811b7ec4350dcee03283043a28c51a66c94f";;
        String basicAuth = Base64.getEncoder().encodeToString(((client_id+':'+client_secret).getBytes()));
        System.out.println(basicAuth);
        //Diretório em que seu certificado em formato .p12 deve ser inserido
        System.setProperty("javax.net.ssl.keyStore", "C:\\ASNSOFTWARE\\homologacao-188975-asnpix.p12"); 
        SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        
        URL url = new URL ("https://api-pix-h.gerencianet.com.br/oauth/token"); //Para ambiente de Desenvolvimento              
        HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Authorization", "Basic "+ basicAuth);
        conn.setSSLSocketFactory(sslsocketfactory);
        String input = "{\"grant_type\": \"client_credentials\"}";
       
        OutputStream os = conn.getOutputStream();
        os.write(input.getBytes());
        os.flush();     

        InputStreamReader reader = new InputStreamReader(conn.getInputStream());
        BufferedReader br = new BufferedReader(reader);

        String response;
        while ((response = br.readLine()) != null) {
          System.out.println(response);
        }
        conn.disconnect();

    }
}