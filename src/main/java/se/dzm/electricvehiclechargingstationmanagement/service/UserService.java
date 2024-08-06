package se.dzm.electricvehiclechargingstationmanagement.service;

import se.dzm.electricvehiclechargingstationmanagement.filter.UserFilter;
import se.dzm.electricvehiclechargingstationmanagement.model.UserModel;

public interface UserService extends BaseService<UserFilter,UserModel, Long> {
    UserModel findByUserName(String userName);
    UserModel register(UserModel model);
}
