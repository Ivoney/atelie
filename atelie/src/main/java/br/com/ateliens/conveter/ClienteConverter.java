package br.com.ateliens.conveter;

import br.com.ateliens.model.Cliente;
import br.com.ateliens.repository.Clientes;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

@FacesConverter(forClass = Cliente.class)
public class ClienteConverter implements Converter {

    @Inject
    private Clientes clientes;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Cliente retorno = null;
        if (value != null && !"".equals(value)) {
            retorno = this.clientes.porId(new Long(value));
        }

        return retorno;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            Cliente cliente = ((Cliente) value);
            return cliente.getId() == null ? null : cliente.getId().toString();
        }
        return null;
    }

}
