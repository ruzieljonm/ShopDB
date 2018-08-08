package com.example.demo.controller;

import com.example.demo.entities.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * Created by ruzieljonm on 04/08/2018.
 */
@Controller
public class ProductController {

    @Autowired
    ProductService productService;

    @RequestMapping("/addproduct")
    public String addProduct(HttpServletRequest request, HttpSession session, Model model){

        Product newProd = new Product();
        newProd.setProductname(request.getParameter("prodname"));
        newProd.setProductprice(request.getParameter("prodprice"));
        newProd.setProductsellerid(Integer.parseInt(session.getAttribute("userid").toString()));
        productService.save(newProd);

        ArrayList<Product> items = productService.findAllByProductsellerid(Integer.parseInt(session.getAttribute("userid").toString()));
        for (int i=0; i<items.size(); i++){
            System.out.println("hey" + items.get(i).getProductname());
        }
        model.addAttribute("items", items);
        return "HomepageSeller";
    }

    @RequestMapping("/deleteitem")
    public String deleteItem(HttpServletRequest request, HttpSession session, Model model){
        String deletethis = request.getParameter("item");
        productService.deleteByProductid(Integer.parseInt(deletethis));

        ArrayList<Product> items = productService.findAllByProductsellerid(Integer.parseInt(session.getAttribute("userid").toString()));
        model.addAttribute("items", items);
        return showShop(model,session);
    }

    ArrayList<String> selected = new ArrayList<String>();
    ArrayList<Integer> prices = new ArrayList<Integer>();

    ArrayList<Product> selectedItems = new ArrayList<Product>();





    public Product getProdDetails(String productid){
        Product prod = productService.findByProductid(Integer.parseInt(productid));
        return prod;
    }



    @RequestMapping("/checkout")
    public String displayarr(Model model){


            model.addAttribute("selected", selectedItems);
            model.addAttribute("total", computeTotal(selectedItems));
            return "Checkout";
    }





    public int computeTotal(ArrayList<Product> selectedItems){
        int total=0;
        if(selectedItems.size()==0){
            return 0;
        }else{
            for (int i = 0; i<selectedItems.size(); i++)
                total+=Integer.parseInt(selectedItems.get(i).getProductprice());

            return total;
        }


    }

    @RequestMapping("/shop")
    public String showShop(Model model, HttpSession session){

        if(session.isNew()){
            return "LoginPage";
        }else {
            if(session.getAttribute("usertype").toString().equals("buyer")){
                ArrayList<Product> items = productService.findAll();
                model.addAttribute("items", items);
                return "Shop";
            }else{
                ArrayList<Product> items = productService.findAllByProductsellerid(Integer.parseInt(session.getAttribute("userid").toString()));
                model.addAttribute("items", items);
                return "HomepageSeller";
            }

        }
    }

    @RequestMapping("/testingthis")
    public String showTest(){
        return "Checkout";
    }

    @RequestMapping("/add")
    public String addtItem(HttpServletRequest request, HttpSession session, Model model){
        String item = request.getParameter("item");
        System.out.println("haha" + item);

        Product newprod = new Product();
        newprod.setProductname(getProdDetails(item).getProductname());
        newprod.setProductprice(getProdDetails(item).getProductprice());
        newprod.setProductsellerid(getProdDetails(item).getProductsellerid());
        newprod.setProductid(getProdDetails(item).getProductid());
        selectedItems.add(newprod);

        model.addAttribute("selected", selectedItems);

        ArrayList<Product> itemlist= productService.findAll();
        model.addAttribute("items", itemlist);
        int total = computeTotal(selectedItems);
        model.addAttribute("total", total);
        return "Shop";
    }

    @RequestMapping("/cancel")
    public String cancel(Model model, HttpServletRequest request){

        String cancelitem = request.getParameter("cancelitem");

        int id = Integer.parseInt(cancelitem);
        for (int i = 0; i < selectedItems.size(); i++) {
            if(id == selectedItems.get(i).getProductid()){
                    selectedItems.remove(i);
                break;
            }
        }


        model.addAttribute("selected", selectedItems);
        ArrayList<Product> itemlist= productService.findAll();
        model.addAttribute("items", itemlist);
        int total = computeTotal(selectedItems);
        model.addAttribute("total", total);
        return "Shop";
    }









}
