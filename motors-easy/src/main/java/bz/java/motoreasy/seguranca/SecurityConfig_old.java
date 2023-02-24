//package bz.java.motoreasy.seguranca;
//
//import bz.java.motoreasy.model.Usuario;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
//import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.JdbcUserDetailsManager;
//import org.springframework.security.provisioning.UserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import javax.sql.DataSource;
//
//
//@Configuration
//@ComponentScan(basePackages = { "bz.java.motoreasy.seguranca" })
//@EnableWebSecurity
//public class SecurityConfig{
//    @Bean
//    public PasswordEncoder encoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//    @Autowired
//    private MyUserDetailsService userDetailsService;
//
//
//    @Bean
//    public InMemoryUserDetailsManager userDetailsService() {
//        UserDetails user1 = User.builder()
//                .username("adm")
//                .password(encoder().encode("123"))
//                .roles("CLIENTE", "ADMIN")
//                .build();
//        UserDetails user2 = User.builder()
//                .username("user")
//                .password(encoder().encode("123"))
//                .roles("CLIENTE")
//                .build();
//        return new InMemoryUserDetailsManager(user1, user2);
//    }
//
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests((requests) -> requests
//                        .requestMatchers("/estilo/**","/script/**","/img/**","/h2","/h2/**","/h2-console/**","/h2-console", "/database/**").permitAll()
//                        .requestMatchers("/admin/**").hasRole("ADMIN")
//                        .requestMatchers("/cliente/**").hasRole("CLIENTE")
//                        .requestMatchers("/resources/**").permitAll()
//                        .requestMatchers("/", "/home", "/catalogo", "/registrarUsuario", "/saveUsuario").permitAll()
//                        .anyRequest().authenticated()
//                ).
//                formLogin((form) -> form
//                        .loginPage("/login")
//                        .defaultSuccessUrl("/home", true)
//                        .failureUrl("/login?error=caralho")
//                        .permitAll()
//                )
//                .logout((logout) -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET")).permitAll());
//
//        return http.build();
//    }
//
//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) -> web.ignoring().requestMatchers("/h2","/h2/**","/h2-console/**","/h2-console", "/database/**");
//    }
//}