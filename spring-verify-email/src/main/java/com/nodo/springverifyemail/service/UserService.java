package com.nodo.springverifyemail.service;

import com.nodo.springverifyemail.entity.ActivationKey;
import com.nodo.springverifyemail.entity.User;
import com.nodo.springverifyemail.repository.ActivationKeyRepository;
import com.nodo.springverifyemail.repository.UserRepository;
import net.bytebuddy.utility.RandomString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    private final Logger log = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ActivationKeyRepository activationKeyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender mailSender;

    public List<User> listAll() {
        return userRepository.findAll();
    }

    public String register(User user, String siteURL) {
        if (isExistedUser(user.getEmail())) return "Email has already existed!";
        String message = "Fail to create new user";
        try {
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            user.setRole((byte) 1);
            user.setActivated((byte) 0);
            userRepository.save(user);
            log.info("Saved new user.");
            String randomKey = RandomString.make(64);
            ActivationKey activationKey = new ActivationKey();
            activationKey.setUserId(user.getId());
            activationKey.setActiveKey(randomKey);
            activationKey.setExpiredAt(LocalDateTime.now().plusMinutes(10));
            activationKeyRepository.save(activationKey);
            log.info("Saved new activation key.");
            sendVerificationEmail(user, siteURL);
            message = "Register success";
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return message;
    }

    public boolean isExistedUser(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) return true;
        return false;
    }
    private void sendVerificationEmail(User user, String siteURL) {
        try {
            String toAddress = user.getEmail();
            String fromAddress = "thinhnb2757@gmail.com";
            String senderName = "Nodo JSC";
            String subject = "Please verify your registration";
            ActivationKey activationKey = activationKeyRepository.findActivationKeyByUserId(user.getId());
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            String verifyURL = siteURL + "/verify?key=" + activationKey.getActiveKey();
            String content = "Hi" + user.getName() + ",<br>"
                    + "Please click the link below to verify your registration:<br>"
                    + "<h3><a href=\"" + verifyURL + "\" target=\"_self\">VERIFY</a></h3>";

            helper.setFrom(fromAddress, senderName);
            helper.setTo(toAddress);
            helper.setSubject(subject);
            helper.setText(content, true);
            mailSender.send(message);

            log.info("Email has been sent");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    public String verify(String verificationKey) {
        ActivationKey activationKey = activationKeyRepository.findByActiveKey(verificationKey);
        if (activationKey.isExpired()) {
            return "Activation key expired!";
        }
        Optional<User> user = userRepository.findById(activationKey.getUserId());
        if (user.isPresent()) {
            if (user.get().getActivated() == 1) {
                return "User has already activated!";
            } else {
                user.get().setActivated((byte) 1);
                userRepository.save(user.get());
                return "Active user successfully!";
            }
        } else {
            return "User is invalid!";
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
