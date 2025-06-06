package com.swiftcraves.onlinefood.controller; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swiftcraves.onlinefood.model.Food;
import com.swiftcraves.onlinefood.model.Restaurant;
import com.swiftcraves.onlinefood.model.User;
import com.swiftcraves.onlinefood.request.CreateFoodRequest;
import com.swiftcraves.onlinefood.response.MessageResponse;
import com.swiftcraves.onlinefood.service.FoodService;
import com.swiftcraves.onlinefood.service.RestaurantService;
import com.swiftcraves.onlinefood.service.UserService;

//CHECKED 
@RestController
@RequestMapping("/api/admin/food")
public class AdminFoodController {

    @Autowired
    private FoodService foodService;

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<Food> createFood(@RequestBody CreateFoodRequest req,
                                            @RequestHeader("Authorization") String jwt) throws Exception{
    
        User user=userService.findUserByJwtToken(jwt);
        Restaurant restaurant=restaurantService.getRestaurantByUserId(user.getId());
        Food food=foodService.createFood(req, req.getCategory(), restaurant);
        return new ResponseEntity<>(food,HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteFood(@PathVariable Long id,
                                            @RequestHeader("Authorization") String jwt) throws Exception{
    
        User user=userService.findUserByJwtToken(jwt);
        foodService.deleteFood(id);
        MessageResponse res=new MessageResponse();
        res.setMessage("Food deleted successfully.");
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Food> updateFoodAvailabilityStatus(@PathVariable Long id,
                                                    @RequestHeader("Authorization") String jwt) throws Exception{
    
        User user=userService.findUserByJwtToken(jwt);
        Food food = foodService.updateAvailabilityStatus(id);
        return new ResponseEntity<>(food, HttpStatus.CREATED);
    }
}
//CHECK DONT CHANGE