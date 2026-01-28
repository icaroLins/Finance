package br.com.icarolins.finance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.icarolins.finance.dto.TokenData;
import br.com.icarolins.finance.login.LoginRequest;
import br.com.icarolins.finance.login.LoginResponse;
import br.com.icarolins.finance.model.User;
import br.com.icarolins.finance.repository.UserRepository;
import br.com.icarolins.finance.security.JwtUtil;
import br.com.icarolins.finance.validate.ValidatorCpf;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User regiter(User user) {
        String cpfLimpo = user.getCpf().replaceAll("\\D", "");
        user.setCpf(cpfLimpo);

        if(!ValidatorCpf.isValid(user.getCpf())){
            throw new RuntimeException("Cpf invalido");
        }
        
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new RuntimeException("Email já cadastrado!");
        }

         user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public LoginResponse validatorLogin(LoginRequest request) {
        String token;
        User user = userRepository.findByEmail(request.getEmail());

        if (user == null) {
            throw new RuntimeException("Email incorreto");
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
              throw new RuntimeException("Senha incorreta");
        }

        TokenData tokenData = new TokenData(user.getEmail(),user.getId(),user.getRole());

        token = jwtUtil.generationToken(tokenData);

        return new LoginResponse(token, user.getId(), user.getEmail());

    }

    public User searchByEmail(String email){
        return userRepository.findByEmail(email);
    }

}