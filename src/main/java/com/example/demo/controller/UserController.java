package com.example.demo.controller;


import com.example.demo.entities.Product;
import com.example.demo.entities.User;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * Created by ruzieljonm on 31/07/2018.
 */
@SuppressWarnings("Duplicates")
@Controller
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    ProductService productService;
    @Autowired
    ProductController productController;

    @RequestMapping("/signup")
    public String showLoginPage(HttpSession session, Model model){
        if(session.isNew()) {
            session.invalidate();
            return "SignupPage";
        }else{
            if(session.getAttribute("usertype").toString().equals("seller")) {
                ArrayList<Product> items = productService.findAllByProductsellerid(Integer.parseInt(session.getAttribute("userid").toString()));
                model.addAttribute("items",items);
                return "HomepageSeller";
            }else{
                ArrayList<Product> items = productService.findAll();
                model.addAttribute("items",items);
                return "Shop";
            }
        }
    }

    @RequestMapping("/signupuser")
    public String signupUser(HttpServletRequest request, Model model, HttpSession session) {

//        if(!session.isNew()){
//            if(session.getAttribute("usertype").toString().equals("seller")) {
//                ArrayList<Product> items = productService.findAllByProductsellerid(Integer.parseInt(session.getAttribute("userid").toString()));
//                model.addAttribute("items", items);
//                return "HomepageSeller";
//            }else{
//                ArrayList<Product> items = productService.findAll();
//                model.addAttribute("items", items);
//                return "Shop";
//            }
//        }else {

            User newuser = new User();
            newuser.setFname(request.getParameter("fname"));
            newuser.setLname(request.getParameter("lname"));
            newuser.setEmail(request.getParameter("email"));
            newuser.setPassword(request.getParameter("password"));
            newuser.setUsertype(request.getParameter("type"));

            userService.saveUser(newuser);

            User user = userService.findByFname(request.getParameter("fname"));
            //System.out.println(user.getFname() + " " + user.getLname());

            if (request.getParameter("type").equals("buyer")) {
                session.setAttribute("fname", request.getParameter("fname"));
                session.setAttribute("lname", request.getParameter("lname"));
                session.setAttribute("userid", user.getUserid());
                session.setAttribute("usertype", user.getUsertype());
                model.addAttribute("user", user);
                ArrayList<Product> items = productService.findAll();

                model.addAttribute("items", items);
                return "Shop";
            } else {
                session.setAttribute("fname", request.getParameter("fname"));
                session.setAttribute("lname", request.getParameter("lname"));
                session.setAttribute("userid", user.getUserid());
                session.setAttribute("usertype", user.getUsertype());
                ArrayList<Product> items = productService.findAllByProductsellerid(user.getUserid());

                model.addAttribute("items", items);
                return "HomepageSeller";
            }
//        }
    }

    @RequestMapping("/signin")
    public String showSignin(HttpSession session, Model model){
        if(session.isNew()) {
            session.invalidate();
            return "LoginPage";

        }else{

            User user = userService.findByUserid(Integer.parseInt(session.getAttribute("userid").toString()));
            session.setAttribute("fname",user.getFname());
            session.setAttribute("lname",user.getLname() );
            session.setAttribute("userid",user.getUserid());
            session.setAttribute("usertype",user.getUsertype());
            if(user.getUsertype().equals("seller")) {
                ArrayList<Product> items = productService.findAllByProductsellerid(user.getUserid());
                model.addAttribute("items", items);
                return "HomepageSeller";
            }else{
                ArrayList<Product> items = productService.findAll();
                model.addAttribute("items", items);
                return "Shop";
            }
        }
    }

    @RequestMapping("/signinuser")
    public String signinUser(HttpServletRequest request, HttpSession session, Model model){
//        if(!session.isNew()){
//            if(session.getAttribute("usertype").toString().equals("seller")) {
//                ArrayList<Product> items = productService.findAllByProductsellerid(Integer.parseInt(session.getAttribute("userid").toString()));
//                model.addAttribute("items", items);
//                return "HomepageSeller";
//            }else{
//                ArrayList<Product> items = productService.findAll();
//                model.addAttribute("items", items);
//                return "Shop";
//            }
//        }else {
            String email = request.getParameter("email");
            String pass = request.getParameter("password");

            User user = userService.findByEmail(email);

            if (user != null && user.getPassword().equals(pass)) {
                session.setAttribute("userid", user.getUserid());
                session.setAttribute("fname", user.getFname());
                session.setAttribute("lname", user.getLname());
                session.setAttribute("usertype", user.getUsertype());

                ArrayList<Product> items;
                if (user.getUsertype().equals("buyer")) {
                    items = productService.findAll();
                    model.addAttribute("items", items);
                    return "Shop";
                } else {
                    items = productService.findAllByProductsellerid(user.getUserid());
                    model.addAttribute("items", items);
                    return "HomepageSeller";
                }
            } else {
                return "LoginPage";
            }
//        }

    }

    @RequestMapping("/signout")
    public String signOut(HttpSession session){
        session.invalidate();
        productController.selectedItems.clear();

        return "LoginPage";

    }





}
