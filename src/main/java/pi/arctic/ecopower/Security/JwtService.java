package pi.arctic.ecopower.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@AllArgsConstructor
public class JwtService implements IJwtService {
    private static final String SECRT="4E635266556A586E3272357538782F413F4428472B4B6250645367566B5970337336763979244226452948404D6351665468576D5A7134743777217A25432A46";
    @Override
    public String extractUserEmail(String jwt) {
        return extractClaim(jwt , Claims::getSubject);    }

    @Override
    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date((System.currentTimeMillis() + 1000 * 60 * 60)))
                .signWith(getSignInKey(), SignatureAlgorithm.HS512)
                .compact();   }

    @Override
    public Boolean isTokenExpired(String jwt) {
        return extractExpiration(jwt).before(new Date());
    }
    @Override
    public Date extractExpiration(String jwt) {
        return extractClaim(jwt, Claims::getExpiration);    }

    @Override
    public Boolean isTokenValid(String jwt, UserDetails userDetails) {
        final String userEmail = extractUserEmail(jwt);
        return (userEmail.equals(userDetails.getUsername())) && !isTokenExpired(jwt);    }

    @Override
    public Claims extractAllClaims(String jwt) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(jwt)
                .getBody();    }

    @Override
    public <T> T extractClaim(String jwt, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims (jwt);
        return claimsResolver.apply(claims);
    }

    @Override
    public Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRT);
        return Keys.hmacShaKeyFor(keyBytes);    }

    @Override
    public String generateTokenWithoutExtraClaims(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);    }
}

