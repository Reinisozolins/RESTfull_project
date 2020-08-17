package lv.ozo.awsspringtest.ui.impl;

import lv.ozo.awsspringtest.ui.model.UserDetailsRequestModel;
import lv.ozo.awsspringtest.ui.model.UserRest;
import lv.ozo.awsspringtest.ui.shared.Utils;
import lv.ozo.awsspringtest.ui.userservice.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

//Don't forget add Service annotation
@Service
public class UserServiceImpl implements UserServiceInterface {

    Map<String, UserRest> users;

    //    Declare utilities
    Utils utils;

    public UserServiceImpl() {
    }

    @Autowired
    public UserServiceImpl(Utils utils) {
        this.utils = utils;

    }

    //    Add implements methods
    @Override
    public UserRest createUser(UserDetailsRequestModel userDetails) {

        UserRest returnValue = new UserRest();
        returnValue.setEmail(userDetails.getEmail());
        returnValue.setFirstName(userDetails.getFirstName());
        returnValue.setLastName(userDetails.getLastName());

////        check if there is not users, than create new
//        String userID = UUID.randomUUID().toString();
//        returnValue.setUserID(userID);

        //        check if there is not users, than create new
        String userID = utils.generatedUserID();
        returnValue.setUserID(userID);

        if (users == null) users = new HashMap<>();
        users.put(userID, returnValue);

        return returnValue;
    }
}
