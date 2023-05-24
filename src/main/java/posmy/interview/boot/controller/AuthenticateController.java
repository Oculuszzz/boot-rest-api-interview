/**
 * 
 */
package posmy.interview.boot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import posmy.interview.boot.payload.request.AuthenticateRequest;
import posmy.interview.boot.payload.response.AuthenticationResponse;
import posmy.interview.boot.service.AuthenticateServiceImpl;

/**
 * Authenticate controller class.
 * 
 * @author mokht
 *
 */
@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class AuthenticateController {

	private final AuthenticateServiceImpl authenticateService;

	/**
	 * Class constructor.
	 * 
	 * @param authenticateService
	 */
	public AuthenticateController(AuthenticateServiceImpl authenticateService) {

		this.authenticateService = authenticateService;

	}

	/**
	 * Responsible to authenticate user.
	 * 
	 * @param authenticationRequest
	 * @return
	 */
	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> authenticateUser(
			@RequestBody AuthenticateRequest authenticationRequest) {

		return ResponseEntity.ok(authenticateService.authenticate(authenticationRequest));

	}

}
