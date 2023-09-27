package com.example.application_calculator;

import java.io.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "CalculatorServlet", value = "/Calculator-servlet")
public class CalculatorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int num1 = Integer.parseInt(req.getParameter("num1"));
        int num2 = Integer.parseInt(req.getParameter("num2"));
        String option = req.getParameter("option");
        int result = 0;
        String error = null;
        try {
            switch (option) {
                case "sum":
                    result = num1 + num2;
                    break;
                case "minus":
                    result = num1 - num2;
                    break;
                case "core":
                    result = num1 * num2;
                    break;
                case "divide":
                    result = num1 / num2;
                    break;
            }
        }catch (Exception e){
            error = e.getMessage();
        }

        if(error!= null){
            req.setAttribute("error",error);
        }else {
            req.setAttribute("result",result);
        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/result.jsp");
        requestDispatcher.forward(req,resp);
    }
}