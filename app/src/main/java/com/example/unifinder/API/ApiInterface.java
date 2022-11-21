package com.example.unifinder.API;

import com.example.unifinder.Modal.MainCategoryModel;
import com.example.unifinder.Modal.searchRides.SerchRides;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiInterface {

    @Multipart
    @POST("login")
    Call<String> Login(@Part("mobile_no") String mobile_no, @Part("password") String password);

    @Multipart
    @POST("registerUser")
    Call<String> Register(@Part("name") String name,
                          @Part("username") String username,
                          @Part("email") String email,
                          @Part("mobile_no") String mobile_no,
                          @Part("alternate_mobile_no") String alternate_mobile_no,
                          @Part("password") String password);

    @Multipart
    @POST("changePassword")
    Call<String> ChangePassword(@Part("old_password") String old_password, @Part("new_password") String new_password);

    @GET("userProfile")
    Call<String> Profile();

    @Multipart
    @POST("updateUserProfile")
    Call<String> UpdateUserProfile(
            @Part("name") String name,
            @Part("alternate_mobile_no") String alternate_mobile_no,
            @Part("password") String password);


    @Multipart
    @POST("otpVerification")
    Call<String> OtpVerification(@Part("mobile_no") String mobile_no, @Part("otp") int otp);

    @Multipart
    @POST("searchDropArea")
    Call<String> getDropArea(@Part("pickupLatitude") String pickupLatitude, @Part("pickupLongitude") String pickupLongitude,@Part("categoryId") String categoryId,@Part("categoryName") String categoryName);

    @Multipart
    @POST("searchRides")
    Call<SerchRides> SearchRides(@Part("pickupLatitude") String pickupLatitude, @Part("pickupLongitude") String pickupLongitude,@Part("dropId") String dropId,
                                 @Part("categoryId") String categoryId, @Part("categoryName") String categoryName);

    @Multipart
    @POST("registerComplain")
    Call<String> RegisterComplain(@Part("booking_number") String booking_number, @Part("message") String message);


    @Multipart
    @POST("sendBookingRequest")
    Call<String> SendBookingRequest(@Part("vehicleId") String vehicleId,
                                    @Part("pickupLocationLatitude") String pickupLocationLatitude,
                                    @Part("pickupLocationLongitude") String pickupLocationLongitude,
                                    @Part("dropOffLocation") String dropOffLocation,
                                    @Part("checkInTime") String checkInTime,
                                    @Part("checkOutTime") String checkOutTime,
                                    @Part("passengerId") String passengerId,
                                    @Part("fare") String fare,
                                    @Part("categoryId") String categoryId
    );


    @GET("getComplainsHistory")
    Call<String> GetComplainsHistory();


    @GET("getPaymentHistory")
    Call<String> GetPaymentHistory();


    @GET("getBookingHistory")
    Call<String> GetBookingHistory();


    @GET("getCurrentBookingList")
    Call<String> GetCurrentBookingList();

    @GET("getPaymentMethod")
    Call<String> GetPaymentMethodSelect();

    // new work
    @GET("getCategories")
    Call<MainCategoryModel> GetCategories(
            @Query("id") String id
    );

    @GET("getCategories")
    Call<MainCategoryModel> GetSubCategories(
            @Query("id") String id
    );

    @GET("getPassengerList")
    Call<String> GetPassengerList();

    @Multipart
    @POST("getDriverLocation")
    Call<String> DriverLocation(@Part("driverId") String driverId);


    // register passenger

    @Multipart
    @POST("registerPassenger")
    Call<String> RegisterPassenger(
            @Part("firstName") String firstName,
            @Part("lastName") String lastName,
            @Part("gender") String gender,
            @Part("address") String address,
            @Part("dob") String dob,
            @Part("serviceDate") String serviceDate);


}
