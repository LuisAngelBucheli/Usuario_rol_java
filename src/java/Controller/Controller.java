/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DBOjects.Rol;
import DBOjects.Usuario;
import SQLObjects.RolDAO;
import SQLObjects.UsuarioDAO;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author ANGEL
 */
@WebServlet(name = "Controller", urlPatterns = {"/Controller"})
public class Controller extends HttpServlet {
    
    Usuario user = new Usuario();
    UsuarioDAO userDb = new UsuarioDAO();
    Rol rol = new Rol();
    RolDAO rolDb = new RolDAO();

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
        String action = request.getParameter("action");
        switch (action){
            case "listRoles":
                listRoles(request, response);
                break;
            case "saveUser":
                saveUser(request, response);
                break;
            case "modifyUser":
                modifyUser(request, response);
                break;
            case "deleteUser":
                deleteUser(request, response);
                break;
            case "listUsers":
                listUsers(request, response);
                break;
            default:
                break;
        }
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

    private void listRoles(HttpServletRequest request, HttpServletResponse response) {
        try {
            PrintWriter out = response.getWriter();
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<Rol> roles = rolDb.listAll();
        Gson json = new Gson();
        String rolList = json.toJson(roles);
        response.setContentType("text/html");
        try {
            response.getWriter().write(rolList);
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void saveUser(HttpServletRequest request, HttpServletResponse response) {
        boolean success = true;
        user.setIdRol(Integer.parseInt(request.getParameter("rol")));
        user.setNombre(request.getParameter("name"));
        user.setActivo(request.getParameter("active"));
        List<Usuario> users = userDb.selectUsers(request.getParameter("name"));
        for (Usuario usr : users) {
            if (request.getParameter("name").equals(usr.getNombre())) {
                success = false;
            }
        }
        if (success) {
            response.setHeader("Content-Type", "text/plain");
            if (userDb.insert(user)) {
                response.setHeader("state", "success");
            } else {
                response.setHeader("state", "fail");
            }
        } else {
            String message = "El usuario ya se encuentra registrado";
            try {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message);
            } catch (IOException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
 
    private void listUsers(HttpServletRequest request, HttpServletResponse response) {
        try {
            PrintWriter out = response.getWriter();
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<Usuario> users = userDb.selectUsers(request.getParameter("name"));
        Gson json = new Gson();
        String usersList = json.toJson(users);
        response.setContentType("text/html");
        try {
            response.getWriter().write(usersList);
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void modifyUser(HttpServletRequest request, HttpServletResponse response) {
        user.setIdUsuario(Integer.parseInt(request.getParameter("iduser")));
        user.setIdRol(Integer.parseInt(request.getParameter("rol")));
        user.setNombre(request.getParameter("name"));
        user.setActivo(request.getParameter("active"));
        response.setHeader("Content-Type", "text/plain");
        if (userDb.update(user)) {
                response.setHeader("state", "success");
        } else {
            response.setHeader("state", "fail");
        }
    }
    
    private void deleteUser(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Content-Type", "text/plain");
        if (userDb.delete(Integer.parseInt(request.getParameter("iduser")))) {
                response.setHeader("state", "success");
        } else {
            response.setHeader("state", "fail");
        }
    }

}
