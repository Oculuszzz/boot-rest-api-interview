/**
 * 
 */
package posmy.interview.boot.service;

import org.springframework.stereotype.Service;

import posmy.interview.boot.payload.request.AuthenticateRequest;
import posmy.interview.boot.payload.response.AuthenticationResponse;

/**
 * @author mokht
 *
 */
@Service
public interface AuthenticateService {

	public AuthenticationResponse authenticate(AuthenticateRequest authenticationRequest);

}
