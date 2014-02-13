/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.service.MenuItem;
import model.service.OrderService;

/**
 *
 * @author Michele
 */
public class MenuController extends HttpServlet {

    private final String FULL_MENU = "/menudisplay.jsp";
    private final String INPUT_KEY = "action";
    private final String INPUT1 = "dinner";
    private final String INPUT2 = "happyhour";
    private final String INPUT3 = "sundaybrunch";
    private final String destination=null;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            
            List <MenuItem> menu = null;
            
            OrderService order = new OrderService();
            
            String key = request.getParameter(INPUT_KEY);
            switch (key) {
                case INPUT1:
                    menu = order.getAllMenuItems();
                    request.setAttribute("menu", menu);
                    break;
                case INPUT2:
                    menu = order.getAllMenuItems();
                    request.setAttribute("menu", menu);
                    break;
                default:
                    menu = order.getAllMenuItems();
                    request.setAttribute("menu", menu);
                    break;
            }
        } catch (Exception ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
       RequestDispatcher dispatcher = 
                request.getRequestDispatcher(FULL_MENU);
        dispatcher.forward(request, response);
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
