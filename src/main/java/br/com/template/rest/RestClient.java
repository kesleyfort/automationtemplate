package br.com.template.rest;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Properties;
import java.util.Random;

@Component
public class RestClient {

    Environment env;
    String adminAccessToken;
    String loggedInUserGuid;
    String cpf;
    public RestClient(Environment env) throws Exception {
        this.env = env;
        if(Objects.equals(env.getProperty("env"), "dev")){
            Unirest.config().defaultBaseUrl(env.getProperty("DevUrl"));
            adminLogin();
            if(checkIfUserExists().equals("0")){
                generateCPF();
                createTestUser();
                getLoggedInUserGuid();
                resetPassword();
            } else {
                getLoggedInUserGuid();
                resetPassword();
            }


        }
        else if(Objects.equals(env.getProperty("env"), "prod")) {
            Unirest.config().defaultBaseUrl(env.getProperty("ProdUrl"));
        }
        else
            throw new Exception("Environment isn't defined");
    }
    private void generateCPF(){
     cpf = Unirest.post("https://www.4devs.com.br/ferramentas_online.php")
                .header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                .body("acao=gerar_cpf&pontuacao=S&cpf_estado=")
                .asString().getBody();
     new Properties().setProperty("CPF_REGISTER", cpf);

    }
    public String generateVoucher(){
        String code = "voucher" + new Random().nextInt();
        String authorization = String.format("Bearer %s", adminAccessToken);
        HttpResponse<String> response = Unirest.post("/credit/consult/personal-vouchers")
                .header("Content-Type", "application/json")
                .header("Authorization", authorization)
                .body("{ \"owner_id\": \"" + loggedInUserGuid + "\", \"code\": \""+ code + "\", \"expiration_date\": \"2023-08-05T01:21:31.127Z\" }")
                .asString()
                .ifFailure(resp -> {
                    try {
                        throw new Exception(String.format("Failure to login as admin -> %s - %s", resp.getStatus(), resp.getBody()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
        return new Gson().fromJson(response.getBody(), JsonObject.class).getAsJsonObject("data").isJsonObject() ? code : null;
    }
    private void adminLogin() {
        HttpResponse<String> response = Unirest.post("/auth/local")
                .header("Content-Type", "application/json")
                .body("{\n  \"email\": \"alves.kesley@gmail.com\",\n  \"password\": \"Mxm*c2@_\"\n}")
                .asString()
                .ifFailure(resp -> {
                    try {
                        throw new Exception(String.format("Failure to login as admin -> %s - %s", resp.getStatus(), resp.getBody()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
        adminAccessToken = new Gson().fromJson(response.getBody(), JsonObject.class).get("access_token").getAsString();
    }
    private void getLoggedInUserGuid() {
        String url = String.format("/users?email=%s", env.getProperty("EMAIL_REGISTER"));
        String authorization = String.format("Bearer %s", adminAccessToken);
        HttpResponse<String> response = Unirest.get(url)
                .header("Authorization", authorization)
                .asString()
                .ifFailure(resp -> {
                    try {
                        throw new Exception(String.format("Failure to get logged in user guid -> %s - %s", resp.getStatus(), resp.getBody()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
        loggedInUserGuid = new Gson().fromJson(response.getBody(), JsonObject.class).getAsJsonArray("data").get(0).getAsJsonObject().get("id").getAsString();
    }
    public void resetPassword(){
        String url = String.format("/users/%s", loggedInUserGuid);
        String authorization = String.format("Bearer %s", adminAccessToken);
        String body = String.format("{\"password\": \"%s\"}", env.getProperty("PWORD_REGISTER"));
        HttpResponse<String> response = Unirest.patch(url)
                .header("Authorization", authorization)
                .header("Content-Type", "application/json")
                .body(body)
                .asString()
                .ifFailure(resp -> {
                    try {
                        throw new Exception(String.format("Failured to reset passsword -> %s - %s", resp.getStatus(), resp.getBody()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
    }
    public void deleteTestUser(){
        String auth = String.format("Bearer %s", adminAccessToken);
        Unirest.delete(String.format("/users/%s", loggedInUserGuid))
                .header("Content-Type", "application/json")
                .header("Authorization", auth)
        .asString()
        .ifFailure(resp -> {
            try {
                throw new Exception(String.format("Failure to delete test user -> %s - %s", resp.getStatus(), resp.getBody()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }
    private void createTestUser() {
        String auth = String.format("Bearer %s", adminAccessToken);
        String json = String.format("{\n" +
                "  \"name\": \"%s\",\n" +
                "  \"email\": \"%s\",\n" +
                "  \"password\": \"%s\",\n" +
                "  \"phone\": \"%s\",\n" +
                "  \"cpf\": \"%s\",\n" +
                "  \"national_id\": \"123456789\",\n" +
                "  \"national_id_expedition_agency\": \"SSP-RS\",\n" +
                "  \"national_id_expedition_date\": \"05/05/1996\",\n" +
                "  \"birthdate\": \"06/01/1994\",\n" +
                "  \"auto_created\": false\n" +
                "}", env.getProperty("COMPLETE_NAME"), env.getProperty("EMAIL_REGISTER"), env.getProperty("PWORD_REGISTER"), env.getProperty("PHONE_REGISTER"), cpf.replace(".", "").replace("-",""));
        Unirest.post("/users")
                .header("Content-Type", "application/json")
                .header("Authorization", auth)
                .body(json)
                .asString().ifFailure(resp -> {
            try {
                throw new Exception(String.format("Failure to create test user -> %s - %s", resp.getStatus(), resp.getBody()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }
    private String checkIfUserExists() {
        String auth = String.format("Bearer %s", adminAccessToken);
        String response = Unirest.get(String.format("/users?email=%s", env.getProperty("EMAIL_REGISTER")))
                .header("Authorization", auth)
                .asString().getBody();
        return new Gson().fromJson(response, JsonObject.class).get("total").getAsString();
    }
}
