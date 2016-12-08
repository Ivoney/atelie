/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ateliens.conveter;

import br.com.ateliens.model.Servico;
import br.com.ateliens.repository.Servicos;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

@FacesConverter(forClass = Servico.class)
public class ServicosConverter implements Converter {

    @Inject
    private Servicos servicos;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Servico retorno = null;
        if (value != null && !"".equals(value)) {
            retorno = this.servicos.porId(new Long(value));
        }

        return retorno;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            Servico servico = ((Servico) value);
            return servico.getId() == null ? null : servico.getId().toString();
        }
        return null;
    }

}
