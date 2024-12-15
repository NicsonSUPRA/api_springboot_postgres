package com.nicson.apipostgres.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.stereotype.Component;

import com.nicson.apipostgres.models.Client;
import com.nicson.apipostgres.services.ClientService;

@Component
public class CustomRegisteredClientRepository implements
        RegisteredClientRepository {

    @Autowired
    private ClientService clientService;

    @Autowired
    private TokenSettings tokenSettings;

    @Autowired
    private ClientSettings clientSettings;

    public CustomRegisteredClientRepository() {
    }

    @Override
    public RegisteredClient findByClientId(String arg0) {
        System.out.println("passou aqui 1");
        Client client = clientService.obterPorClientId(arg0);

        if (client == null) {
            System.out.println("nenhum cliente encontrado");
            return null;
        }
        System.out.println("passou aqui ihwbxuw");
        return RegisteredClient
                .withId(client.getId().toString())
                .clientId(client.getClientId())
                .clientSecret(client.getClientSecret())
                .redirectUri(client.getRedirectURI())
                .scope(client.getScope())
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                .tokenSettings(tokenSettings)
                .clientSettings(clientSettings)
                .build();
    }

    @Override
    public RegisteredClient findById(String arg0) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public void save(RegisteredClient registeredClient) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

}
