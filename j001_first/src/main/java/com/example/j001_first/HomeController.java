package com.example.j001_first;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    
    @GetMapping("/") //home mapping (default url);
    public String home(){
        System.out.println("Home fun");
        return "index"; // Return index.html;
    }

    @PostMapping("/post") //executed when form submitted;
    public String getDataFromIndexInputs(@RequestParam("input1") String input1, //RequestParam annotation get data from Form and set into variable;
                                         @RequestParam("input2") String input2,
                                         Model model) { //model is used to set data on html page;
        
        String msg = "Received--> " + input1 + " " + input2;
        System.out.println(msg);
        model.addAttribute("message", msg+"'s Data Submitted Successfully"); //set data on 'message' attribute in result.html bcz it return result.html;
        return "result"; // A view to show the result or confirmation message
    }
}
