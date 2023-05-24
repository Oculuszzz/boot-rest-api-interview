/**
 * 
 */
package posmy.interview.boot.service;

import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import posmy.interview.boot.payload.request.AuthenticateRequest;
import posmy.interview.boot.payload.response.AuthenticationResponse;
import posmy.interview.boot.security.JwtUtils;
import posmy.interview.boot.userdetails.UserDetailsImpl;

/**
 * @author mokht
 *
 */
@Service
public class AuthenticateServiceImpl implements AuthenticateService {

	private final AuthenticationManager authenticationManager;

	private final JwtUtils jwtUtils;

	/**
	 * @param authenticationManager
	 * @param jwtUtils
	 */
	public AuthenticateServiceImpl(AuthenticationManager authenticationManager, JwtUtils jwtUtils) {

		this.authenticationManager = authenticationManager;
		this.jwtUtils = jwtUtils;

	}

	@Override
	public AuthenticationResponse authenticate(AuthenticateRequest request) {

		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				request.getUsername(), request.getPassword()));

//		SecurityContextHolder.getContext().setAuthentication(authentication);
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority()).toList();

		String accessToken = jwtUtils.generateJWTToken(userDetails);

		return new AuthenticationResponse(accessToken, userDetails.getId(), userDetails.getUsername(), roles);
	}

}
