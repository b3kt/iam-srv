package id.bekt.anjrtz.iam.service.impl;

import id.bekt.anjrtz.iam.service.IKeycloakClientService;
import id.bekt.anjrtz.iam.vo.UserVO;

import java.util.List;
import java.util.Optional;

public class KeycloakClientService implements IKeycloakClientService {

    @Override
    public List<UserVO> getAllUsers() {
        return null;
    }

    @Override
    public Optional<UserVO> getUserByUsername(String username) {
        return Optional.empty();
    }


}
