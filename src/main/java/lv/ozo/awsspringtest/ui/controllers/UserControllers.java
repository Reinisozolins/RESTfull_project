package lv.ozo.awsspringtest.ui.controllers;

import lv.ozo.awsspringtest.ui.exceptions.UserServiceException;
import lv.ozo.awsspringtest.ui.impl.UserServiceImpl;
import lv.ozo.awsspringtest.ui.model.UpdateUserDetailsRequestModel;
import lv.ozo.awsspringtest.ui.model.UserDetailsRequestModel;
import lv.ozo.awsspringtest.ui.model.UserRest;
import lv.ozo.awsspringtest.ui.userservice.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("users") //http://localhost:8080/users
public class UserControllers {

    Map<String, UserRest> users;

    @Autowired
    UserServiceInterface userServiceInterface;

    @GetMapping
    public String getUsers(@RequestParam(value = "page", defaultValue = "1") int page,
                           @RequestParam(value = "limit", defaultValue = "20") int limit,
                           @RequestParam(value = "sort", required = false) String sort) {
        if (sort == null) sort = "desc";
        return "get user was called with  page = " + page + "and limit = " + limit + "and sort " + sort;
    }

    @GetMapping(path = "/{userID}",
    produces = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE
    })

    public ResponseEntity <UserRest> getUser(@PathVariable String userID)
    {

//        For  user exception Handler
        if (true)throw new UserServiceException("A user service exception is thrown");




//        if users contains a key witch is userID than
        if (users.containsKey(userID))
        {
//            than returns ResponseEntity witch contain userID
            return new ResponseEntity<>( users.get(userID), HttpStatus.OK);
//            else return empty responseEntity
        }else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

//        return new ResponseEntity<UserRest>( users.get(userID), HttpStatus.OK);
    }


//    {
//        UserRest returnValue = new UserRest();
//        returnValue.setEmail("test@test.lv");
//        returnValue.setFirstName("Reinis");
//        returnValue.setLastName("Ozo");
//
//        return new ResponseEntity<UserRest>( HttpStatus.BAD_REQUEST);
//    }



//    @PostMapping
//    public String createUser()
//    {
//        return "create user was called";
//    }

    @PostMapping(
            consumes = {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE
            },
            produces = {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE
            }
    )

    public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserDetailsRequestModel userDetails) {
//        Moved to UserServiceImpl

//        UserRest returnValue = new UserRest();
//        returnValue.setEmail(userDetails.getEmail());
//        returnValue.setFirstName(userDetails.getFirstName());
//        returnValue.setLastName(userDetails.getLastName());
//
////        check if there is not users, than create new
//        String userID = UUID.randomUUID().toString();
//        returnValue.setUserID(userID);
//
//        if (users == null ) users = new HashMap<>();
//        users.put(userID, returnValue);

//        I add userServiceInterface so don't need any more
//        UserRest returnValue = new UserServiceImpl().createUser(userDetails);
        UserRest returnValue = userServiceInterface.createUser(userDetails);
        return new ResponseEntity<UserRest>(returnValue, HttpStatus.OK);
    }


    @PutMapping(path = "/{userID}", consumes = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE
    },
    produces = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE
    })
    public UserRest updateUser(@PathVariable String userID, @Valid @RequestBody UpdateUserDetailsRequestModel userDetails)
    {
        UserRest storedUserDetails = users.get(userID);
        storedUserDetails.setFirstName(userDetails.getFirstName());
        storedUserDetails.setLastName(userDetails.getLastName());

        users.put(userID, storedUserDetails);
        return storedUserDetails;
    }

//    @PutMapping
//    public  String updateUser()
//    {
//        return "update user was called";
//    }

//    @DeleteMapping
//    public  String deleteUser()
//    {
//        return "delete user was called";
//    }
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id)
    {
        users.remove(id);
        return  ResponseEntity.noContent().build();
    }
}
