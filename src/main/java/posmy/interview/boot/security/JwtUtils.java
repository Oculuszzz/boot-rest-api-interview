package posmy.interview.boot.security;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import posmy.interview.boot.userdetails.UserDetailsImpl;

/**
 * @author mokht
 *
 */
@Component
public class JwtUtils {

	private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

	@Value("${posmy.interview.boot.jwtSecret}") // get value from properties file
	private String jwtSecret;

	@Value("${posmy.interview.boot.jwtExpiration}") // get value from properties file
	private Long jwtExpiration;

	/**
	 * @param userDetails
	 * @return
	 */
	public String generateJWTToken(UserDetailsImpl userDetails) {

		return generateJWTToken(userDetails, new HashMap<>());

	}

	/**
	 * @param userDetails
	 * @param extraClaims
	 * @return
	 */
	public String generateJWTToken(UserDetailsImpl userDetails, Map<String, Object> extraClaims) {

		Key key = getSigningKey();
		JwtBuilder jwtBuilder = Jwts.builder();
		jwtBuilder.setClaims(extraClaims); // Always set claims first before set subject. Or else it's will causes
											// problem when trying decode the token.
		jwtBuilder.setSubject((userDetails.getUsername()));
//		jwtBuilder.setIssuer(issuerUrl);
		jwtBuilder.setIssuedAt(new Date()); // Set current time
		jwtBuilder.setExpiration(new Date((new Date()).getTime() + jwtExpiration)); // Set token expiration
		jwtBuilder.signWith(key, SignatureAlgorithm.HS256); // Decode token to SHA-256

		return jwtBuilder.compact();

	}

	/**
	 * @return
	 */
	private Key getSigningKey() {

		byte[] base64BytesKey = Decoders.BASE64URL.decode(jwtSecret);

		return Keys.hmacShaKeyFor(base64BytesKey);

	}

	/**
	 * @param authToken
	 * @return
	 */
	public boolean validateJWTToken(String jwt) {

		boolean tokenOK = false;

		try {

			Key key = getSigningKey();

			Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt);

			tokenOK = true;

		} catch (ExpiredJwtException e) {

			logger.error(String.format("JWT Token is expired - %s", e.getMessage()));

		} catch (UnsupportedJwtException e) {

			logger.error(String.format("Unsupported JWT Token - %s", e.getMessage()));

		} catch (MalformedJwtException e) {

			logger.error(String.format("Invalid JWT Token format - %s", e.getMessage()));

		} catch (IllegalArgumentException e) {

			logger.error(String.format("Invalid JWT Token argument - %s", e.getMessage()));

		} catch (SignatureException e) {

			logger.error(String.format("Invalid JWT Token signature - %s", e.getMessage()));

		}

		return tokenOK;

	}

	/**
	 * @param <T>
	 * @param token
	 * @param claimResolver
	 * @return
	 */
	public <T> T getClaim(String token, Function<Claims, T> claimResolver) {

		final Claims claims = getAllClaims(token);

		return claimResolver.apply(claims);

	}

	/**
	 * @param token
	 * @return
	 */
	private Claims getAllClaims(String token) {

		Key key = getSigningKey();

		return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();

	}

	/**
	 * @param token
	 * @return
	 */
	public String getUsernameFromToken(String token) {

		return getClaim(token, Claims::getSubject);

	}

}
