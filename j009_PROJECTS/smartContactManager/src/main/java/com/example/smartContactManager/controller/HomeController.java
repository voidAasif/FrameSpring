package com.example.smartContactManager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.smartContactManager.dao.UserRepository;
import com.example.smartContactManager.entities.Mail;
import com.example.smartContactManager.entities.Otp;
import com.example.smartContactManager.entities.User;
import com.example.smartContactManager.helper.Message;
import com.example.smartContactManager.helper.OtpHelper;
import com.example.smartContactManager.helper.SendMailHelper;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    private UserRepository userRepository; // to use JPA methods;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder; // bean from MyConfig;

    @Autowired
    private SendMailHelper sendMailHelper;

    @Autowired
    private OtpHelper otpHelper;

    @GetMapping("/")
    public String home(Model model) { // show home page;
        model.addAttribute("title", "Home - Smart Contact Manager");
        return "home";
    }

    @GetMapping("/about") // show about page;
    public String about(Model model) {
        model.addAttribute("title", "About - Smart Contact Manager");
        return "about";
    }

    @GetMapping("/signup") // show signup page;
    public String signup(Model model) {
        model.addAttribute("title", "Register - Smart Contact Manager");
        model.addAttribute("user", new User()); // set new empty user;
        return "signup";
    }

    // this handler for registering user;

    @PostMapping("/do_register") // execute when submit button clicked;
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult,
            @RequestParam(value = "agreement", defaultValue = "false") boolean agreement, Model model,
            HttpSession httpSession) {
        // ModelAttribute is used to get user from form;
        // RequestParm is used to get any value from html page;
        // HttpSession is used to display the message;

        if (bindingResult.hasErrors()) {
            System.out.println("**************************************");
            System.out.println(bindingResult.getAllErrors()); // log;
            System.out.println("**************************************");

            return "signup"; // error- display same form;
        }

        try {
            user.setEnabled(true); // set empty values of user;
            user.setRole("USER_ROLE");
            user.setImage("default.png");

            System.out.println(model); // logs;
            System.out.println(agreement);
            System.out.println();

            if (!agreement) { // if user not check the checkbox;
                System.out.println("Please accept the terms and conditions first");
                throw new Exception("Please accept the terms and conditions first"); // throw exception and jump to
                                                                                     // catch block;
            }

            // encode password;
            user.setPassword(passwordEncoder.encode(user.getPassword()));

            // temporary block the DB for validation testing;
            User savedUser = userRepository.save(user); // save user into DB;
            System.out.println("saved user: " + savedUser); // log;

            // now we can access it in HTML by (${session.className.attribute}), we take
            // content to store message and type to append class name in css;
            httpSession.setAttribute("message", new Message("Data submit successfully", "alert-success"));

            model.addAttribute("user", new User()); // if all data is correct then empty tha form;

        } catch (Exception e) {
            e.printStackTrace();

            // e.getMessage append the exception message;
            httpSession.setAttribute("message",
                    new Message("Something went wrong !!" + e.getMessage(), "alert-danger"));
            // if exception occurs then display old user data into form;
            model.addAttribute("user", user);
        }

        return "login"; // success- change view; currently not changed;
    }

    @GetMapping("/login") // display login page;
    public String loginPage(Model model) {
        model.addAttribute("title", "Login - Smart Contact Manager");
        return "login";
    }

    @GetMapping("/forgot-password")
    public String forgotPassword(Model model) {
        model.addAttribute("title", "Forgot-Password");

        return "forgotPassword";
    }

    @PostMapping("/process-forgot-password")
    public String processForgotPassword(@ModelAttribute("email") String email, HttpSession httpSession) {
        System.out.println("forgot mail: " + email); // log;

        // check credentials and send OTP to given mail;
        Mail mail = new Mail();

        mail.setMailTo(email);
        mail.setMailSubject("Aasif - Smart Contact Manager");

        String generatedOtp = otpHelper.generateOTP(); // Generate OTP;
        mail.setMailContent(
                "Your One-Time Password (OTP) is: " + generatedOtp + ". Please use this to verify your account.");

        // Store the OTP and the associated email in the session (for temporary storage)
        httpSession.setAttribute("otp", generatedOtp);
        httpSession.setAttribute("otpEmail", email);
        httpSession.setAttribute("otpExpireTime", System.currentTimeMillis() + (5 * 60 * 1000)); // Set OTP expiry
                                                                                                 // (e.g., 5 minutes)

        try {
            sendMailHelper.sendMail(mail);
            System.out.println("Mail sended successfully"); // log;
            return "otp";
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error while sending mail");
        }

        return "forgotPassword";
    }

    @PostMapping("/process-otp")
    public String processOtp(@RequestParam("otp") String userEnteredOtp, HttpSession session, Model model) {
        System.out.println("Entered OTP: " + userEnteredOtp); //log;

        // Retrieve the stored OTP from the session
        String storedOtp = (String) session.getAttribute("otp");
        String otpEmail = (String) session.getAttribute("otpEmail"); // Added to get email
        Long otpExpireTime = (Long) session.getAttribute("otpExpireTime"); // Added to get expire time

        // Check if the stored OTP exists (meaning the user has gone through the forgot
        // password flow)
        if (storedOtp != null && otpEmail != null && otpExpireTime != null) { // Added email check
            // check if OTP is expired
            if (System.currentTimeMillis() < otpExpireTime) {
                // Compare the user-entered OTP with the stored OTP
                if (storedOtp.equals(userEnteredOtp)) {
                    // OTP is valid
                    System.out.println("OTP Matched!");
                    session.removeAttribute("otp"); // Remove OTP from session after successful validation
                    session.removeAttribute("otpEmail"); // Remove email from session
                    session.removeAttribute("otpExpireTime"); // Remove expire time
                    model.addAttribute("email", otpEmail); // Pass email to reset password page.
                    return "resetPasswordForm"; // Redirect to the reset password form
                } else {
                    // OTP is invalid
                    System.out.println("OTP did not match.");
                    model.addAttribute("error", "Invalid OTP. Please try again."); // Add error message for the user
                    return "otp"; // Stay on the OTP verification page
                }
            } else {
                // OTP expired.
                model.addAttribute("error", "OTP Expired. Request new OTP");
                session.removeAttribute("otp"); // Remove OTP from session
                session.removeAttribute("otpEmail"); // Remove email from session
                session.removeAttribute("otpExpireTime"); // Remove expire time
                return "forgotPassword";
            }

        } else {
            // The user accessed this page directly without going through the forgot
            // password flow
            System.out.println("User accessed OTP verification directly or OTP was not found in session.");
            model.addAttribute("error", "Invalid request. Please initiate the forgot password process.");
            return "forgotPassword"; // Redirect to the forgot password page
        }
    }

    @PostMapping("/process-reset-password")
    public String processResetPassword(Model model, @ModelAttribute("newPassword") String newPassword, @ModelAttribute("email") String email){
        model.addAttribute("title", "Reset Password");

        try {     
            User user = userRepository.getUserByUserName(email);
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
        } catch (Exception e) {
            System.out.println("Password not changed");
            e.printStackTrace();

            return "otp";
        }


        return "login";
    }

}

/*
 * 1. @ModelAttribute("user") User user
 * Binds form data (usually from a form submission in HTML) to the User object.
 * The form fields should have names corresponding to the properties of the User
 * class.
 * 2. @RequestParam(value = "agreement", defaultValue = "false") boolean
 * agreement
 * Binds a specific request parameter (agreement) from the request to the
 * agreement boolean variable.
 * The defaultValue = "false" ensures that if the request parameter is not
 * provided, it defaults to false.
 * 3. Model model
 * Allows adding attributes to the model, which will be accessible in the view
 * template (like Thymeleaf or JSP).
 * 4. HttpSession httpSession
 * Used to store and retrieve session-specific information.
 * Can be used to pass messages between the controller and the view.
 */