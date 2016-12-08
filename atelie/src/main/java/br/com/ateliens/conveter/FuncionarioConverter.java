 
package br.com.ateliens.conveter;

import br.com.ateliens.model.Cliente;
import br.com.ateliens.model.Funcionario;
import br.com.ateliens.repository.Funcionarios;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

@FacesConverter(forClass = Funcionario.class)
public class FuncionarioConverter implements Converter{
    
    @Inject
    private Funcionarios funcionarios;
    
     @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Funcionario retorno = null;
        if (value != null && !"".equals(value)) {
            retorno = this.funcionarios.porId(new Long(value));
        }

        return retorno;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            Funcionario funcionario = ((Funcionario) value);
            return funcionario.getId() == null ? null : funcionario.getId().toString();
        }
        return null;
    }
    
}
