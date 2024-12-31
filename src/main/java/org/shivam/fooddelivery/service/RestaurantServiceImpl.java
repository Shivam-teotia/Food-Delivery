package org.shivam.fooddelivery.service;

import org.shivam.fooddelivery.DTO.RestaurantDTO;
import org.shivam.fooddelivery.Model.Address;
import org.shivam.fooddelivery.Model.Restaurant;
import org.shivam.fooddelivery.Model.User;
import org.shivam.fooddelivery.Repository.AddressRepository;
import org.shivam.fooddelivery.Repository.RestaurantRepository;
import org.shivam.fooddelivery.Repository.UserRepository;
import org.shivam.fooddelivery.exception.RestaurantException;
import org.shivam.fooddelivery.request.CreateRestaurantRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImpl implements ResataurantService{
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public Restaurant createRestaurant(CreateRestaurantRequest req,User user) {
        Address address=new Address();
        address.setCity(req.getAddress().getCity());
        address.setCountry(req.getAddress().getCountry());
        if (req.getAddress().getFullName() != null) {
            address.setFullName(req.getAddress().getFullName());
        } else {
            address.setFullName(user.getFullName());
        }
        address.setPostalCode(req.getAddress().getPostalCode());
        address.setState(req.getAddress().getState());
        address.setStreetAddress(req.getAddress().getStreetAddress());
        Address savedAddress = addressRepository.save(address);

        Restaurant restaurant = new Restaurant();

        restaurant.setAddress(savedAddress);
        restaurant.setContactInformation(req.getContactInformation());
        restaurant.setCuisineType(req.getCuisineType());
        restaurant.setDescription(req.getDescription());
        restaurant.setImages(req.getImages());
        restaurant.setName(req.getName());
        restaurant.setOpeningHours(req.getOpeningHours());
        restaurant.setRegistrationDate(LocalDateTime.now());
        restaurant.setOwner(user);
        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updatedReq)
            throws RestaurantException {
        Restaurant restaurant = findRestaurantById(restaurantId);
        if (restaurant.getCuisineType() != null) {
            restaurant.setCuisineType(updatedReq.getCuisineType());
        }
        if (restaurant.getDescription() != null) {
            restaurant.setDescription(updatedReq.getDescription());
        }
        return restaurantRepository.save(restaurant);
    }

    @Override
    public void deleteRestaurant(Long restaurantId) throws RestaurantException{
        Restaurant restaurant = findRestaurantById(restaurantId);
        if (restaurant != null) {
            restaurantRepository.delete(restaurant);
            return;
        }
        throw new RestaurantException("Restaurant with id " + restaurantId + " Not found");

    }

    @Override
    public Restaurant getRestaurantsByUserId(Long userId) throws RestaurantException {
        return restaurantRepository.findByOwnerId(userId);
    }

    @Override
    public List<Restaurant> getAllRestaurant(){
        return restaurantRepository.findAll();
    }

    @Override
    public List<Restaurant>searchRestaurant(String keyword){
        return restaurantRepository.findBySearchQuery(keyword);
    }

    @Override
    public Restaurant findRestaurantById(Long id) throws RestaurantException{
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        if(restaurant.isPresent()){
            return restaurant.get();
        }
        throw new RestaurantException("Restaurant not found");
    }

    @Override
    public RestaurantDTO addToFavorites(Long restaurantId, User user) throws RestaurantException{
        Restaurant restaurant=findRestaurantById(restaurantId);

        RestaurantDTO dto=new RestaurantDTO();
        dto.setTitle(restaurant.getName());
        dto.setImages(restaurant.getImages());
        dto.setId(restaurant.getId());
        dto.setDescription(restaurant.getDescription());

        boolean isFavorited = false;
        List<RestaurantDTO> favorites = user.getFavourites();
        for (RestaurantDTO favorite : favorites) {
            if (favorite.getId().equals(restaurantId)) {
                isFavorited = true;
                break;
            }
        }
        if (isFavorited) {
            favorites.removeIf(favorite -> favorite.getId().equals(restaurantId));
        } else {
            favorites.add(dto);
        }
        userRepository.save(user);
        return dto;
    }

    @Override
    public Restaurant updateRestaurantStatus(Long id) throws RestaurantException {
        Restaurant restaurant=findRestaurantById(id);
        restaurant.setOpen(!restaurant.isOpen());
        return restaurantRepository.save(restaurant);
    }
}
