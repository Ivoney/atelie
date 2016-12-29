package br.com.ateliens.controller;

import br.com.ateliens.util.jsf.FacesUtil;
import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Named
@SessionScoped
public class LoginBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private FacesContext facesContext;

    @Inject
    private ExternalContext externalContext;

    @Inject
    private HttpServletRequest request;

    @Inject
    private HttpServletResponse response;

    private String email;

    public void preRender() {
        if ("true".equals(request.getParameter("invalid"))) {
            FacesUtil.addErrorMessage("Usuário ou senha inválido!");
        }
    }

    public void login() throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/j_spring_security_check");
        dispatcher.forward(request, response);

        facesContext.responseComplete();
    }

    public boolean isAdmin(String role) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        return request.isUserInRole("ADMINISTRADORES");
       
    }

    public boolean isFuncionario(String role) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        return request.isUserInRole("FUNCIONARIOS");
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
