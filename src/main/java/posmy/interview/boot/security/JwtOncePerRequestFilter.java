/**
 * 
 */
package posmy.interview.boot.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import posmy.interview.boot.service.UserDetailsServiceImpl;

/**
 * Implementation of once per request filter class.
 * 
 * @author mokht
 *
 */
@Component
public class JwtOncePerRequestFilter extends OncePerRequestFilter {

	/**
	 * Object of user details service implementation.
	 */
	private final UserDetailsServiceImpl userDetailsService;

	/**
	 * Object of JWT utils.
	 */
	private final JwtUtils jwtUtils;

	/**
	 * Class constructor.
	 * 
	 * @param detailsService
	 * @param jwtUtils
	 */
	public JwtOncePerRequestFilter(UserDetailsServiceImpl userDetailsService, JwtUtils jwtUtils) {

		this.userDetailsService = userDetailsService;
		this.jwtUtils = jwtUtils;

	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String jwt = parseJwt(request);

		if (jwt != null && SecurityContextHolder.getContext().getAuthentication() == null
				&& jwtUtils.validateJWTToken(jwt)) {

			String username = jwtUtils.getUsernameFromToken(jwt);
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);

			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,
					null, userDetails.getAuthorities());
			authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

			SecurityContextHolder.getContext().setAuthentication(authentication);

		}

		filterChain.doFilter(request, response);

	}

	/**
	 * Retrieve actually token values.
	 * 
	 * @param request
	 * @return
	 */
	private String parseJwt(HttpServletRequest request) {

		String headerAuth = request.getHeader("Authorization");

		if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer-")) {

			return headerAuth.substring(7, headerAuth.length());

		}

		return null;
	}

}
