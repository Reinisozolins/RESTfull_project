package lv.ozo.awsspringtest.ui.userservice;

import lv.ozo.awsspringtest.ui.model.UserDetailsRequestModel;
import lv.ozo.awsspringtest.ui.model.UserRest;

public interface UserServiceInterface {
    UserRest createUser(UserDetailsRequestModel userDetailsRequestModel);
}
