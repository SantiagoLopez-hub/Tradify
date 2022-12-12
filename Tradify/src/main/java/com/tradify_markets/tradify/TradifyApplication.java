package com.tradify_markets.tradify;

import com.tradify_markets.tradify.model.Announcement;
import com.tradify_markets.tradify.model.Role;
import com.tradify_markets.tradify.model.Share;
import com.tradify_markets.tradify.model.User;
import com.tradify_markets.tradify.repository.AnnouncementRepository;
import com.tradify_markets.tradify.repository.RoleRepository;
import com.tradify_markets.tradify.repository.ShareRepository;
import com.tradify_markets.tradify.repository.UserRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class TradifyApplication {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ShareRepository shareRepository;
    private final AnnouncementRepository announcementRepository;

    public TradifyApplication(UserRepository userRepository, RoleRepository roleRepository, ShareRepository shareRepository, AnnouncementRepository announcementRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.shareRepository = shareRepository;
        this.announcementRepository = announcementRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(TradifyApplication.class, args);
    }

    @Bean
    InitializingBean sendDatabase() {
        return () -> {
            roleRepository.save(
                    Role.builder()
                            .id(1)
                            .name("Admin")
                            .build()
            );
            roleRepository.save(
                    Role.builder()
                            .id(2)
                            .name("User")
                            .build()
            );


            userRepository.save(
                    User.builder()
                            .id(1)
                            .email("Jhn@gmail.com")
                            .password("pass3ord")
                            .firstName("John")
                            .lastName("Doe")
                            .phoneNumber("1234456789")
                            .address("123 Main St")
                            .city("London")
                            .postCode("E1 6D")
                            .country("UK")
                            .role(roleRepository.findById(1).get())
                            .build()
            );

            userRepository.save(
                    User.builder()
                            .id(2)
                            .email("Jgohn@gmail.com")
                            .password("password")
                            .firstName("John")
                            .lastName("Doe")
                            .phoneNumber("1223456789")
                            .address("123 Street")
                            .city("Paris")
                            .postCode("E 6BD")
                            .country("UK")
                            .role(roleRepository.findById(2).get())
                            .build()
            );
            userRepository.save(
                    User.builder()
                            .id(3)
                            .email("Jon@gmail.com")
                            .password("pa8swo1d")
                            .firstName("John")
                            .lastName("Doe")
                            .phoneNumber("1234556789")
                            .address("12 Fake Street")
                            .city("Madrid")
                            .postCode("1 6BD")
                            .country("UK")
                            .build()
            );
            userRepository.save(
                    User.builder()
                            .id(4)
                            .email("Jochbn@gmail.com")
                            .password("as2s1wa33ord")
                            .firstName("John")
                            .lastName("Doe")
                            .phoneNumber("8123456789")
                            .address("13 Fake Street")
                            .city("Moscow")
                            .postCode("E1 D")
                            .country("UK")
                            .build()
            );
            userRepository.save(
                    User.builder()
                            .id(5)
                            .email("John@bgmail.com")
                            .password("p9asw6ord")
                            .firstName("John")
                            .lastName("Doe")
                            .phoneNumber("12384456789")
                            .address("3 Fake Street")
                            .city("Milan")
                            .postCode("E1 6B")
                            .country("UK")
                            .build()
            );

            announcementRepository.save(
                    Announcement.builder()
                            .id(1)
                            .title("Test")
                            .content("Test")
                            .build()
            );
            shareRepository.save(
                    Share.builder()
                            .id(1)
                            .price(100)
                            .announcements(List.of(announcementRepository.findById(1).get()))
                            .build()
            );
        };
    }
}
