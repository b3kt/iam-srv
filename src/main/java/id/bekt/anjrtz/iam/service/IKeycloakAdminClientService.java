package id.bekt.anjrtz.iam.service;

import id.bekt.anjrtz.iam.vo.UserVO;

import java.util.List;

public interface IKeycloakAdminClientService {

    String getKeycloakAdminToken();

    List<UserVO> getKeycloakUserList(String token);
}
