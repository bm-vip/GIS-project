package se.dzm.electricvehiclechargingstationmanagement.service;

import se.dzm.electricvehiclechargingstationmanagement.model.UserModel;

public interface UserService extends BaseService<UserModel, Long> {
    UserModel findByUserName(String userName);
    UserModel register(UserModel model);
}
