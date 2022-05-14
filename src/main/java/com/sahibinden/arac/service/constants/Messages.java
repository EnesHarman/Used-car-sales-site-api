package com.sahibinden.arac.service.constants;

import com.sahibinden.arac.dto.responses.CompareVehiclesResponse;

public interface Messages {
     String CUSTOMER_REGISTERED= "You have been successfully registered.";
    String ROLE_ADDED = "The role has been added to the service successfully.";
    String VEHICLE_ADDED = "Your vehicle has been added successfully.";
    String VEHICLE_NOT_FOUND = "There is no vehicle with that id. Please try again later.";
    String VEHICLE_UPDATED = "The vehicle has been updated successfully.";
    String NOT_AUTHORIZED_ACTION = "You are not allowed to take this action.";
    String COMMENT_ADDED = "Your comment has been added.";
    String COMMENT_DELETED = "Your comment has been deleted successfully.";
    String COMMENT_NOT_FOUND = "There is no comment with that id. Please try again later.";
    String VEHICLE_PUBLISHED = "The vehicle published successfully.";
    String UNPUBLISHED_ALREADY = "Your vehicle is not published.";
    String UNPUBLISH_VEHICLE = "Your vehicle is unpublished now.";
    String PUBLISHED_ALREADY = "The vehicle has been already published.";
    String VEHICLE_COMPARE_ERROR = "Please provide 2 vehicle to compare.";
}
