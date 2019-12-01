package id.bekt.anjrtz.iam.service.impl;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import id.bekt.anjrtz.common.service.BaseService;
import id.bekt.anjrtz.iam.constant.KeycloakAdminConstant;
import id.bekt.anjrtz.iam.service.IKeycloakAdminClientService;
import id.bekt.anjrtz.iam.vo.UserVO;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.infinispan.Cache;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.manager.EmbeddedCacheManager;
import org.jose4j.json.internal.json_simple.JSONObject;
import org.keycloak.common.util.Base64;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.TimeUnit;

@ApplicationScoped
public class KeycloakAdminClientService extends BaseService implements IKeycloakAdminClientService {

    EmbeddedCacheManager cacheManager = new DefaultCacheManager();
    Cache<String, String> cache;

    Client client = ClientBuilder.newClient();

    @ConfigProperty(name = "iam.keycloak.admin.secret")
    String adminSecret;

    @ConfigProperty(name = "iam.keycloak.admin.user")
    String adminUser;

    @ConfigProperty(name = "iam.keycloak.admin.pass")
    String adminPass;

    @ConfigProperty(name = "iam.keycloak.admin.grant_type")
    String adminGrantType;

    @ConfigProperty(name = "iam.keycloak.admin.client_id")
    String adminClientId;

    @ConfigProperty(name = "iam.keycloak.admin.client_secret")
    String adminClientSecret;

    @ConfigProperty(name = "iam.keycloak.admin.realms")
    String adminRealm;

    public KeycloakAdminClientService(){
        cacheManager.defineConfiguration("token", new ConfigurationBuilder().build());
        cache = cacheManager.getCache("token");
    }

    @Override
    public String getKeycloakAdminToken() {

        Form formData = new Form()
                .param(KeycloakAdminConstant.FIELD_USERNAME, adminUser)
                .param(KeycloakAdminConstant.FIELD_PASSWORD,adminPass)
                .param(KeycloakAdminConstant.FIELD_GRANT_TYPE, adminGrantType);

        String auth = Base64.encodeBytes((adminClientId + ":" + adminClientSecret).getBytes());
        String token = null;

        String cachedToken = (String) cache.get(adminUser);
        if (StringUtils.isEmpty(cachedToken)) {
            logger.info("cache empty, request new token");
            try(final Response response = client.target(KeycloakAdminConstant.BASE_URL + KeycloakAdminConstant.ADMIN_TOKEN_URL)
                    .request()
                    .header(HttpHeaders.AUTHORIZATION,"Basic "+auth)
                    .post(Entity.form(formData));
            ) {
                JSONObject json = response.readEntity(JSONObject.class);
                token = (String) json.get(KeycloakAdminConstant.FIELD_ACCESS_TOKEN);
            }catch (Exception e){
                logger.error("Exception : {}", e);
            }
            cache.put(adminUser, token, 1, TimeUnit.HOURS);
        }else{
            logger.info("using token from cache");
            token = cachedToken;
        }

        return token;
    }

    @Override
    public List<UserVO> getKeycloakUserList(String token) {
        List<UserVO> results  = null;
        try{
            final Response response = client.target(KeycloakAdminConstant.BASE_URL + KeycloakAdminConstant.ADMIN_USER_LIST)
                    .request()
                    .header(HttpHeaders.AUTHORIZATION, "Bearer "+token)
                    .get(Response.class);

            if(HttpStatus.SC_UNAUTHORIZED == response.getStatus()){
                //expire token
                cache.clear();
            }

            Type listType = new TypeToken<List<UserVO>>() {}.getType();
            results = new Gson().fromJson(response.readEntity(String.class), listType);
        }catch (Exception e){
            logger.error("Exception : {}", e);
        }
        return results;
    }
}
