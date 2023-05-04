package pi.arctic.ecopower.config;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import pi.arctic.ecopower.Security.JwtAutenticationFilter;
import pi.arctic.ecopower.entities.Role;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecrityConfig   {
    private final AuthenticationProvider authProvider;
    private final JwtAutenticationFilter jwtAuthFilter;

    @Bean
    public LogoutHandler logoutHandler() {
        return new SecurityContextLogoutHandler();
    }


    @Bean

    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .authorizeHttpRequests()
                .antMatchers("/auth/register").permitAll()
                .antMatchers("/logout").permitAll()
                .antMatchers("**").permitAll()
                .antMatchers("/login/oauth2/google").permitAll()
                .antMatchers("/user/add-user").hasAuthority(Role.Admin.name())
                .antMatchers("/user/getUsers").hasAuthority(Role.Admin.name())
                .antMatchers("/user/get-user/{id}").hasAuthority(Role.Admin.name())
                .antMatchers("/user/remove-user/{id}").hasAuthority(Role.Admin.name())
                .antMatchers("/user/updateUser").hasAuthority(Role.Admin.name())
                .antMatchers("/user/UpdatePassword/{oldPassword}/{newPassword}").
                hasAnyAuthority(Role.Admin.name()
                        , Role.User.name())
                .antMatchers("/auth/authenticate").permitAll()
                .antMatchers("/checkout/**").hasAnyAuthority(Role.Provider.name() ,Role.User.name())

                .anyRequest()
                .authenticated()
                .and()
                .logout()
                .logoutUrl("/logout")
                .invalidateHttpSession(true)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
    return http.build();
}}
