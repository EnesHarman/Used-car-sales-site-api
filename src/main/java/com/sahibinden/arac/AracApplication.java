package com.sahibinden.arac;

import com.sahibinden.arac.model.*;
import com.sahibinden.arac.service.*;
import com.sahibinden.arac.service.constants.DBConstants;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class AracApplication {

    public static void main(String[] args) {
        SpringApplication.run(AracApplication.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.sahibinden.arac"))
                .build();
    }

    @Bean
    CommandLineRunner run(RoleService roleService, FuelTypeService fuelTypeService, VehicleTypeService vehicleTypeService, ManagerService managerService, AppUserService appUserService) {
        Role role = Role.builder()
                .roleId(DBConstants.DB_ROLE_MANAGER_ID)
                .build();

        AppUser appUser = AppUser.builder()
                .appUserEmail("testmanager@gmail.com")
                .appUserPassword("123456")
                .appUserName("Test")
                .appUserSurname("Manager")
                .role(role)
                .build();

        Manager manager = Manager.builder()
                .managerIdentityNumber("12345678900")
                .user(appUser)
                .build();

        return args -> {
            roleService.addRole(new Role(2, "ROLE_MANAGER"));
            roleService.addRole(new Role(3, "ROLE_CUSTOMER"));

            appUserService.saveAppUser(appUser);
            managerService.saveManager(manager);

            fuelTypeService.addFuelType(new FuelType(1, "DIESEL"));

            vehicleTypeService.addVehicleType(new VehicleType(1, "AUTO"));
        };
    }
}
