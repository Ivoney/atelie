/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ateliens.conveter;

import br.com.ateliens.model.Grupo;
import br.com.ateliens.repository.Grupos;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

 
@FacesConverter(forClass = Grupo.class)
public class GrupoConverter implements Converter{

    @Inject
    private Grupos grupos;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
 
        Grupo retorno = null;
        if(value != null && !"".equals(value)){
            retorno = this.grupos.porId(new Long(value));
        }
        return retorno;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {

        if(value != null){
            Grupo grupo = ((Grupo) value);
            return grupo.getId() == null ? null : grupo.getId().toString();
        }

        return null;
    }
    
}
