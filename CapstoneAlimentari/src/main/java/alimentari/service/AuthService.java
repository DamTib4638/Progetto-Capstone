package alimentari.service;

import alimentari.payload.LoginDto;
import alimentari.payload.RegisterDto;

public interface AuthService {
    
	String login(LoginDto loginDto);
    String register(RegisterDto registerDto);
    
}
