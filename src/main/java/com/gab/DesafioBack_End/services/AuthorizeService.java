package com.gab.DesafioBack_End.services;

import com.gab.DesafioBack_End.dtos.authorize.AuthorizeResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthorizeService {
    private final RestTemplate restTemplate = new RestTemplate();

    public boolean isAuthorized(){
        String url = "https://util.devi.tools/api/v2/authorize";

        try{
            ResponseEntity<AuthorizeResponse> response = restTemplate.getForEntity(url, AuthorizeResponse.class);

            if (response.getBody() == null) {
                throw new RuntimeException("Resposta vazia da API");
            }

            return response.getBody().data().authorization();
        }catch (HttpClientErrorException.Forbidden e){
            return false;
        }catch (Exception e){
            throw new RuntimeException("Erro ao chamar serviço de autorização");
        }
    }
}
