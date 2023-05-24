/**
 * 
 */
package posmy.interview.boot.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.ExceptionHandlingConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import posmy.interview.boot.security.JwtAuthenticationEntryPointImpl;
import posmy.interview.boot.security.JwtOncePerRequestFilter;
import posmy.interview.boot.security.JwtUtils;
import posmy.interview.boot.service.UserDetailsServiceImpl;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

/**
 * Web security configuration class.
 * 
 * @author mokht
 *
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

	/**
	 * Object of user details service implementation.
	 */
	private final UserDetailsServiceImpl userDetailsService;

	/**
	 * Object of JWT authentication entry point implementation.
	 */
	private final JwtAuthenticationEntryPointImpl unauthorizedHandler;

	/**
	 * Object of JWT utils.
	 */
	private final JwtUtils jwtUtils;

	/**
	 * Class constructor.
	 * 
	 * @param userDetailsService
	 * @param unauthorizedHandler
	 * @param jwtUtils
	 */
	public SecurityConfiguration(UserDetailsServiceImpl userDetailsService,
			JwtAuthenticationEntryPointImpl unauthorizedHandler, JwtUtils jwtUtils) {

		this.userDetailsService = userDetailsService;
		this.unauthorizedHandler = unauthorizedHandler;
		this.jwtUtils = jwtUtils;

	}

	@Bean
	JwtOncePerRequestFilter authenticationJwtTokenFilter() {

		return new JwtOncePerRequestFilter(userDetailsService, jwtUtils);

	}

	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {

		return authConfig.getAuthenticationManager();

	}

	@Bean
	PasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();

	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.csrf(wrapperCSRFCustomizer());

		http.authorizeHttpRequests(authz -> authz
				.requestMatchers("/api/v1/authenticate", "/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**")
				.permitAll().requestMatchers(toH2Console()).permitAll()
//				.requestMatchers("/api/v1/books/**").hasRole(ERole.LIBRARIAN.name())
//				.requestMatchers(HttpMethod.GET, "/api/v1/books/**").hasAuthority( EPermission.LIBRARIAN_READ.name())
//				.requestMatchers(HttpMethod.POST, "/api/v1/books/**").hasAuthority( EPermission.LIBRARIAN_CREATE.name())
//				.requestMatchers(HttpMethod.PUT, "/api/v1/books/**").hasAuthority( EPermission.LIBRARIAN_UPDATE.name())
//				.requestMatchers(HttpMethod.DELETE, "/api/v1/books/**").hasAuthority( EPermission.LIBRARIAN_DELETE.name())
//				
//				.requestMatchers("/api/v1/users/**").hasAnyRole(ERole.LIBRARIAN.name())
//				.requestMatchers(HttpMethod.GET, "/api/v1/users/**").hasAnyAuthority( EPermission.LIBRARIAN_READ.name(), EPermission.MEMBER_READ.name())
//				.requestMatchers(HttpMethod.POST, "/api/v1/users/**").hasAuthority( EPermission.LIBRARIAN_CREATE.name())
//				.requestMatchers(HttpMethod.PUT, "/api/v1/users/**").hasAuthority( EPermission.LIBRARIAN_UPDATE.name())
//				.requestMatchers(HttpMethod.DELETE, "/api/v1/users/delete-user/").hasAuthority( EPermission.LIBRARIAN_DELETE.name())

				.anyRequest().authenticated());

		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		// making H2 console working
		http.headers(headers -> headers.frameOptions().disable());

		http.authenticationProvider(authenticationProvider());
		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

		return http.build();

	}

	private Customizer<CsrfConfigurer<HttpSecurity>> wrapperCSRFCustomizer() {

		return csrf ->

		{
			try {

				csrf.ignoringRequestMatchers(toH2Console()).disable()
						.exceptionHandling(wrapperExceptionHandlingCustomizer());

			} catch (Exception e) {

				throw new RuntimeException(e.getMessage());

			}
		};

	}

	private Customizer<ExceptionHandlingConfigurer<HttpSecurity>> wrapperExceptionHandlingCustomizer() {

		return exceptionHandling -> exceptionHandling.authenticationEntryPoint(unauthorizedHandler);

	}

}
