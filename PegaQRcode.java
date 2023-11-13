/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication14;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;

/**
 *
 * @author alan
 */
public class PegaQRcode {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, KeyStoreException, NoSuchAlgorithmException, CertificateException, UnrecoverableKeyException, KeyManagementException {


        String basicAuth = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0eXBlIjoiYWNjZXNzX3Rva2VuIiwiY2xpZW50SWQiOiJDbGllbnRfSWRfNjRlYjY1OWQ1ZGRhMWMxM2YyOWNkOThhOGJkMWU1ZTM5MTEwZTIzYyIsImFjY291bnQiOjE4ODk3NSwiYWNjb3VudF9jb2RlIjoiZGZhYzlkNmQzZWI0Njg2MTk5MWQ4ZTc1ZDNhNjdlODUiLCJzY29wZXMiOlsiY29iLnJlYWQiLCJjb2Iud3JpdGUiLCJjb2J2LnJlYWQiLCJjb2J2LndyaXRlIiwiZ24uYmFsYW5jZS5yZWFkIiwiZ24ucGl4LmV2cC5yZWFkIiwiZ24ucGl4LmV2cC53cml0ZSIsImduLnBpeC5zZW5kLnJlYWQiLCJnbi5yZXBvcnRzLnJlYWQiLCJnbi5yZXBvcnRzLndyaXRlIiwiZ24uc2V0dGluZ3MucmVhZCIsImduLnNldHRpbmdzLndyaXRlIiwiZ24uc3BsaXQucmVhZCIsImduLnNwbGl0LndyaXRlIiwicGF5bG9hZGxvY2F0aW9uLnJlYWQiLCJwYXlsb2FkbG9jYXRpb24ud3JpdGUiLCJwaXgucmVhZCIsInBpeC5zZW5kIiwicGl4LndyaXRlIiwid2ViaG9vay5yZWFkIiwid2ViaG9vay53cml0ZSJdLCJleHBpcmVzSW4iOjM2MDAsImNvbmZpZ3VyYXRpb24iOnsieDV0I1MyNTYiOiJncGkwREV1QWlkWkpDWjZwT2xQQ3NybXZObit5azhxVjFaeWpZeVJoU1VjPSJ9LCJpYXQiOjE2ODkxNjY0OTUsImV4cCI6MTY4OTE3MDA5NX0.88XfbBVUty3cVgxNMXpRGb-a_mhb43bI8n0s-BKkA3E";

        //Diretório em que seu certificado em formato .p12 deve ser inserido
//        System.setProperty("javax.net.ssl.keyStore", "C:\\ASNSOFTWARE\\homologacao-188975-asnpix.p12"); 
        
        
        FileInputStream fis = new FileInputStream("C:\\ASNSOFTWARE\\homologacao-188975-asnpix.p12"); 
 
            KeyStore keyStore = KeyStore.getInstance("PKCS12"); 
            keyStore.load(fis, "".toCharArray()); // Substitua "senhaDoCertificado" pela senha do seu certificado 
 
 
            KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm()); 
            kmf.init(keyStore, "".toCharArray()); // Substitua "senhaDoCertificado" pela senha do seu certificado 
 
 
            SSLContext sslContext = SSLContext.getInstance("TLS"); 
            sslContext.init(kmf.getKeyManagers(), null, null); 
 
            
//            RequestBody body = "{\r\n  \"calendario\": {\r\n    \"expiracao\": 3600\r\n  },\r\n  \"devedor\": {\r\n    \"cpf\": \"06020531538\",\r\n    \"nome\": \"alan soares nascimento\"\r\n  },\r\n  \"valor\": {\r\n    \"original\": \"10.45\"\r\n  },\r\n  \"chave\": \"financeiro@asnsoftware.com.br\",\r\n  \"solicitacaoPagador\": \"Informe o número ou identificador do pedido.\"\r\n}");
            
            
        URL url = new URL ("https://api-pix-h.gerencianet.com.br/v2/cob/"+"fc9a4366ff3d4964b5dbc6c91a872273"); //Para ambiente de Desenvolvimento              
        HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();
       
        conn.setDoOutput(true);
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("authorization", basicAuth);
    conn.setSSLSocketFactory(sslContext.getSocketFactory());
        
//         String input =  "{\r\n  \"calendario\": {\r\n    \"expiracao\": 3600\r\n  },\r\n  \"devedor\": {\r\n    \"cpf\": \"06020531538\",\r\n    \"nome\": \"alan soares nascimento\"\r\n  },\r\n  \"valor\": {\r\n    \"original\": \"10.45\"\r\n  },\r\n  \"chave\": \"financeiro@asnsoftware.com.br\",\r\n  \"solicitacaoPagador\": \"Informe o número ou identificador do pedido.\"\r\n}";
        
        
//        
//        OutputStream os = conn.getOutputStream();
//        os.write(0);
//        os.flush();     

        InputStreamReader reader = new InputStreamReader(conn.getInputStream());
        BufferedReader br = new BufferedReader(reader);

        String response;
        while ((response = br.readLine()) != null) {
          System.out.println(response);
        }
        
        
        conn.disconnect();

    }
        
 

}






